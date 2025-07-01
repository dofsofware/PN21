package com.secusociale.portail.repository;

import com.secusociale.portail.domain.Carriere;
import com.secusociale.portail.domain.Salarie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Spring Data  repository for the Carriere entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CarriereRepository extends JpaRepository<Carriere, Long>, JpaSpecificationExecutor<Carriere> {

    Optional<Carriere> findByNumeroUniqueEmployeurAndDebutCotisationAndFinCotisationAndSalarie(String emp, LocalDate debut, LocalDate fin, Salarie salarie);
}
