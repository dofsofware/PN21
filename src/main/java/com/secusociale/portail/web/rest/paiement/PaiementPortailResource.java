package com.secusociale.portail.web.rest.paiement;

import com.secusociale.portail.domain.APGPaiment;
import com.secusociale.portail.model.ListPaiementInputModel;
import com.secusociale.portail.repository.APGPaimentRepository;
import com.secusociale.portail.service.dto.custom.NewImpayeesDTO;
import com.secusociale.portail.service.paiement.*;
import com.secusociale.portail.service.soap.derniersPaiements.DERNPAYEMPLOYEURSERVICE;
import com.secusociale.portail.service.soap.detailsFactureDNS.DetailsFactureDNSService;
import com.secusociale.portail.service.soap.facturesImpayes.FACTURESIMPAYEESSERVICE;
import com.secusociale.portail.service.soap.listPaiements.CMGETPAYMENT;
import com.secusociale.portail.service.soap.newfactureimpayees.CMPAYDNSXAI;
import com.secusociale.portail.service.soap.recuPaiement.CmGenererUrlRecuEmployeur;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;

import javax.servlet.ServletContext;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.ws.Holder;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PaiementPortailResource {

    private final ServletContext servletContext;

    @Autowired
    private DerniersPaiementsService derniersPaiementsService;

    @Autowired
    private ListPaiementsService listPaiementsService;

    @Autowired
    private FacturesImpayesService facturesImpayesService;

    @Autowired
    private NewFacturesImpayesService newFacturesImpayesService;

    @Autowired
    private DetailsFactureService detailsFactureService;

    @Autowired
    private RecuFactureService recuFactureService;

    private final Logger log = LoggerFactory.getLogger(PaiementPortailResource.class);

    @Autowired
    private APGPaimentRepository apgPaimentRepository;

    public PaiementPortailResource(ServletContext servletContext) {
        this.servletContext = servletContext;
    }


    @GetMapping("/recuPaiement/url/{payEventId}")
    Holder<CmGenererUrlRecuEmployeur> getRecuHolder(@PathVariable String payEventId) throws JAXBException {
        return recuFactureService.getRecu(payEventId);
    }

    @GetMapping("/recuPaiement/{payEventId}")
    ResponseEntity<Object> getRecuStream(@PathVariable String payEventId) throws Exception {
        Holder<CmGenererUrlRecuEmployeur> result = new Holder<>();
        String fileName = "";
        String stringUrl = "";

        try {
            result = recuFactureService.getRecu(payEventId);
            if (result.value != null) {
                stringUrl = result.value.getOutput().getUrl();
                String[] tab = stringUrl.substring(6).split("/");
                fileName = tab[tab.length - 1];
                if (StringUtils.isEmpty(stringUrl))
                    throw new Exception("Aucune facture pour cette declaration!");
                URL url = new URL(stringUrl);
                InputStream is = url.openStream();
                byte[] data = IOUtils.toByteArray(is);
                ByteArrayResource resource = new ByteArrayResource(data);
                MediaType mediaType = MediaType.APPLICATION_PDF;
                return ResponseEntity.ok()
                    // Content-Disposition
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                    // Content-Type
                    .contentType(mediaType) //
                    // Content-Length
                    .contentLength(data.length) //
                    .body(resource);
            }
        } catch (Exception e) {
            throw new Exception(Problem.builder().withTitle("Erreur lors du telechargement du recu").with("stringUrl", stringUrl).withDetail("Verifier si l'url >> " + stringUrl + " existe").build());
        }

        return null;
    }

    @PostMapping("/listPaiements")
    Holder<CMGETPAYMENT> getListPaiements(@RequestBody ListPaiementInputModel model) {
        try {
            return listPaiementsService.getListPaiements(model);
        } catch (JAXBException | DatatypeConfigurationException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/listPaiement/{numeroUnique}")
    Holder<DERNPAYEMPLOYEURSERVICE> getListePaiement(@PathVariable String numeroUnique) throws JAXBException {
        return derniersPaiementsService.getDerniersPaiementEmployeur(numeroUnique);
    }

    @GetMapping("/urlFacture/{formId}")
    Holder<DetailsFactureDNSService> getUrlFacture(@PathVariable String formId) throws JAXBException {
        return detailsFactureService.getUrlFacture(formId);
    }

    @GetMapping("/facturesImpayes/{numeroUnique}")
    List<FactureImpayee> getListfactureImapye(@PathVariable String numeroUnique) throws JAXBException {
        Holder<FACTURESIMPAYEESSERVICE> impayes = new Holder<FACTURESIMPAYEESSERVICE>();
        impayes = facturesImpayesService.getFactureImpaye(numeroUnique);
        List<FACTURESIMPAYEESSERVICE.Output> impayees = impayes.value.getOutput();
        List<FactureImpayee> impayeesFinal = new ArrayList<>();
        for (FACTURESIMPAYEESSERVICE.Output fac : impayees) {
            List<APGPaiment> list = apgPaimentRepository.findAllByFacturesConcerneesContains(fac.getNumeroFacture());
            int lastIndex = list.size() - 1;
            if (list.isEmpty()) {
                impayeesFinal.add(new FactureImpayee(fac, "UNPAID"));
            } else {
                String stat = list.get(lastIndex).getStatut();
                impayeesFinal.add(new FactureImpayee(fac, stat));
            }
        }
        return impayeesFinal;
    }

    @GetMapping("/factures/impayees/{numeroUnique}")
    ResponseEntity<List<NewImpayeesDTO>> getFacturesImpayees(@PathVariable String numeroUnique) throws JAXBException {
        Holder<CMPAYDNSXAI> holder = newFacturesImpayesService.getFactureImpaye(numeroUnique);
        List<NewImpayeesDTO> impayeesFinal = new ArrayList<>();
        if (holder != null && holder.value != null) {
            for (CMPAYDNSXAI.Results impayee : holder.value.getResults()) {
                List<APGPaiment> list = apgPaimentRepository.findAllByFacturesConcerneesContains(impayee.getNumeroFacture());
                int lastIndex = list.size() - 1;
                if (list.isEmpty()) {
                    impayeesFinal.add(new NewImpayeesDTO(impayee, "UNPAID"));
                } else {
                    String stat = list.get(lastIndex).getStatut();
                    impayeesFinal.add(new NewImpayeesDTO(impayee, stat));
                }
            }
        }

        return ResponseEntity.ok(impayeesFinal);
    }

    public static Map<String, String> splitQuery(String query) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }

    static class FactureImpayee {
        private String numeroFacture;
        private String typeFacture;
        private String dateEcheance;
        private String montantPrincipal;
        private String majorations;
        private String montantTotal;
        private String dateDebut;
        private String dateFin;
        private String dette;
        private String montantPaye;
        private String penalite;
        private String status;

        public String getNumeroFacture() {
            return numeroFacture;
        }

        public void setNumeroFacture(String numeroFacture) {
            this.numeroFacture = numeroFacture;
        }

        public String getTypeFacture() {
            return typeFacture;
        }

        public void setTypeFacture(String typeFacture) {
            this.typeFacture = typeFacture;
        }

        public String getDateEcheance() {
            return dateEcheance;
        }

        public void setDateEcheance(String dateEcheance) {
            this.dateEcheance = dateEcheance;
        }

        public String getMontantPrincipal() {
            return montantPrincipal;
        }

        public void setMontantPrincipal(String montantPrincipal) {
            this.montantPrincipal = montantPrincipal;
        }

        public String getMajorations() {
            return majorations;
        }

        public void setMajorations(String majorations) {
            this.majorations = majorations;
        }

        public String getMontantTotal() {
            return montantTotal;
        }

        public void setMontantTotal(String montantTotal) {
            this.montantTotal = montantTotal;
        }

        public String getDateDebut() {
            return dateDebut;
        }

        public void setDateDebut(String dateDebut) {
            this.dateDebut = dateDebut;
        }

        public String getDateFin() {
            return dateFin;
        }

        public void setDateFin(String dateFin) {
            this.dateFin = dateFin;
        }

        public String getDette() {
            return dette;
        }

        public void setDette(String dette) {
            this.dette = dette;
        }

        public String getMontantPaye() {
            return montantPaye;
        }

        public void setMontantPaye(String montantPaye) {
            this.montantPaye = montantPaye;
        }

        public String getPenalite() {
            return penalite;
        }

        public void setPenalite(String penalite) {
            this.penalite = penalite;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public FactureImpayee() {
        }

        public FactureImpayee(FACTURESIMPAYEESSERVICE.Output old, String status) {
            this.numeroFacture = old.getNumeroFacture();
            this.typeFacture = old.getTypeFacture();
            this.dateEcheance = old.getDateEcheance();
            this.montantPrincipal = old.getMontantPrincipal();
            this.majorations = old.getMajorations();
            this.montantTotal = old.getMontantTotal();
            this.dateDebut = old.getDateDebut();
            this.dateFin = old.getDateFin();
            this.dette = old.getDette();
            this.montantPaye = old.getMontantPaye();
            this.penalite = old.getPenalite();
            this.status = status;
        }
    }

}
