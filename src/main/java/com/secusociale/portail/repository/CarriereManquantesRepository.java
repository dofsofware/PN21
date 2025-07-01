package com.secusociale.portail.repository;

import com.secusociale.portail.domain.CarriereManquantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarriereManquantesRepository extends JpaRepository<CarriereManquantes, Long> {
    List<CarriereManquantes> findByReclamationId(Long reclamationId);
}
