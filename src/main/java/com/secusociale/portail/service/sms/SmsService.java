package com.secusociale.portail.service.sms;

import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.core.JsonProcessingException;

public interface SmsService {

    void sendSms(String message, String destinataire) throws Exception;
    String formatPhoneNumber(String numberPhone);
}
