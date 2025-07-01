package com.secusociale.portail.repository;

import com.secusociale.portail.domain.DeclaredEmployer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the DeclaredEmployer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeclaredEmployerRepository extends JpaRepository<DeclaredEmployer, Long> {
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM DeclaredEmployer e " +
        "WHERE " +
        "(:searchType = 'NINEA' AND e.ninea = :keyWanted) OR " +
        "(:searchType = 'NUMERO_UNIQUE' AND e.numeroUnique = :keyWanted) OR " +
        "(:searchType = 'ANCIEN_NUM_IPRES' AND e.ancienNumIpres = :keyWanted) OR " +
        "(:searchType = 'ANCIEN_NUM_CSS' AND e.ancienNumCss = :keyWanted)")
    boolean existsBySearchCriteria(@Param("searchType") String searchType, @Param("keyWanted") String keyWanted);

    @Query("SELECT e FROM DeclaredEmployer e " +
        "WHERE " +
        "(:searchType = 'NINEA' AND e.ninea = :keyWanted) OR " +
        "(:searchType = 'NUMERO_UNIQUE' AND e.numeroUnique = :keyWanted) OR " +
        "(:searchType = 'ANCIEN_NUM_IPRES' AND e.ancienNumIpres = :keyWanted) OR " +
        "(:searchType = 'ANCIEN_NUM_CSS' AND e.ancienNumCss = :keyWanted)")
    Optional<DeclaredEmployer> findBySearchCriteria(@Param("searchType") String searchType, @Param("keyWanted") String keyWanted);

    @Query("SELECT e FROM DeclaredEmployer e WHERE e.raisonSociale LIKE %:keyWanted%")
    List<DeclaredEmployer> findByRaisonSocialeLike(@Param("keyWanted") String keyWanted);

    boolean existsByNinea(String ninea);
    boolean existsByNumeroUnique(String numeroUnique);

    Page<DeclaredEmployer> findByNumeroUniqueParent(String numeroUnique, Pageable pageable);


}
