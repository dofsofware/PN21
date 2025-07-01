package com.secusociale.portail.config;

import com.secusociale.portail.service.CronSettingService;
import com.secusociale.portail.service.PortailConstant;
import com.secusociale.portail.service.dto.CronSettingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * Configure the converters to use the ISO format for dates by default.
 */
@Configuration
public class CronSettingConfiguration {
    private final CronSettingService cronSettingService;
    private String defaultCron;

    private final Logger log = LoggerFactory.getLogger(CronSettingConfiguration.class);

    public CronSettingConfiguration(CronSettingService cronSettingService) {
        this.cronSettingService = cronSettingService;
    }

    @PostConstruct
    public void setDefault() {
        defaultCron = PortailConstant.DEFAULTUSEDCRON;
    }

    @Bean("carriereJobExpression")
    @Transactional
    String carriereJob() {
        String code = "carriereJob";
        CronSettingDTO cs = getSetting(code);
        if (cs == null)
            log.warn("Cannot find cron setting with code '${code}', so the defaultCron '${defaultCron}' will be used as expression value!");
        String returnedCron = cs != null ? cs.getExpression() : defaultCron;
        log.info("returnedCron {}", returnedCron);
        return returnedCron;
    }

    @Bean("sendDeclarationJobExpression")
    @Transactional
    String sendDeclarationJob() {
        String code = "sendDeclarationJob";
        CronSettingDTO cs = getSetting(code);
        if (cs == null)
            log.warn("Cannot find cron setting with code '${code}', so the defaultCron '${defaultCron}' will be used as expression value!");
        String returnedCron = cs != null ? cs.getExpression() : defaultCron;
        log.info("returnedCron {}", returnedCron);
        return returnedCron;
    }

    @Bean("sendRepportsSync")
    @Transactional
    String sendRepportsSync() {
        String code = "sendRepportsSync";
        CronSettingDTO cs = getSetting(code);
        if (cs == null)
            log.warn("Cannot find cron setting with code '${code}', so the defaultCron '${defaultCron}' will be used as expression value!");
        String returnedCron = cs != null ? cs.getExpression() : defaultCron;
        log.info("returnedCron {}", returnedCron);
        return returnedCron;
    }

    @Bean("changeImmatriculationStatusJobExpression")
    @Transactional
    String changeImmatriculationStatusJob() {
        String code = "changeImmatriculationStatusJob";
        CronSettingDTO cs = getSetting(code);
        if (cs == null)
            log.warn("Cannot find cron setting with code '${code}', so the defaultCron '${defaultCron}' will be used as expression value!");
        String returnedCron = cs != null ? cs.getExpression() : defaultCron;
        log.info("returnedCron {}", returnedCron);
        return returnedCron;
    }

    @Bean("getLocalImmatriculationUniqueNumberJobExpression")
    @Transactional
    String getLocalImmatriculationUniqueNumberJobExpression() {
        String code = "getLocalImmatriculationUniqueNumberJob";
        CronSettingDTO cs = getSetting(code);
        if (cs == null)
            log.warn("Cannot find cron setting with code '${code}', so the defaultCron '${defaultCron}' will be used as expression value!");
        String returnedCron = cs != null ? cs.getExpression() : defaultCron;
        log.info("returnedCron {}", returnedCron);
        return returnedCron;
    }

    @Bean("changeBase64ToUrlJobExpression")
    @Transactional
    String changeBase64ToUrlJob() {
        String code = "changeBase64ToUrlJob";
        CronSettingDTO cs = getSetting(code);
        if (cs == null)
            log.warn("Cannot find cron setting with code '${code}', so the defaultCron '${defaultCron}' will be used as expression value!");
        String returnedCron = cs != null ? cs.getExpression() : defaultCron;
        log.info("returnedCron {}", returnedCron);
        return returnedCron;
    }

    private CronSettingDTO getSetting(String code) {
        return this.cronSettingService.getOne(code).orElse(null);
    }

}
