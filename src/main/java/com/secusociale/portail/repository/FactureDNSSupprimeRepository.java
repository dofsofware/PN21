package com.secusociale.portail.repository;

import com.secusociale.portail.domain.FactureDNS;
import com.secusociale.portail.domain.FactureDNSSupprime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactureDNSSupprimeRepository extends JpaRepository<FactureDNSSupprime, Long>, JpaSpecificationExecutor<FactureDNSSupprime> {

    List<FactureDNSSupprime> findAllByIdDeclaration(Long idDeclaration);
}
