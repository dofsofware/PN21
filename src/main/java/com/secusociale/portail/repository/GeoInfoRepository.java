package com.secusociale.portail.repository;

import com.secusociale.portail.domain.GeoInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoInfoRepository extends JpaRepository<GeoInfo, Long> {
    long count();
}
