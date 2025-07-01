package com.secusociale.portail.repository;

import com.secusociale.portail.domain.NouvelleImmatriculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the NouvelleImmatriculation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NouvelleImmatriculationRepository extends JpaRepository<NouvelleImmatriculation, Long>, JpaSpecificationExecutor<NouvelleImmatriculation> {

    List<NouvelleImmatriculation> findAllByUser(Long user);

    List<NouvelleImmatriculation> findAllByIsSubmitIsTrueAndNumdocIsNull();

    Optional<NouvelleImmatriculation> findByNineaAndIsSubmitAndUser(String ninea, boolean isSubmit, Long user);

    Optional<NouvelleImmatriculation> findByNinea(String ninea);

    @Query("select immat from NouvelleImmatriculation immat where immat.numeroUnique= ?1")
    List<NouvelleImmatriculation> findAllByNumeroUnique(@Param("1") String numeroUnique);

    Optional<NouvelleImmatriculation> findByNumeroUnique(String numeroUnique);

    List<NouvelleImmatriculation> findByNineaAndIsSubmit(String ninea, boolean isSubmit);

    List<NouvelleImmatriculation> findByNineaAndTypeAndIsSubmit(String ninea, String type, boolean isSubmit);

    List<NouvelleImmatriculation> findAllByUserAndIsSubmit(Long user, boolean isSubmit);

    List<NouvelleImmatriculation> findAllByUserAndTypeAndIsSubmit(Long user, String type, boolean isSubmit);

    List<NouvelleImmatriculation> findAllByNumdocNotNull();

    List<NouvelleImmatriculation> findAllByStatusdocAndTypeIdentifiantNull(String statusdoc);

    List<NouvelleImmatriculation> findAllByStatusdocAndNumeroUniqueNull(String statusdoc);

    List<NouvelleImmatriculation> findAllById(Long id);

    List<NouvelleImmatriculation> findAllByIsSubmitIsTrueAndNumdocIsNullAndStatusdocEquals(String statusdoc);

    @Query("SELECT n FROM NouvelleImmatriculation n WHERE n.numdoc IS NOT NULL AND n.statusdoc != :statusdoc")
    List<NouvelleImmatriculation> findAllByNumdocNotNullAndStatusdocNotIESV(@Param("statusdoc") String statusdoc);

    @Modifying
    @Query("update NouvelleImmatriculation set numeroUnique=:param where id = :id")
    void updateUnikNumber(@Param("id") Long id, @Param("param") String param);

    @Modifying
    @Query("update NouvelleImmatriculation set numeroUnique=:param1, typeIdentifiant=:param2 where id = :id")
    void updateUnikNumberAndTypeId(@Param("id") Long id, @Param("param1") String param1, @Param("param2") String param2);

    @Modifying
    @Query("update NouvelleImmatriculation set tauxAt=:param where id = :id")
    void updateTauxAt(@Param("id") Long id, @Param("param") String param);

    @Modifying
    @Query("update NouvelleImmatriculation set agenceIpres=:param1,agenceCss=:param2 where id = :id")
    void updateAgenceIpresAndCss(@Param("id") Long id, @Param("param1") String param1, @Param("param2") String param2);

    @Query("SELECT ni FROM NouvelleImmatriculation ni WHERE ni.manager = :id OR ni.user = :id")
    List<NouvelleImmatriculation> findAllByUserIdOrManagerId(@Param("id") Long id);

    @Modifying
    @Query("update NouvelleImmatriculation set manager = :managerId where user = :employeeId")
    void updateManager(@Param("managerId") Long managerId, @Param("employeeId") Long employeeId);

    @Query("SELECT n FROM NouvelleImmatriculation n WHERE n.statusdoc = 'IESV'")
    List<NouvelleImmatriculation> findByStatusIESV();

    @Query("SELECT n FROM NouvelleImmatriculation n WHERE n.statusdoc = 'IESV' AND n.numeroUnique = :numeroUnique")
    Optional<NouvelleImmatriculation> findByStatusIESVAndNumeroUnique(@Param("numeroUnique") String numeroUnique);

    long countByCreatedAtBetween(Instant from, Instant to);
    long countByStatusdocAndCreatedAtBetween(String statusdoc, Instant from, Instant to);
    long countByStatusdocNotInAndCreatedAtBetween(List<String> statusdoc, Instant from, Instant to);

}
