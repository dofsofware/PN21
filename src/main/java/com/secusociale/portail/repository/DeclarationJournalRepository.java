package com.secusociale.portail.repository;

import com.secusociale.portail.domain.Declaration;
import com.secusociale.portail.domain.DeclarationJournal;
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
public interface DeclarationJournalRepository extends JpaRepository<DeclarationJournal, Long>, JpaSpecificationExecutor<DeclarationJournal> {

}
