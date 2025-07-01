package com.secusociale.portail.repository;

import com.secusociale.portail.domain.Reclamation;
import com.secusociale.portail.domain.enumeration.StatutReclamation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {

    List<Reclamation> findByUserIdOrderByIdDesc(Long userId);

    List<Reclamation> findByUserIdAndAgenceIdOrderByIdDesc(Long userId, Long agenceId);

    List<Reclamation> findByAgenceIdOrderByIdDesc(Long agenceId);

    List<Reclamation> findByAgenceIdAndStatutOrderByIdDesc(Long agenceId, StatutReclamation statutSoumis);
    @Query("SELECT r FROM Reclamation r INNER JOIN Salarie s ON r.userId = s.userId WHERE "
        + "(:globalSearch IS NULL OR "
        + "LOWER(r.libAutresRec) LIKE LOWER(CONCAT('%', :globalSearch, '%')) OR "
        + "LOWER(r.typeReclamation) LIKE LOWER(CONCAT('%', :globalSearch, '%')) OR "
        + "LOWER(r.statut) LIKE LOWER(CONCAT('%', :globalSearch, '%')) OR "
        + "LOWER(s.prenom) LIKE LOWER(CONCAT('%', :globalSearch, '%')) OR "
        + "LOWER(s.nom) LIKE LOWER(CONCAT('%', :globalSearch, '%')) OR "
        + "LOWER(s.telephone) LIKE LOWER(CONCAT('%', :globalSearch, '%'))) "
        + "AND (:salarieId IS NULL OR s.id = :salarieId) "
        + "AND (:statut IS NULL OR r.statut = :statut) "
        + "AND (:typeDemande IS NULL OR r.typeReclamation = :typeDemande)")
    Page<Reclamation> findAllByFilters(
        @Param("globalSearch") String globalSearch,
        @Param("statut") StatutReclamation statut,
        @Param("typeDemande") String typeDemande,
        @Param("salarieId") Long salarieId,
        Pageable pageable);
}

