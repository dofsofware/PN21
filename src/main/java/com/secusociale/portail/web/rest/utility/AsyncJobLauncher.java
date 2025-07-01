package com.secusociale.portail.service;

import com.secusociale.portail.domain.enumeration.TypeJobName;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncJobLauncher {

    @Async
    public void runSendAllSynchronisationDelaration(DeclarationService declarationService, TypeJobName jobType) {
        declarationService.sendAllSynchronisationDelaration(jobType);
    }

    @Async
    public void runSendImmatriculations(NouvelleImmatriculationService immatriculationService) {
        immatriculationService.sendImmatriculationsToPCRMByPortail();
    }
}
