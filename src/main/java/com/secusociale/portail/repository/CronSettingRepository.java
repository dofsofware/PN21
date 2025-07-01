package com.secusociale.portail.repository;

import com.secusociale.portail.domain.CronSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the CronSetting entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CronSettingRepository extends JpaRepository<CronSetting, Long>, JpaSpecificationExecutor<CronSetting> {

    Optional<CronSetting> findByCode(String code);
}
