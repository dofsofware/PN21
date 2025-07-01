package com.secusociale.portail.repository;

import com.secusociale.portail.domain.EmailNotif;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EmailNotif entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmailNotifRepository extends JpaRepository<EmailNotif, Long>, JpaSpecificationExecutor<EmailNotif> {
    EmailNotif findByGroupe(String groupe);
}
