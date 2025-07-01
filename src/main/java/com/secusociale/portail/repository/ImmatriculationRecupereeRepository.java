package com.secusociale.portail.repository;

import com.secusociale.portail.domain.ImmatriculationRecuperee;
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
 * Spring Data  repository for the ImmatriculationRecuperee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImmatriculationRecupereeRepository extends JpaRepository<ImmatriculationRecuperee, Long>, JpaSpecificationExecutor<ImmatriculationRecuperee> {

    @Query("select immat from ImmatriculationRecuperee immat where immat.status = 'ACTIVEE'")
    List<ImmatriculationRecuperee> findAllActives();

    @Query("select immat from ImmatriculationRecuperee immat where immat.status = 'ACTIVEE' and immat.agentId = :#{#agentId} ")
    List<ImmatriculationRecuperee> findAllActivesByAgent(@Param("agentId") Long agentId);

    List<ImmatriculationRecuperee> findAllByUserId(Long id);

    Optional<ImmatriculationRecuperee> findByTypeIdentifiantAndNumeroIdentifiant(String typeIdentifiant, String numeroIdentifiant);

    Optional<ImmatriculationRecuperee> findByNumeroUnique(String numeroUnique);

    @Query("select immat from ImmatriculationRecuperee immat where immat.numeroUnique= ?1")
    List<ImmatriculationRecuperee> findAllByNumeroUnique(@Param("1") String numeroUnique);

    List<ImmatriculationRecuperee> findByUserId(Long userId);

    @Query("SELECT ir FROM ImmatriculationRecuperee ir WHERE ir.manager = :id OR ir.userId = :id")
    List<ImmatriculationRecuperee> findAllByUserIdOrManagerId(@Param("id") Long id);

    @Modifying
    @Query("update ImmatriculationRecuperee set manager = :managerId where userId = :employeeId")
    void updateManager(@Param("managerId") Long managerId, @Param("employeeId") Long employeeId);

    long countByCreatedAtBetween(Instant from, Instant to);
    long countByStatusAndCreatedAtBetween(String status, Instant from, Instant to);
    long countByStatusInAndCreatedAtBetween(List<String> status, Instant from, Instant to);
}
