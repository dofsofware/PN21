package com.secusociale.portail.repository;

import com.secusociale.portail.domain.DemandeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the DemandeService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DemandeServiceRepository extends JpaRepository<DemandeService, Long> {

    List<DemandeService> findAllByEmployeur(Long iDemployeur);

    List<DemandeService> findAllByEmployeurAndTypeDemande(Long iDemployeur, String typeDemande);

    List<DemandeService> findAllByTypeDemande(String typeDemande);

    List<DemandeService> findAllByTypeDemandeIn(List<String> typeDemandes);

    List<DemandeService> findAllByIdDossierNotNullAndStatutNot(String statut);

    List<DemandeService> findAllByIdDossierIsNull();

    Optional<DemandeService> findByIdAndIdDossierIsNull(Long id);

    List<DemandeService> findAllByEmployeurAndTypeDemandeAndStatut(Long iDemployeur, String typeDemande, String statut);

    List<DemandeService> findAllByIdDossierAndTypeDemande(String idDossier, String typeDemande);

    List<DemandeService> findAllByIdDossierAndTypeDemandeAndStatut(String idDossier, String typeDemande, String statut);

    List<DemandeService> findAllByIdDossierAndTypeDemandeAndStatutIn(String idDossier, String typeDemande, List<String> statuses);

    List<DemandeService> findAllByNumeroUniqueAndTypeDemandeAndStatutIn(String idDossier, String typeDemande, List<String> statuses);

    Page<DemandeService> findAllByNumeroUnique(String numeroUnique, Pageable pageable);

    List<DemandeService> findAllByIdDossier(String idDossier);

    List<DemandeService> findAllByIdDossierAndStatut(String idDossier, String statut);

    long countByCreatedAtBetween(Instant from, Instant to);
    long countByStatutAndCreatedAtBetween(String statut, Instant from, Instant to);
    long countByStatutContainingAndCreatedAtBetween(String statut, Instant from, Instant to);
    long countByStatutNotInAndCreatedAtBetween(List<String> statut, Instant from, Instant to);
}
