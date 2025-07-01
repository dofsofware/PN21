package com.secusociale.portail.repository;

import com.secusociale.portail.domain.Localimmatriculation;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Localimmatriculation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocalimmatriculationRepository extends JpaRepository<Localimmatriculation, Long>, JpaSpecificationExecutor<Localimmatriculation> {

    List<Localimmatriculation> findAllByUser(Long user);

    Optional<Localimmatriculation> findByNineaAndIsSubmitAndUser(String ninea, boolean isSubmit, Long user);

    Optional<Localimmatriculation> findByNinea(String ninea);

    List<Localimmatriculation> findByNineaAndIsSubmit(String ninea, boolean isSubmit);

    List<Localimmatriculation> findByNineaAndTypeAndIsSubmit(String ninea, String type, boolean isSubmit);

    List<Localimmatriculation> findAllByUserAndIsSubmit(Long user, boolean isSubmit);

    List<Localimmatriculation> findAllByUserAndTypeAndIsSubmit(Long user, String type, boolean isSubmit);

    List<Localimmatriculation> findAllByNumdocNotNull();
    List<Localimmatriculation> findAllByStatusdocAndTypeIdentifiantNull(String statusdoc);

    List<Localimmatriculation> findAllById(Long id);

}
