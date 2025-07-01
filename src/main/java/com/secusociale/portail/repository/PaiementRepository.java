package com.secusociale.portail.repository;

import com.secusociale.portail.domain.Paiement;

import com.secusociale.portail.domain.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Paiement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {

    public List<Paiement> findByUserId(Long userId);

}
