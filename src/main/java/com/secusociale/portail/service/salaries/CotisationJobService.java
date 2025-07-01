package com.secusociale.portail.service.salaries;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.secusociale.portail.domain.Carriere;
import com.secusociale.portail.domain.Salarie;
import com.secusociale.portail.repository.CarriereRepository;
import com.secusociale.portail.repository.SalarieRepository;
import com.secusociale.portail.service.CarriereService;
import com.secusociale.portail.service.dto.CarriereDTO;
import com.secusociale.portail.service.dto.custom.ExtraInfosDTO;
import com.secusociale.portail.service.immatriculation.ImmatPortailService;
import com.secusociale.portail.service.soap.cotisationParAnne.CmRecupererInfosCotisationParAnnee;
import com.secusociale.portail.service.soap.detailsCotisationParAnne.CmRecupererInfosDetailsCotisation;
import com.secusociale.portail.service.soap.employeurExistant.CMGETEMPLOYEURDETAILS;
import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import javax.xml.ws.Holder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CotisationJobService {
    private final CotisationService cotisationService;
    private final DetailsCotisationService detailsCotisationService;
    private final SalarieRepository salarieRepository;
    private final CarriereRepository carriereRepository;
    private final CarriereService carriereService;
    private final ImmatPortailService immatPortailService;

    public CotisationJobService(CotisationService cotisationService, DetailsCotisationService detailsCotisationService, SalarieRepository salarieRepository, CarriereRepository carriereRepository, CarriereService carriereService, ImmatPortailService immatPortailService) {
        this.cotisationService = cotisationService;
        this.detailsCotisationService = detailsCotisationService;
        this.salarieRepository = salarieRepository;
        this.carriereRepository = carriereRepository;
        this.carriereService = carriereService;
        this.immatPortailService = immatPortailService;
    }

    @Transactional
    @Scheduled(cron = "#{@carriereJobExpression}")
    public void populateCotisations() {
        this.job();
    }

    public void job() {
        List<Salarie> salarieList = salarieRepository.findAll();
        salarieList.forEach(this::jobForSingleSalarie);
    }

    public void jobForSingleSalarie(Salarie salarie, String annee) {

        CmRecupererInfosDetailsCotisation elmt = new CmRecupererInfosDetailsCotisation();
        CmRecupererInfosCotisationParAnnee.Results cotisation = getByAnnee(salarie, annee);
        if (!StringUtils.isEmpty(salarie.getNumeroCni())) {
            elmt.setTypePiece("NIN");
            elmt.setNumeroPiece(salarie.getNumeroCni());
            elmt.setAnnee(cotisation != null ? cotisation.getAnnee() : annee);
            try {
                Holder<CmRecupererInfosDetailsCotisation> reponse = detailsCotisationService.detailsCotisation(elmt);
                if (reponse.value != null && reponse.value.getResults().size() > 0) {
                    List<CmRecupererInfosDetailsCotisation.Results> results = reponse.value.getResults();
                    List<String> ids = new ArrayList<>();
                    HashMap<String, String> raisons = new HashMap<>();
                    if (!results.isEmpty()) {
                        ids = results.stream().map(CmRecupererInfosDetailsCotisation.Results::getIdEmployeur).distinct().collect(Collectors.toList());
                        ids.forEach(s -> {
                            CMGETEMPLOYEURDETAILS employeurDetails = new CMGETEMPLOYEURDETAILS();
                            CMGETEMPLOYEURDETAILS.Input input = new CMGETEMPLOYEURDETAILS.Input();
                            input.setNumeroUnique(s);
                            employeurDetails.setInput(input);
                            try {
                                Holder<CMGETEMPLOYEURDETAILS> holder = immatPortailService.getEmployeurExistant(employeurDetails);
                                if (holder.value != null) {
                                    raisons.put(s, holder.value.getOutput().getRaisonSocial());
                                }
                            } catch (JAXBException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    Salarie sal = salarieRepository.findByNumeroCni(elmt.getNumeroPiece()).orElse(null);
                    results.forEach(line -> {
                        LocalDate debut = LocalDate.parse(line.getDateDebutPeriodeCotisation());
                        LocalDate fin = LocalDate.parse(line.getDateFinPeriodeCotisation());
                        Optional<CarriereDTO> carriereDTO = carriereService.findSalarie(line.getIdEmployeur(), debut, fin, sal);
                        if (!carriereDTO.isPresent()) {
                            Carriere carriere = new Carriere();
                            //numeroUniqueSalarie
                            carriere.setSalarie(sal);
                            //exercice
                            carriere.setExercice(StringUtils.isEmpty(line.getAnnee()) ? null : Integer.parseInt(line.getAnnee()));
                            //numeroUniqueEmployeur
                            carriere.setNumeroUniqueEmployeur(line.getIdEmployeur());
                            carriere.setNumeroUniqueSalarie(sal != null ? sal.getNumeroUnique() : null);
                            carriere.setRaisonSociale(raisons.get(line.getIdEmployeur()));
                            carriere.setDebutCotisation(debut);
                            carriere.setFinCotisation(fin);
                            carriere.setCotisationCss(Double.parseDouble(line.getMontantATMP()) + Double.parseDouble(line.getMontantPF()));
                            carriere.setCotisationIpres(Double.parseDouble(line.getMontantRC()) + Double.parseDouble(line.getMontantRG()));
                            ObjectMapper objectMapper = new ObjectMapper();
                            try {
                                ExtraInfosDTO extraInfos = new ExtraInfosDTO(cotisation, line);
                                carriere.setExtraInfos(objectMapper.writeValueAsString(extraInfos));
                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
                                carriere.setExtraInfos(null);
                            }

                            carriereRepository.save(carriere);
                        }
                    });
                }
            } catch (JAXBException e) {
                e.printStackTrace();
            }

        } else {
            if (!StringUtils.isEmpty(salarie.getNumeroUnique())) {
                elmt.setPersonId(salarie.getNumeroUnique());
                elmt.setAnnee(cotisation != null ? cotisation.getAnnee() : annee);
                try {
                    Holder<CmRecupererInfosDetailsCotisation> reponse = detailsCotisationService.detailsCotisation(elmt);
                    if (reponse.value != null && reponse.value.getResults().size() > 0) {
                        List<CmRecupererInfosDetailsCotisation.Results> results = reponse.value.getResults();
                        List<String> ids = new ArrayList<>();
                        HashMap<String, String> raisons = new HashMap<>();
                        if (!results.isEmpty()) {
                            ids = results.stream().map(CmRecupererInfosDetailsCotisation.Results::getIdEmployeur).distinct().collect(Collectors.toList());
                            ids.forEach(s -> {
                                CMGETEMPLOYEURDETAILS employeurDetails = new CMGETEMPLOYEURDETAILS();
                                CMGETEMPLOYEURDETAILS.Input input = new CMGETEMPLOYEURDETAILS.Input();
                                input.setNumeroUnique(s);
                                employeurDetails.setInput(input);
                                try {
                                    Holder<CMGETEMPLOYEURDETAILS> holder = immatPortailService.getEmployeurExistant(employeurDetails);
                                    if (holder.value != null) {
                                        raisons.put(s, holder.value.getOutput().getRaisonSocial());
                                    }
                                } catch (JAXBException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                        Salarie sal = salarieRepository.findByNumeroUnique(elmt.getPersonId()).orElse(null);
                        results.forEach(line -> {
                            LocalDate debut = LocalDate.parse(line.getDateDebutPeriodeCotisation());
                            LocalDate fin = LocalDate.parse(line.getDateFinPeriodeCotisation());
                            Optional<CarriereDTO> carriereDTO = carriereService.findSalarie(line.getIdEmployeur(), debut, fin, sal);
                            if (!carriereDTO.isPresent()) {
                                Carriere carriere = new Carriere();
                                //numeroUniqueSalarie
                                carriere.setSalarie(sal);
                                //exercice
                                carriere.setExercice(StringUtils.isEmpty(line.getAnnee()) ? null : Integer.parseInt(line.getAnnee()));
                                //numeroUniqueEmployeur
                                carriere.setNumeroUniqueEmployeur(line.getIdEmployeur());
                                carriere.setNumeroUniqueSalarie(sal != null ? sal.getNumeroUnique() : null);
                                carriere.setRaisonSociale(raisons.get(line.getIdEmployeur()));
                                carriere.setDebutCotisation(debut);
                                carriere.setFinCotisation(fin);
                                carriere.setCotisationCss(Double.parseDouble(line.getMontantATMP()) + Double.parseDouble(line.getMontantPF()));
                                carriere.setCotisationIpres(Double.parseDouble(line.getMontantRC()) + Double.parseDouble(line.getMontantRG()));
                                ObjectMapper objectMapper = new ObjectMapper();
                                try {
                                    ExtraInfosDTO extraInfos = new ExtraInfosDTO(cotisation, line);
                                    carriere.setExtraInfos(objectMapper.writeValueAsString(extraInfos));
                                } catch (JsonProcessingException e) {
                                    e.printStackTrace();
                                    carriere.setExtraInfos(null);
                                }

                                carriereRepository.save(carriere);
                            }
                        });
                    }
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void jobForSingleSalarie(Salarie salarie) {

        CmRecupererInfosDetailsCotisation elmt = new CmRecupererInfosDetailsCotisation();

        if (!StringUtils.isEmpty(salarie.getNumeroCni())) {
            elmt.setTypePiece("NIN");
            elmt.setNumeroPiece(salarie.getNumeroCni());
            getByAnnee(salarie).forEach(cotisation -> {
                elmt.setAnnee(cotisation.getAnnee());
                try {
                    Holder<CmRecupererInfosDetailsCotisation> reponse = detailsCotisationService.detailsCotisation(elmt);
                    if (reponse.value != null && reponse.value.getResults().size() > 0) {
                        List<CmRecupererInfosDetailsCotisation.Results> results = reponse.value.getResults();

                        List<String> ids = new ArrayList<>();
                        HashMap<String, String> raisons = new HashMap<>();
                        if (!results.isEmpty()) {
                            ids = results.stream().map(CmRecupererInfosDetailsCotisation.Results::getIdEmployeur).distinct().collect(Collectors.toList());
                            ids.forEach(s -> {
                                CMGETEMPLOYEURDETAILS employeurDetails = new CMGETEMPLOYEURDETAILS();
                                CMGETEMPLOYEURDETAILS.Input input = new CMGETEMPLOYEURDETAILS.Input();
                                input.setNumeroUnique(s);
                                employeurDetails.setInput(input);
                                try {
                                    Holder<CMGETEMPLOYEURDETAILS> holder = immatPortailService.getEmployeurExistant(employeurDetails);
                                    if (holder.value != null) {
                                        raisons.put(s, holder.value.getOutput().getRaisonSocial());
                                    }
                                } catch (JAXBException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                        Salarie sal = salarieRepository.findByNumeroCni(elmt.getNumeroPiece()).orElse(null);
                        results.forEach(line -> {
                            LocalDate debut = LocalDate.parse(line.getDateDebutPeriodeCotisation());
                            LocalDate fin = LocalDate.parse(line.getDateFinPeriodeCotisation());
                            Optional<CarriereDTO> carriereDTO = carriereService.findSalarie(line.getIdEmployeur(), debut, fin, sal);
                            if (!carriereDTO.isPresent()) {
                                Carriere carriere = new Carriere();
                                //numeroUniqueSalarie
                                carriere.setSalarie(sal);
                                //exercice
                                carriere.setExercice(StringUtils.isEmpty(line.getAnnee()) ? null : Integer.parseInt(line.getAnnee()));
                                //numeroUniqueEmployeur
                                carriere.setNumeroUniqueEmployeur(line.getIdEmployeur());
                                carriere.setNumeroUniqueSalarie(sal != null ? sal.getNumeroUnique() : null);
                                carriere.setRaisonSociale(raisons.get(line.getIdEmployeur()));
                                carriere.setDebutCotisation(debut);
                                carriere.setFinCotisation(fin);
                                carriere.setCotisationCss(Double.parseDouble(line.getMontantATMP()) + Double.parseDouble(line.getMontantPF()));
                                carriere.setCotisationIpres(Double.parseDouble(line.getMontantRC()) + Double.parseDouble(line.getMontantRG()));
                                ObjectMapper objectMapper = new ObjectMapper();
                                try {
                                    ExtraInfosDTO extraInfos = new ExtraInfosDTO(cotisation, line);
                                    carriere.setExtraInfos(objectMapper.writeValueAsString(extraInfos));
                                } catch (JsonProcessingException e) {
                                    e.printStackTrace();
                                    carriere.setExtraInfos(null);
                                }

                                carriereRepository.save(carriere);
                            }
                        });
                    }
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            });

        } else {
            if (!StringUtils.isEmpty(salarie.getNumeroUnique())) {
                elmt.setPersonId(salarie.getNumeroUnique());
                getByAnnee(salarie).forEach(cotisation -> {
                    elmt.setAnnee(cotisation.getAnnee());
                    try {
                        Holder<CmRecupererInfosDetailsCotisation> reponse = detailsCotisationService.detailsCotisation(elmt);
                        if (reponse.value != null && reponse.value.getResults().size() > 0) {
                            List<CmRecupererInfosDetailsCotisation.Results> results = reponse.value.getResults();
                            List<String> ids = new ArrayList<>();
                            HashMap<String, String> raisons = new HashMap<>();
                            if (!results.isEmpty()) {
                                ids = results.stream().map(CmRecupererInfosDetailsCotisation.Results::getIdEmployeur).distinct().collect(Collectors.toList());
                                ids.forEach(s -> {
                                    CMGETEMPLOYEURDETAILS employeurDetails = new CMGETEMPLOYEURDETAILS();
                                    CMGETEMPLOYEURDETAILS.Input input = new CMGETEMPLOYEURDETAILS.Input();
                                    input.setNumeroUnique(s);
                                    employeurDetails.setInput(input);
                                    try {
                                        Holder<CMGETEMPLOYEURDETAILS> holder = immatPortailService.getEmployeurExistant(employeurDetails);
                                        if (holder.value != null) {
                                            raisons.put(s, holder.value.getOutput().getRaisonSocial());
                                        }
                                    } catch (JAXBException e) {
                                        e.printStackTrace();
                                    }
                                });
                            }
                            Salarie sal = salarieRepository.findByNumeroUnique(elmt.getPersonId()).orElse(null);
                            results.forEach(line -> {
                                LocalDate debut = LocalDate.parse(line.getDateDebutPeriodeCotisation());
                                LocalDate fin = LocalDate.parse(line.getDateFinPeriodeCotisation());
                                Optional<CarriereDTO> carriereDTO = carriereService.findSalarie(line.getIdEmployeur(), debut, fin, sal);
                                if (!carriereDTO.isPresent()) {
                                    Carriere carriere = new Carriere();
                                    //numeroUniqueSalarie
                                    carriere.setSalarie(sal);
                                    //exercice
                                    carriere.setExercice(StringUtils.isEmpty(line.getAnnee()) ? null : Integer.parseInt(line.getAnnee()));
                                    //numeroUniqueEmployeur
                                    carriere.setNumeroUniqueEmployeur(line.getIdEmployeur());
                                    carriere.setNumeroUniqueSalarie(sal != null ? sal.getNumeroUnique() : null);
                                    carriere.setRaisonSociale(raisons.get(line.getIdEmployeur()));
                                    carriere.setDebutCotisation(debut);
                                    carriere.setFinCotisation(fin);
                                    carriere.setCotisationCss(Double.parseDouble(line.getMontantATMP()) + Double.parseDouble(line.getMontantPF()));
                                    carriere.setCotisationIpres(Double.parseDouble(line.getMontantRC()) + Double.parseDouble(line.getMontantRG()));
                                    ObjectMapper objectMapper = new ObjectMapper();
                                    try {
                                        ExtraInfosDTO extraInfos = new ExtraInfosDTO(cotisation, line);
                                        carriere.setExtraInfos(objectMapper.writeValueAsString(extraInfos));
                                    } catch (JsonProcessingException e) {
                                        e.printStackTrace();
                                        carriere.setExtraInfos(null);
                                    }

                                    carriereRepository.save(carriere);
                                }
                            });
                        }
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                });
            }
        }

    }

    public List<CmRecupererInfosCotisationParAnnee.Results> getByAnnee(Salarie salarie) {
        CmRecupererInfosCotisationParAnnee elmt = new CmRecupererInfosCotisationParAnnee();
        if (!StringUtils.isEmpty(salarie.getNumeroCni())) {
            elmt.setTypePiece("NIN");
            elmt.setNumeroPiece(salarie.getNumeroCni());
            try {
                Holder<CmRecupererInfosCotisationParAnnee> reponse = cotisationService.cotisationParAnnee(elmt);
                if (reponse.value != null) {
                    return reponse.value.getResults();
                }
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        } else {
            if (!StringUtils.isEmpty(salarie.getNumeroUnique())) {
                elmt.setPersonId(salarie.getNumeroUnique());
                try {
                    Holder<CmRecupererInfosCotisationParAnnee> reponse = cotisationService.cotisationParAnnee(elmt);
                    if (reponse.value != null) {
                        return reponse.value.getResults();
                    }
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ArrayList<>();
    }

    public CmRecupererInfosCotisationParAnnee.Results getByAnnee(Salarie salarie, String annee) {
        CmRecupererInfosCotisationParAnnee elmt = new CmRecupererInfosCotisationParAnnee();
        if (!StringUtils.isEmpty(salarie.getNumeroCni())) {
            elmt.setTypePiece("NIN");
            elmt.setNumeroPiece(salarie.getNumeroCni());
            try {
                Holder<CmRecupererInfosCotisationParAnnee> reponse = cotisationService.cotisationParAnnee(elmt);
                if (reponse.value != null && reponse.value.getResults().size() > 0) {
                    List<CmRecupererInfosCotisationParAnnee.Results> list = reponse.value.getResults().stream().filter(results -> annee.equals(results.getAnnee())).collect(Collectors.toList());
                    return list.size() > 0 ? list.get(0) : null;
                }
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        } else {
            if (!StringUtils.isEmpty(salarie.getNumeroUnique())) {
                elmt.setPersonId(salarie.getNumeroUnique());
                try {
                    Holder<CmRecupererInfosCotisationParAnnee> reponse = cotisationService.cotisationParAnnee(elmt);
                    if (reponse.value != null && reponse.value.getResults().size() > 0) {
                        List<CmRecupererInfosCotisationParAnnee.Results> list = reponse.value.getResults().stream().filter(results -> annee.equals(results.getAnnee())).collect(Collectors.toList());
                        return list.size() > 0 ? list.get(0) : null;
                    }
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
