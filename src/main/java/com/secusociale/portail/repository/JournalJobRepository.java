package com.secusociale.portail.repository;

import com.secusociale.portail.domain.JournalJob;
import com.secusociale.portail.domain.SuiviJob;
import com.secusociale.portail.service.dto.custom.JournalJobReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the SuiviJob entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JournalJobRepository extends JpaRepository<JournalJob, Long>, JpaSpecificationExecutor<JournalJob> {

    Optional<JournalJob> findByNom(String nom);
    @Query(value = "SELECT s.* FROM journal_job s " +
        "INNER JOIN ( " +
        "   SELECT nom, MAX(demarre_le) AS max_demarre_le " +
        "   FROM journal_job " +
        "   GROUP BY nom " +
        ") AS grouped " +
        "ON s.nom = grouped.nom AND s.demarre_le = grouped.max_demarre_le",
        nativeQuery = true)
    List<SuiviJob> findLatestJobsPerNom();
    Optional<JournalJob> findByBatchExecutionId(String batchExecutionId);

    @Query("SELECT new com.secusociale.portail.service.dto.custom.JournalJobReportDTO(" +
        "j.modeExecution, " +
        "COUNT(j), " +
        "SUM(CASE WHEN j.statut = 'COMPLETED' THEN 1 ELSE 0 END), " +
        "SUM(CASE WHEN j.statut = 'CANCELED' THEN 1 ELSE 0 END), " +
        "SUM(CASE WHEN j.statut = 'FAILED' THEN 1 ELSE 0 END), " +
        "SUM(CASE WHEN j.totals IS NOT NULL AND j.totals != '' THEN CAST(j.totals AS long) ELSE 0 END), " +
        "SUM(CASE WHEN j.valides IS NOT NULL AND j.valides != '' THEN CAST(j.valides AS long) ELSE 0 END), " +
        "SUM(CASE WHEN j.erreurs IS NOT NULL AND j.erreurs != '' THEN CAST(j.erreurs AS long) ELSE 0 END)) " +
        "FROM JournalJob j " +
        "WHERE j.nom = :jobName " +
        "AND j.demarreLe >= :startTime " +
        "GROUP BY j.modeExecution")
    List<JournalJobReportDTO> findReportDataByJobNameAndTimeRange(
        @Param("jobName") String jobName,
        @Param("startTime") Instant startTime);

    @Query("SELECT j.modeExecution, " +
        "COUNT(j), " +
        "SUM(CASE WHEN j.statut = 'COMPLETED' THEN 1 ELSE 0 END), " +
        "SUM(CASE WHEN j.statut = 'CANCELED' THEN 1 ELSE 0 END), " +
        "SUM(CASE WHEN j.statut = 'FAILED' THEN 1 ELSE 0 END), " +
        "SUM(CASE WHEN j.totals IS NOT NULL AND j.totals != '' THEN CAST(j.totals AS long) ELSE 0 END), " +
        "SUM(CASE WHEN j.valides IS NOT NULL AND j.valides != '' THEN CAST(j.valides AS long) ELSE 0 END), " +
        "SUM(CASE WHEN j.erreurs IS NOT NULL AND j.erreurs != '' THEN CAST(j.erreurs AS long) ELSE 0 END) " +
        "FROM JournalJob j " +
        "WHERE j.nom = :jobName " +
        "AND j.demarreLe >= :startTime " +
        "GROUP BY j.modeExecution")
    List<Object[]> findReportDataAsArrayByJobNameAndTimeRange(
        @Param("jobName") String jobName,
        @Param("startTime") Instant startTime);

}
