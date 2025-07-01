package com.secusociale.portail.web.rest.sms;

import com.secusociale.portail.service.sms.SmsService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/api")
@RestController
public class SmsControlleer {
    private final SmsService smsService;

    @PostMapping("/sendSms")
    public void sendSms() throws Exception {
        smsService.sendSms("test","+221771503115");
    }

}
