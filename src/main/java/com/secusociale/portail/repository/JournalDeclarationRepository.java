package com.secusociale.portail.repository;

import com.secusociale.portail.domain.JournalDeclaration;
import com.secusociale.portail.domain.JournalImmatriculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalDeclarationRepository extends JpaRepository<JournalDeclaration, Long> {
    List<JournalDeclaration> findByIdDeclaration(Long Id);
    void deleteByIdDeclaration(Long idDeclaration);
}
