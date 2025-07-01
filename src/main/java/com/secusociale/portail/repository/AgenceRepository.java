package com.secusociale.portail.repository;

import com.secusociale.portail.domain.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Agence entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AgenceRepository extends JpaRepository<Agence, Long>, JpaSpecificationExecutor<Agence> {
    Optional<Agence> findByCodeAndInstitution(String code, String institution);

    Agence findByCode(String code);

    @Query("SELECT DISTINCT a FROM Agence a WHERE " +
        "LOWER(a.code) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
        "LOWER(a.nom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
        "LOWER(a.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
        "LOWER(a.adresse) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
        "LOWER(a.institution) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Agence> findBySearchTerm(@Param("searchTerm") String searchTerm);

}
