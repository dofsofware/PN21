package com.secusociale.portail.service;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.domain.*;
import com.secusociale.portail.domain.enumeration.StatutReclamation;
import com.secusociale.portail.domain.enumeration.TypeOperation;
import com.secusociale.portail.repository.CarriereRepository;
import com.secusociale.portail.repository.ReclamationRepository;
import com.secusociale.portail.repository.SalarieRepository;
import com.secusociale.portail.service.dto.ApiResponse;
import com.secusociale.portail.service.dto.CarriereManquanteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ReclamationService {

    @Autowired
    CarriereRepository carriereRepository ;

    @Autowired
    SalarieRepository salarieRepository ;

    public boolean isCarriereMontantsValid(CarriereManquanteDTO carriere) {
        return carriere.getSalaireBrut() != null && carriere.getSalaireBrut().compareTo(0.0) >= 0
            && carriere.getCotisationCss() != null && carriere.getCotisationCss().compareTo(0.0) >= 0
            && carriere.getCotisationIpres() != null && carriere.getCotisationIpres().compareTo(0.0) >= 0;
    }

    public Optional<Reclamation> partialSave(ReclamationRepository reclamationRepository, Reclamation reclamation) {
        return reclamationRepository
            .findById(reclamation.getId())
            .map(existingReclamation -> {
                if (reclamation.getTypeReclamation() != null) {
                    existingReclamation.setTypeReclamation(reclamation.getTypeReclamation());
                }
                if (reclamation.getUserId() != null) {
                    existingReclamation.setUserId(reclamation.getUserId());
                }
                if (reclamation.getAgenceId() != null) {
                    existingReclamation.setAgenceId(reclamation.getAgenceId());
                }
                if (reclamation.getNumSalarie() != null) {
                    existingReclamation.setNumSalarie(reclamation.getNumSalarie());
                }
                if (reclamation.getSecondNumSalarie() != null) {
                    existingReclamation.setSecondNumSalarie(reclamation.getSecondNumSalarie());
                }
                if (reclamation.getLibAutresRec() != null) {
                    existingReclamation.setLibAutresRec(reclamation.getLibAutresRec());
                }
                if (reclamation.getDescriptionAutres() != null) {
                    existingReclamation.setDescriptionAutres(reclamation.getDescriptionAutres());
                }
                if (reclamation.getStatut() != null) {
                    existingReclamation.setStatut(reclamation.getStatut());
                }
                if (reclamation.getDateSoumission() != null) {
                    existingReclamation.setDateSoumission(reclamation.getDateSoumission());
                }
                if (reclamation.getDateValidateGC() != null) {
                    existingReclamation.setDateValidateGC(reclamation.getDateValidateGC());
                }
                if (reclamation.getUserIdValidateGC() != null) {
                    existingReclamation.setUserIdValidateGC(reclamation.getUserIdValidateGC());
                }
                if (reclamation.getDateValidateCFCSS() != null) {
                    existingReclamation.setDateValidateCFCSS(reclamation.getDateValidateCFCSS());
                }
                if (reclamation.getUserIdValidateCFCSS() != null) {
                    existingReclamation.setUserIdValidateCFCSS(reclamation.getUserIdValidateCFCSS());
                }
                if (reclamation.getDateValidateCFIPRES() != null) {
                    existingReclamation.setDateValidateCFIPRES(reclamation.getDateValidateCFIPRES());
                }
                if (reclamation.getUserIdValidateCFIPRES() != null) {
                    existingReclamation.setUserIdValidateCFIPRES(reclamation.getUserIdValidateCFIPRES());
                }
                if (reclamation.getMotif() != null) {
                    existingReclamation.setMotif(reclamation.getMotif());
                }
                if (reclamation.getAgenceIpresID() != null) {
                    existingReclamation.setAgenceIpresID(reclamation.getAgenceIpresID());
                }
                if (reclamation.getAgenceCssID() != null) {
                    existingReclamation.setAgenceCssID(reclamation.getAgenceCssID());
                }

                return existingReclamation;
            })
            .map(reclamationRepository::save);
    }

    public void addCarriereManquanteToCarrie(Reclamation reclamation) {
        for (CarriereManquantes carriereManquantes : reclamation.getCarriereManquantes()){
            Carriere carriere = new Carriere();
            carriere.cotisationCss(carriereManquantes.getCotisationCss());
            carriere.cotisationIpres(carriereManquantes.getCotisationIpres());
            carriere.salaireBrute(carriereManquantes.getSalaireBrut());
            carriere.createdAt(new Date().toInstant());
            carriere.debutCotisation(carriereManquantes.getDateDebut());
            carriere.finCotisation(carriereManquantes.getDateFin());
            carriere.raisonSociale(carriereManquantes.getRaisonSociale());
            if(reclamation.getUserId() !=null){
                Optional<Salarie> salarie = salarieRepository.findByUserId(reclamation.getUserId());
                salarie.ifPresent(carriere::salarie);
            }

            carriereRepository.save(carriere);
        }
    }
    public Object determineNewStatus(User user, TypeOperation typeOperation, Reclamation reclamation) {
        if (typeOperation == TypeOperation.REJETE) {
            return StatutReclamation.REJETE;
        }
        if (typeOperation == TypeOperation.RETOURNE) {
            return StatutReclamation.RETOURNE;
        }

        StatutReclamation currentStatus = reclamation.getStatut();
        switch (user.getTypeCompte()) {
            case Constants.GESTIONNAIRE_SALARIE:
                if (currentStatus == null ||
                    (!currentStatus.equals(StatutReclamation.VALIDATION_CHEF_AGENCE_CSS) &&
                        !currentStatus.equals(StatutReclamation.VALIDATION_CHEF_AGENCE_IPRES))) {
                    return StatutReclamation.VALIDATION_GESTIONNAIRE_DE_COMPTE;
                }
                return ApiResponse.error400("Cette réclamation est en attente de validation pour les prochaines étapes.");

            case Constants.CHEF_AGENCE:
                if ("CSS".equals(user.getInstitution())) {
                    if (currentStatus != null &&
                        (currentStatus.equals(StatutReclamation.VALIDATION_GESTIONNAIRE_DE_COMPTE) ||
                            currentStatus.equals(StatutReclamation.VALIDATION_CHEF_AGENCE_CSS))) {
                        return StatutReclamation.VALIDATION_CHEF_AGENCE_CSS;
                    }
                    return ApiResponse.error400("Cette réclamation doit être validée par le gestionnaire de compte au préalable.");
                } else if ("IPRES".equals(user.getInstitution())) {
                    if (currentStatus != null &&
                        currentStatus.equals(StatutReclamation.VALIDATION_CHEF_AGENCE_CSS)) {
                        return StatutReclamation.VALIDATION_CHEF_AGENCE_IPRES;
                    }
                    return ApiResponse.error400("Cette réclamation doit être validée par le Chef agence CSS au préalable.");
                }
        }
        return ApiResponse.error400("Opération non autorisée pour votre type de compte");
    }

}
