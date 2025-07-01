package com.secusociale.portail.repository;

import com.secusociale.portail.domain.DocumentUrl;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DocumentUrl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentUrlRepository extends JpaRepository<DocumentUrl, Long> {

}
