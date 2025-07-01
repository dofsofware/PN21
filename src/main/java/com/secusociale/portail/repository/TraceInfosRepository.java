package com.secusociale.portail.repository;

import com.secusociale.portail.domain.TraceInfos;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TraceInfos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TraceInfosRepository extends JpaRepository<TraceInfos, Long> {

}
