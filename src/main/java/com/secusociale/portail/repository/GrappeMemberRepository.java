package com.secusociale.portail.repository;

import com.secusociale.portail.domain.GrappeMember;

import com.secusociale.portail.domain.Salarie;
import com.secusociale.portail.domain.enumeration.StatutGrappeMembre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the GrappeMember entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GrappeMemberRepository extends JpaRepository<GrappeMember, Long>, JpaSpecificationExecutor<GrappeMember> {
    List<GrappeMember> findAllByTypeAndSalarie(String type, Salarie salarie);

    @Modifying
    @Query("UPDATE GrappeMember gm SET gm.statut = :statutGrappeMembre WHERE gm.id = :grappeFamillialeId")
    void modifyStatus(@Param("statutGrappeMembre") StatutGrappeMembre statutGrappeMembre, @Param("grappeFamillialeId") Long grappeFamillialeId);

    @Modifying
    @Query("UPDATE GrappeMember gm SET gm.statut = :statutGrappeMembre WHERE gm.salarie.id = :salarieId")
    void modifyStatusBySalarieId(@Param("statutGrappeMembre") StatutGrappeMembre statutGrappeMembre, @Param("salarieId") Long salarieId);

    @Query("SELECT g FROM GrappeMember g WHERE g.statut = :statut")
    List<GrappeMember> getGrappeMembersByStatus(@Param("statut") StatutGrappeMembre statut);

    @Modifying
    @Query("UPDATE GrappeMember gm SET gm.statut = :statutGrappeMembre WHERE gm.salarie.id = :salarieId AND gm.statut IN (:validStatuses)")
    void updateStatusBySalarieId(
        @Param("statutGrappeMembre") StatutGrappeMembre newStatus,
        @Param("salarieId") Long salarieId,
        @Param("validStatuses") StatutGrappeMembre[] validStatuses
    );

    @Modifying
    @Query("UPDATE GrappeMember gm SET gm.statut = :newStatus WHERE gm.id = :grappeMemberId")
    int updateStatusById(@Param("newStatus") StatutGrappeMembre newStatus, @Param("grappeMemberId") Long grappeMemberId);

    @Modifying
    @Query("UPDATE GrappeMember g SET g.statut = :statut, g.motif = :motif WHERE g.salarie.id = :salarieId")
    void updateStatusAndMotifBySalarieId(
        @Param("statut") StatutGrappeMembre statut,
        @Param("motif") String motif,
        @Param("salarieId") Long salarieId
    );

    @Modifying
    @Query("UPDATE GrappeMember g SET g.statut = :statut, g.motif = :motif WHERE g.id = :grappeId")
    void updateStatusAndMotifBySId(
        @Param("statut") StatutGrappeMembre statut,
        @Param("motif") String motif,
        @Param("grappeId") Long grappeId
    );

    @Modifying
    @Query("UPDATE GrappeMember g SET g.statut = :statut WHERE g.salarie.id = :salarieId")
    void updateStatusBySalarieId(
        @Param("statut") StatutGrappeMembre statut,
        @Param("salarieId") Long salarieId
    );

    boolean existsBySalarieId(Long salarieId);

    boolean existsById(Long id);

    @Query("SELECT gm FROM GrappeMember gm WHERE (:salarieId IS NULL OR gm.salarie.id = :salarieId)")
    Page<GrappeMember> searchByStatusAndTerm(
        @Param("salarieId") Long salarieId,
        Pageable pageable
    );

}
