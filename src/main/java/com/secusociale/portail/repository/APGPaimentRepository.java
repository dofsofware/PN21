package com.secusociale.portail.repository;

import com.secusociale.portail.domain.APGPaiment;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the APGPaiment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface APGPaimentRepository extends JpaRepository<APGPaiment, Long> {
    Optional<APGPaiment> findByReferencePaiment(String reference);

    List<APGPaiment> findAllByFacturesConcerneesContains(String numFac);

    List<APGPaiment> findAllByPersonIdAndMethodeDePaiementContains(String personId,String method);

    List<APGPaiment> findAllByPersonIdAndMethodeDePaiementLike(String personId,String method);

    List<APGPaiment> findAllByPersonIdAndMethodeDePaiementNotLike(String personId,String method);

    List<APGPaiment> findAllByReferencePaimentAndTransactionId(String requestId,String transactionId);

    List<APGPaiment> findAllByFacturesConcerneesContainsAndStatutNot(String numFac,String statut);
}
