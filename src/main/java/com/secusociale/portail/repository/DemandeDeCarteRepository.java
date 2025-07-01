package com.secusociale.portail.repository;

import com.secusociale.portail.domain.demandeDeCarte.DemandeDeCarteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DemandeDeCarteRepository extends JpaRepository<DemandeDeCarteEntity, Long> {

    Optional<DemandeDeCarteEntity> findByIdAndStatut(Long salarieId, String enAttente);
    Optional<DemandeDeCarteEntity> findBySalarieIdAndStatut(Long salarieId, String enAttente);
    Optional<DemandeDeCarteEntity> findFirstBySalarieIdAndStatut(Long salarieId, String enAttente);

    Page<DemandeDeCarteEntity> findByIdAgent(Long id, Pageable pageable);
    Object countByIdAgent(Long id);

    Optional<DemandeDeCarteEntity> findFirstByNinAndStatut(String nin, String enAttente);
    Optional<DemandeDeCarteEntity> findFirstByNinOrSalarieIdAndStatut(String nin, Long id, String enAttente);

    Page<DemandeDeCarteEntity> findBySalarieId(Long id, Pageable pageable);
    Object countBySalarieId(Long id);

    @Query(value = "SELECT DISTINCT d.lot FROM DemandeDeCarteEntity d " +
        "WHERE d.lot IS NOT NULL ORDER BY d.lot DESC")
    Page<String> findDistinctLots(Pageable pageable);

    @Query("SELECT d FROM DemandeDeCarteEntity d WHERE d.lot = :lot")
    List<DemandeDeCarteEntity> findByLot(@Param("lot") String lot);

    @Query("SELECT COUNT(DISTINCT d.lot) FROM DemandeDeCarteEntity d " +
        "WHERE d.lot IS NOT NULL")
    long countDistinctLot();

    @Query("SELECT d FROM DemandeDeCarteEntity d " +
        "LEFT JOIN Salarie s ON d.salarieId = s.id " +
        "LEFT JOIN Agence a ON d.agence = a.id " +
        "WHERE (:idAgent IS NULL OR d.idAgent = :idAgent) AND " +
        "(:searchTerm IS NULL OR LOWER(d.numeroDemande) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
        "LOWER(s.prenom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
        "LOWER(s.nom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
        "LOWER(d.salarieId) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
        "LOWER(s.telephone) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
        "LOWER(a.code) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
        "LOWER(d.statut) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    Page<DemandeDeCarteEntity> findByIdAgentAndSearchTerm(@Param("idAgent") Long idAgent,
                                                          @Param("searchTerm") String searchTerm,
                                                          Pageable pageable);

    // Nouvelle méthode pour compter les demandes par idAgent et searchTerm
    @Query("SELECT COUNT(d) FROM DemandeDeCarteEntity d " +
        "LEFT JOIN Salarie s ON d.salarieId = s.id " +
        "LEFT JOIN Agence a ON d.agence = a.id " +
        "WHERE (:idAgent IS NULL OR d.idAgent = :idAgent) AND " +
        "(:searchTerm IS NULL OR LOWER(d.numeroDemande) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
        "LOWER(s.prenom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
        "LOWER(s.nom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
        "LOWER(s.telephone) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
        "LOWER(a.code) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
        "LOWER(d.statut) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    long countByIdAgentAndSearchTerm(@Param("idAgent") Long idAgent,
                                     @Param("searchTerm") String searchTerm);

    @Query("SELECT d FROM DemandeDeCarteEntity d " +
        "LEFT JOIN Salarie s ON d.salarieId = s.id " +
        "WHERE d.lot = :lot AND (" +
        ":searchTerm IS NULL OR LOWER(d.numeroDemande) LIKE LOWER(:searchTerm) OR " +
        "LOWER(s.prenom) LIKE LOWER(:searchTerm) OR " +
        "LOWER(s.nom) LIKE LOWER(:searchTerm) OR " +
        "LOWER(s.telephone) LIKE LOWER(:searchTerm) OR " +
        "LOWER(d.statut) LIKE LOWER(:searchTerm))")
    List<DemandeDeCarteEntity> findByLotAndSearchTerm(@Param("lot") String lot, @Param("searchTerm") String searchTerm);

}
