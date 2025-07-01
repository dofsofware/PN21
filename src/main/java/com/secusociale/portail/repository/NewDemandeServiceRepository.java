package com.secusociale.portail.repository;

import com.secusociale.portail.domain.DemandeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the DemandeService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NewDemandeServiceRepository extends JpaRepository<DemandeService, Long>, JpaSpecificationExecutor<DemandeService> {

    List<DemandeService> findAllByEmployeur(Long iDemployeur);

    List<DemandeService> findAllByEmployeurAndTypeDemande(Long iDemployeur, String typeDemande);

    List<DemandeService> findAllByTypeDemande(String typeDemande);

    List<DemandeService> findAllByTypeDemandeIn(List<String> typeDemandes);

    List<DemandeService> findAllByEmployeurAndTypeDemandeAndStatut(Long iDemployeur, String typeDemande, String statut);

    List<DemandeService> findAllByIdDossierAndTypeDemande(String idDossier, String typeDemande);

    List<DemandeService> findAllByIdDossierAndTypeDemandeAndStatut(String idDossier, String typeDemande, String statut);

    List<DemandeService> findAllByIdDossierAndTypeDemandeAndStatutIn(String idDossier, String typeDemande, List<String> statuses);

    List<DemandeService> findAllByIdDossier(String idDossier);

    List<DemandeService> findAllByIdDossierAndStatut(String idDossier, String statut);
}
