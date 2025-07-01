package com.secusociale.portail.service;

import com.secusociale.portail.config.Constants;
import com.secusociale.portail.repository.JournalJobRepository;
import com.secusociale.portail.service.dto.custom.JournalJobReportDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class JournalJobReportService {

    private final Logger log = LoggerFactory.getLogger(JournalJobReportService.class);

    private final JournalJobRepository journalJobRepository;
    private final MailService mailService;
    private final SpringTemplateEngine templateEngine;

    public JournalJobReportService(JournalJobRepository journalJobRepository,
                                   MailService mailService,
                                   SpringTemplateEngine templateEngine) {
        this.journalJobRepository = journalJobRepository;
        this.mailService = mailService;
        this.templateEngine = templateEngine;
    }

    public List<JournalJobReportDTO> generateReportData() {
        log.debug("Rapport pour sendInvoicesToPSRM");

        Instant last24Hours = Instant.now().minus(Duration.ofHours(24));

        return journalJobRepository.findReportDataByJobNameAndTimeRange("sendInvoicesToPSRM", last24Hours);
    }

    public void sendReport(String[] customRecipients) {
        try {
            List<JournalJobReportDTO> reportData = generateReportData();

            long totalJobsGlobal = reportData.stream().mapToLong(JournalJobReportDTO::getTotalJobs).sum();
            long totalGlobal = reportData.stream().mapToLong(JournalJobReportDTO::getTotalSum).sum();
            long validesGlobal = reportData.stream().mapToLong(JournalJobReportDTO::getValidesSum).sum();
            long erreursGlobal = reportData.stream().mapToLong(JournalJobReportDTO::getErreursSum).sum();
            long completedGlobal = reportData.stream().mapToLong(JournalJobReportDTO::getCompletedJobs).sum();
            long canceledGlobal = reportData.stream().mapToLong(JournalJobReportDTO::getCanceledJobs).sum();
            long failedGlobal = reportData.stream().mapToLong(JournalJobReportDTO::getFailedJobs).sum();

            Context context = new Context();
            context.setVariable("reportData", reportData);
            context.setVariable("generationDate", LocalDateTime.now());
            context.setVariable("generationDate24Avant", LocalDateTime.now().minusDays(1));
            context.setVariable("hasData", !reportData.isEmpty());
            context.setVariable("totalJobsGlobal", totalJobsGlobal);
            context.setVariable("totalGlobal", totalGlobal);
            context.setVariable("validesGlobal", validesGlobal);
            context.setVariable("erreursGlobal", erreursGlobal);
            context.setVariable("completedGlobal", completedGlobal);
            context.setVariable("canceledGlobal", canceledGlobal);
            context.setVariable("failedGlobal", failedGlobal);

            String contenu = templateEngine.process("mail/rapportJournalJob", context);

            String subject = "Rapport Journalier de synchronisation - " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            mailService.sendEmail(customRecipients, subject, contenu, false, true);

        } catch (Exception e) {
            log.error("Erreur lors de l'envoi du rapport", e);
        }
    }

    @Scheduled(cron = "#{@sendRepportsSync}")
    public void sendDailyReport() {
        log.info("Envoi du rapport quotidien JournalJob");
        sendReport(Constants.EMAILS_DAILY_REPPORTING);
    }
}
