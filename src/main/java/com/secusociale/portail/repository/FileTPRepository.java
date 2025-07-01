package com.secusociale.portail.repository;

import com.secusociale.portail.domain.FileTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileTPRepository extends JpaRepository<FileTP, Long> {}

