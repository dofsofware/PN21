package com.secusociale.portail.repository;

import com.secusociale.portail.domain.DemandeCertificat;
import com.secusociale.portail.domain.Salarie;
import com.secusociale.portail.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeDeCertificatRepository extends JpaRepository<DemandeCertificat, Long> {
    Page<DemandeCertificat> findByAgent(User user, Pageable pageable);

    Page<DemandeCertificat> findBySalarie(Salarie salarie, Pageable pageable);
}
