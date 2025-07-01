package com.secusociale.portail.service.paiement;

import com.secusociale.portail.domain.APGPaiment;
import com.secusociale.portail.repository.APGPaimentRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Objects;

@Service
public class apgService {

    static APGPaiment paiment = new APGPaiment();
    private final RestTemplate restTemplate;
    private final Logger log = LoggerFactory.getLogger(apgService.class);

    private final APGPaimentRepository apgPaimentRepository;

    public apgService(RestTemplateBuilder restTemplateBuilder, APGPaimentRepository apgPaimentRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.apgPaimentRepository = apgPaimentRepository;
    }

    @Scheduled(cron = "0 */15 * ? * *")
    public void CheckStatusForUpdate() {
        apgPaimentRepository.findAll().stream().filter(apgPaiment -> apgPaiment.getStatut().equalsIgnoreCase("PENDING")).map(apgPaiment -> {
                paiment = apgPaiment;
                log.info("url: " + apgPaiment.getGetStatusUrl());
                log.info("checkPayload(apgPaiment)" + checkPayload(apgPaiment).toString());
                if(StringUtils.isEmpty(apgPaiment.getGetStatusUrl())){
                    return null;
                }
                return this.restTemplate.postForObject(apgPaiment.getGetStatusUrl(), checkPayload(apgPaiment)
                    , LinkedHashMap.class);
            }
        ).filter(Objects::nonNull).forEach(resultat -> {
            log.info(String.valueOf(resultat));
            if (!StringUtils.isEmpty(String.valueOf(resultat))) {
                if (resultat.get("code").equals("0000")) {
                    paiment.setStatut(StringUtils.isEmpty((String) resultat.get("status")) ? "SUCCESS" : (String) resultat.get("status"));
                    apgPaimentRepository.save(paiment);
                    log.info(String.valueOf(resultat));
                }
            }
        });
    }

    private CheckDTO checkPayload(APGPaiment paiment) {
        long dt = Instant.now().getEpochSecond();
        String toHash = String.format("%d%s%s", dt, paiment.getReferencePaiment(), paiment.getSecretKey());
        String auth = DigestUtils.md5Hex(toHash).toUpperCase(Locale.ROOT);
        return new CheckDTO(paiment.getTerminalNumber(), paiment.getReferencePaiment(), dt, auth);
    }
}

class CheckDTO {
    private String terminalNumber;
    private String requestId;
    private long timestamp;
    private String auth;

    public CheckDTO(String terminalNumber, String requestId, long timestamp, String auth) {
        this.terminalNumber = terminalNumber;
        this.requestId = requestId;
        this.timestamp = timestamp;
        this.auth = auth;
    }

    public CheckDTO() {
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    @Override
    public String toString() {
        return "'CheckDTO':{" +
            "terminalNumber:'" + terminalNumber + '\'' +
            ", requestId:'" + requestId + '\'' +
            ", timeStamp:" + timestamp +
            ", auth:'" + auth + '\'' +
            '}';
    }
}
