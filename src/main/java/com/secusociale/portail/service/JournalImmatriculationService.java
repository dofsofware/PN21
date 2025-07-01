package com.secusociale.portail.service;

import com.secusociale.portail.domain.JournalImmatriculation;
import com.secusociale.portail.domain.JournalImmatriculationDTO;
import com.secusociale.portail.domain.NouvelleImmatriculation;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.repository.JournalImmatriculationRepository;
import com.secusociale.portail.service.dto.NouvelleImmatriculationDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class JournalImmatriculationService {

    private final JournalImmatriculationRepository journalImmatriculationRepository;
    private final UserService userService;

    public JournalImmatriculationService(JournalImmatriculationRepository journalImmatriculationRepository,
                                         @Lazy UserService userService) {
        this.journalImmatriculationRepository = journalImmatriculationRepository;
        this.userService = userService;
    }

    public void journaliserAction(NouvelleImmatriculation immatriculation, String detail, String typeJournal) {
        User currentUser = userService.getUserWithAuthorities()
            .orElseThrow(() -> new RuntimeException("Utilisateur non connecté"));

        JournalImmatriculation journal = new JournalImmatriculation();
        journal.setDateAction(Instant.now());
        journal.setDetail(detail);
        journal.setTypeJournal(typeJournal);
        journal.setUtilisateurId(currentUser.getId());
        journal.setUtilisateurLogin(currentUser.getLogin());
        journal.setImmatriculation(immatriculation);

        journalImmatriculationRepository.save(journal);
    }

    @Transactional(readOnly = true)
    public List<JournalImmatriculationDTO> getHistoriqueImmatriculation(Long immatriculationId) {
        return journalImmatriculationRepository.findByImmatriculation_IdOrderByDateActionDesc(immatriculationId)
            .stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    private JournalImmatriculationDTO toDto(JournalImmatriculation journal) {
        JournalImmatriculationDTO dto = new JournalImmatriculationDTO();
        dto.setId(journal.getId());
        dto.setDateAction(journal.getDateAction());
        dto.setDetail(journal.getDetail());
        dto.setTypeJournal(journal.getTypeJournal());
        dto.setUtilisateurId(journal.getUtilisateurId());
        dto.setUtilisateurLogin(journal.getUtilisateurLogin());
        dto.setImmatriculationId(journal.getImmatriculation().getId());
        dto.setImmatriculationNumero(journal.getImmatriculation().getNumeroUnique());
        return dto;
    }
}
