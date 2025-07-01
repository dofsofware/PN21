package com.secusociale.portail.repository;

import com.secusociale.portail.domain.DeclarationManquante;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the DeclarationManquante entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeclarationManquanteRepository extends JpaRepository<DeclarationManquante, Long> {
    List<DeclarationManquante> findAllByUserId(Long userId);
}
