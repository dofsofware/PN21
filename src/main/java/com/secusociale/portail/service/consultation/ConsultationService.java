package com.secusociale.portail.service.consultation;

import com.secusociale.portail.domain.Declaration;
import com.secusociale.portail.domain.consultation.EmployeurConsultation;
import com.secusociale.portail.repository.DeclarationRepository;
import com.secusociale.portail.repository.consultation.ConsultationRepository;
import com.secusociale.portail.service.dto.consultation.EmployeurConsultationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final DeclarationRepository declarationRepository;

    public EmployeurConsultation save(EmployeurConsultationDTO employeurConsultationDTO) {
        EmployeurConsultation employeurConsultation = EmployeurConsultation.builder()
            .numeroUnique(employeurConsultationDTO.getNumeroUnique())
            .numeroCni(employeurConsultationDTO.getNumeroCni())
            .prenom(employeurConsultationDTO.getPrenom())
            .nom(employeurConsultationDTO.getNom())
            .telephone(employeurConsultationDTO.getTelephone())
            .email(employeurConsultationDTO.getEmail())
            .active(false)
            .userId(employeurConsultationDTO.getUserId())
            .build();

        return consultationRepository.save(employeurConsultation);
    }

    public List<EmployeurConsultation> findAllByUserId(Long userId) {
        return consultationRepository.findAllByUserId(userId);
    }

    public Optional<EmployeurConsultation> findByUserId(Long userId) {
        return consultationRepository.findByUserId(userId);
    }

    public EmployeurConsultation activate(Long id, String mode, Long activateBy) {
        Optional<EmployeurConsultation> employeurConsultation = consultationRepository.findById(id);
        if (!employeurConsultation.isPresent()) {
            throw Problem.builder()
                .withStatus(Status.UNAUTHORIZED)
                .withDetail("Employeur non trouve")
                .withTitle("Erreur")
                .build();
        }
        if (mode.equals("activation")) {
            List<Declaration> allByNumeroUnique = declarationRepository.findAllByNumeroUnique(employeurConsultation.get().getNumeroUnique());
            if (!allByNumeroUnique.isEmpty()) {
                throw Problem.builder()
                    .withStatus(Status.EXPECTATION_FAILED)
                    .withDetail("Immatriculation inexistante")
                    .withTitle("Erreur")
                    .build();
            }
            employeurConsultation.get().setActive(true);
            employeurConsultation.get().setAgentId(activateBy);
            employeurConsultation.get().setValidateAt(Instant.now());
        } else if (mode.equals("desactivation")) {
            employeurConsultation.get().setActive(false);
            employeurConsultation.get().setAgentId(null);
            employeurConsultation.get().setValidateAt(null);
        }
        return consultationRepository.save(employeurConsultation.get());
    }
}
