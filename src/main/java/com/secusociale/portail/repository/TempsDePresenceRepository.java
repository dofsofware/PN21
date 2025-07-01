package com.secusociale.portail.repository;

import com.secusociale.portail.domain.TempsDePresence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempsDePresenceRepository extends JpaRepository<TempsDePresence, Long> {}
