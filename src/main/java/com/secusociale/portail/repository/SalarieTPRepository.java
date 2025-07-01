package com.secusociale.portail.repository;

import com.secusociale.portail.domain.ContenuTP;
import com.secusociale.portail.domain.SalarieTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SalarieTPRepository extends JpaRepository<SalarieTP, Long> {
    Set<SalarieTP> findAllByContenuTP(ContenuTP contenuTP);

    boolean existsByContenuTP_AnneeAndContenuTP_TrimestreAndNomAndPrenomAndTypePieceAndNumeroPiece(
        Integer annee,
        Integer trimestre,
        String nom,
        String prenom,
        String typePiece,
        String numeroPiece
    );

    Optional<SalarieTP> findByContenuTP_AnneeAndContenuTP_TrimestreAndNomAndPrenomAndTypePieceAndNumeroPiece(
        Integer annee,
        Integer trimestre,
        String nom,
        String prenom,
        String typePiece,
        Long numeroPiece
    );
}
