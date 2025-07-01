package com.secusociale.portail.repository;

import com.secusociale.portail.domain.DeclarationSituationPersonnelle;
import com.secusociale.portail.domain.enumeration.StatutGrappeMembre;
import com.secusociale.portail.domain.enumeration.TypeDeclarationSituationPersonnelle;
import com.secusociale.portail.service.dto.DeclarationSituationPersonnelleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeclarationSituationPersonnelleRepository extends JpaRepository<DeclarationSituationPersonnelle, Long> {
    List<DeclarationSituationPersonnelle> findByTypeDeclarationSituationPersonnelleAndUserId(TypeDeclarationSituationPersonnelle typeDeclaration, Long userId);

    @Query("SELECT d FROM DeclarationSituationPersonnelle d WHERE d.numSalarie = :salarieNum")
    Page<DeclarationSituationPersonnelle> searchById(@Param("salarieNum") Long salarieNum, Pageable pageable);


}

