package com.secusociale.portail.repository;

import com.secusociale.portail.domain.DeclarationSupprime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeclarationSupprimeRepository extends JpaRepository<DeclarationSupprime, Long>, JpaSpecificationExecutor<DeclarationSupprime> {
}
