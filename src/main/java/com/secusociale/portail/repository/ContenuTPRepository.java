package com.secusociale.portail.repository;

import com.secusociale.portail.domain.ContenuTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface ContenuTPRepository extends JpaRepository<ContenuTP, Long> {

    List<ContenuTP> findAllByNumeroUnique(Long numeroUnique);

    List<ContenuTP> findAllByNumeroUniqueAndAnnee(long numeroUnique, Integer annee);

    List<ContenuTP> findAllByNumeroUniqueAndAnneeAndTrimestre(long numeroUnique, Integer trimestre, Integer annee);

    List<ContenuTP> findAllByNumeroUniqueAndTrimestre(long numeroUnique, Integer trimestre);

    Page<ContenuTP> findByFileTPIsNull(Pageable pageable);

}
