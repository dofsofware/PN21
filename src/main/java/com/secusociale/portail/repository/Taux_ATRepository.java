package com.secusociale.portail.repository;

import com.secusociale.portail.domain.Taux_AT;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Taux_ATRepository extends JpaRepository<Taux_AT, Long> {
    long count();
}
