package com.secusociale.portail.repository.consultation;

import com.secusociale.portail.domain.consultation.EmployeurConsultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ConsultationRepository extends JpaRepository<EmployeurConsultation, Long>, JpaSpecificationExecutor<EmployeurConsultation> {

    List<EmployeurConsultation> findAllByUserId(Long userId);
   Optional<EmployeurConsultation> findByUserId(Long userId);
}
