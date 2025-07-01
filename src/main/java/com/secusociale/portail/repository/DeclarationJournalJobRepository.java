package com.secusociale.portail.repository;

import com.secusociale.portail.domain.DeclarationJournalJob;
import com.secusociale.portail.domain.JournalJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the SuiviJob entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeclarationJournalJobRepository extends JpaRepository<DeclarationJournalJob, Long> {
     List<DeclarationJournalJob> findByJournalJobId(Long journalJobId);
     void deleteByDeclarationId(Long idDeclaration);
}

