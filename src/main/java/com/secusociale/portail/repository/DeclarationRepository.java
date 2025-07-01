package com.secusociale.portail.repository;

import com.secusociale.portail.domain.Declaration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Declaration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeclarationRepository extends JpaRepository<Declaration, Long>, JpaSpecificationExecutor<Declaration> {
    Optional<Declaration> findByFileName(String fileName);

    Page<Declaration> findAllByNumeroUnique(String numeroUnique, Pageable pageable);

    Page<Declaration> findAllByNumeroUniqueIn(List<String> uniques, Pageable pageable);

    Optional<Declaration> findByNumeroUnique(String numeroUnique);

    List<Declaration> findAllByNumeroUniqueAndStatus(String numeroUnique, String status);

    @Query("select declaration from Declaration declaration where declaration.numeroUnique= ?1")
    List<Declaration> findAllByNumeroUnique(@Param("1") String numeroUnique);

    List<Declaration> findAllByTypeDeclarationAndStatus(String typeDeclaration, String status);

    List<Declaration> findAllByNumeroUniqueAndTypeDeclarationAndDebutCotisationBeforeOrderByDebutCotisationDesc(String numeroUnique, String type, Instant debut);

    List<Declaration> findAllByNumeroUniqueAndDebutCotisationBetweenOrFinCotisationBetween(String numeroUnique, Instant debut1, Instant fin1, Instant debut2, Instant fin2);

    List<Declaration> findAllByNumeroUniqueAndDebutCotisationBetween(String numeroUnique, Instant debut, Instant fin);

    List<Declaration> findAllByNumeroUniqueAndFinCotisationBetween(String numeroUnique, Instant debut, Instant fin);

    List<Declaration> findAllByNumeroUniqueAndFinCotisationLessThanEqual(String numeroUnique, Instant fin);

    List<Declaration> findAllByNumeroUniqueAndDebutCotisationGreaterThanEqual(String numeroUnique, Instant debut);

    List<Declaration> findAllByNumeroUniqueAndDebutCotisationLessThanEqualOrFinCotisationGreaterThanEqual(String numeroUnique, Instant debut, Instant fin);


    List<Declaration> findAllByCodeAgenceCSSIsNullAndCodeAgenceIPRESIsNull();

    Optional<Declaration> findByNumeroUniqueAndStatusIn(String numeroUnique, List<String> statuses);

    List<Declaration> findAllByNumeroUniqueAndStatusIn(String numeroUnique, List<String> statuses);

    List<Declaration> findAllByOwnerID(Long owner);

    List<Declaration> findAllByOwnerIDAndIsReadIsTrue(Long owner);

    List<Declaration> findAllByOwnerIDAndIsReadIsFalse(Long owner);

    List<Declaration> findAllByOwnerIDAndIsRead(Long owner, boolean read);

    boolean existsBySynthese(Long synthese);

    Long countByCreateAtBetween(Instant fromDate, Instant toDate);
    Long countByTypeDeclarationAndCreateAtBetween(String typeDeclaration, Instant fromDate, Instant toDate);
    Long countByStatusAndCreateAtBetween(String status, Instant fromDate, Instant toDate);
    Long countByStatusInAndCreateAtBetween(List<String> statuses, Instant fromDate, Instant toDate);
    Long countByIsUploadedTrueAndCreateAtBetween(Instant fromDate, Instant toDate);
    Long countByIsUploadedFalseAndCreateAtBetween(Instant fromDate, Instant toDate);

}
