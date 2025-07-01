package com.secusociale.portail.service;

import com.secusociale.portail.domain.ContenuTP;
import com.secusociale.portail.domain.SalarieTP;
import com.secusociale.portail.domain.enumeration.StatutTP;
import com.secusociale.portail.repository.ContenuTPRepository;
import com.secusociale.portail.repository.SalarieTPRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ContenuTPService {
    private final ContenuTPRepository contenuTPRepository;
    private final SalarieTPRepository salarieTPRepository;

    public ContenuTPService(
        ContenuTPRepository contenuTPRepository,
        SalarieTPRepository salarieTPRepository
    ) {
        this.contenuTPRepository = contenuTPRepository;
        this.salarieTPRepository = salarieTPRepository;
    }

    public boolean checkTempsPresenceExists(
        Integer annee,
        Integer trimestre,
        String nom,
        String prenom,
        String typePiece,
        String numeroPiece
    ) {
        return salarieTPRepository.existsByContenuTP_AnneeAndContenuTP_TrimestreAndNomAndPrenomAndTypePieceAndNumeroPiece(
            annee, trimestre, nom, prenom, typePiece, numeroPiece
        );
    }

    public Optional<SalarieTP> findExistingSalarieTP(
        Integer annee,
        Integer trimestre,
        String nom,
        String prenom,
        String typePiece,
        Long numeroPiece
    ) {
        return salarieTPRepository.findByContenuTP_AnneeAndContenuTP_TrimestreAndNomAndPrenomAndTypePieceAndNumeroPiece(
            annee, trimestre, nom, prenom, typePiece, numeroPiece
        );
    }

    public SalarieTP updateSalarieTP(SalarieTP existingSalarieTP, SalarieTP newSalarieTP) {
        existingSalarieTP.setTempsDePresenceHeureMois1(newSalarieTP.getTempsDePresenceHeureMois1());
        existingSalarieTP.setTempsDePresenceHeureMois2(newSalarieTP.getTempsDePresenceHeureMois2());
        existingSalarieTP.setTempsDePresenceHeureMois3(newSalarieTP.getTempsDePresenceHeureMois3());
        return salarieTPRepository.save(existingSalarieTP);
    }

    @Transactional
    public ContenuTP updateStatus(Long id, StatutTP newStatus, String motif) {
        ContenuTP contenuTP = contenuTPRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("ContenuTP not found with id: " + id));

        contenuTP.setStatut(newStatus);

        if (newStatus == StatutTP.RETOURNE || newStatus == StatutTP.REJETE) {
            contenuTP.setMotif(motif);
        } else {
            contenuTP.setMotif(null);
        }

        return contenuTPRepository.save(contenuTP);
    }
}
