package com.secusociale.portail.service.sms;

import com.secusociale.portail.config.Constants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class SmsServiceImpl implements SmsService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void sendSms(String message, String destinataire) throws Exception {
        Map<String,Object> data = new HashMap<>();
        data.put("from","IPRES-CSS");
        data.put("to", destinataire);
        data.put("text", message);
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(data);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic Y2Rpb3A6SXByZXNAMjAyMA==");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map> entity = new HttpEntity<>(data,headers);
        String url = "https://api.freebusiness.sn/sms/1/text/single";
        String response = restTemplate.postForObject(url, entity, String.class);
        System.out.println("Response: " + response);
    }

    @Override
    public String formatPhoneNumber(String numberPhone) {
        if (numberPhone == null) {
            return null;
        }

        numberPhone = numberPhone.trim();
        int length = numberPhone.length();

        if (length < 9) {
            return null;
        } else if (length > 9) {
            numberPhone = numberPhone.substring(length - 9);
        }

        return Constants.INDICATIF_DE_TELEPHONE + numberPhone;
    }
}
