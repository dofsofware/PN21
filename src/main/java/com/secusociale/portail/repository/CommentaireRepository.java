package com.secusociale.portail.repository;

import com.secusociale.portail.domain.Carriere;
import com.secusociale.portail.domain.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long>, JpaSpecificationExecutor<Commentaire> {
    List<Commentaire> findByNouvelleImmatriculationId(Long nouvelleImmatriculationId);

}
