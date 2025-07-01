package com.secusociale.portail.repository;

import com.secusociale.portail.domain.JournalImmatriculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JournalImmatriculationRepository extends JpaRepository<JournalImmatriculation, Long> {
    List<JournalImmatriculation> findByImmatriculation_IdOrderByDateActionDesc(Long immatriculationId);
}
