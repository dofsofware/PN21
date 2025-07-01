package com.secusociale.portail.service;

import com.secusociale.portail.domain.*;
import com.secusociale.portail.repository.JournalDeclarationRepository;
import com.secusociale.portail.repository.JournalImmatriculationRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class JournalDeclarationService {

    private final JournalDeclarationRepository journalDeclarationRepository;
    private final UserService userService;

    public JournalDeclarationService(
                                     JournalDeclarationRepository journalDeclarationRepository, @Lazy UserService userService) {
        this.journalDeclarationRepository = journalDeclarationRepository;
        this.userService = userService;
    }

    public JournalDeclaration journaliserAction(Long id, String detail, String typeJournal) {

        User currentUser = userService.getUserWithAuthorities()
            .orElseThrow(() -> new RuntimeException("Utilisateur non connecté"));

      //  JournalDeclaration journal = new JournalDeclaration();
        JournalDeclaration journal = new JournalDeclaration();
        journal.setDateAction(Instant.now());
        journal.setDetail(detail);
        journal.setTypeJournal(typeJournal);
        journal.setUtilisateurId(currentUser.getId());
        journal.setUtilisateurLogin(currentUser.getLogin());
        journal.setUtilisateurPrenom(currentUser.getFirstName());
        journal.setUtilisateurNom(currentUser.getLastName());
        journal.setIdDeclaration(id);

        return journalDeclarationRepository.save(journal);
    }

    @Transactional(readOnly = true)
    public List<JournalDeclaration> getAllHistoriqueDeclaration() {
        return journalDeclarationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<JournalDeclarationDTO> getHistoriqueDeclaration(Long declarationId) {
        return journalDeclarationRepository.findByIdDeclaration(declarationId)
            .stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    private JournalDeclarationDTO toDto(JournalDeclaration journal) {
        JournalDeclarationDTO dto = new JournalDeclarationDTO();
        dto.setId(journal.getId());
        dto.setDateAction(journal.getDateAction());
        dto.setDetail(journal.getDetail());
        dto.setTypeJournal(journal.getTypeJournal());
        dto.setUtilisateurId(journal.getUtilisateurId());
        dto.setUtilisateurLogin(journal.getUtilisateurLogin());
        dto.setDeclarationId(journal.getIdDeclaration());
      //  dto.setDeclarationNumero(journal.get);
        return dto;
    }
}
