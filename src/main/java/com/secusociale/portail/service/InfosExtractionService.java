package com.secusociale.portail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InfosExtractionService {

    private final Logger log = LoggerFactory.getLogger(InfosExtractionService.class);

    public boolean isObject(Object json) {
        if (json instanceof JSONObject)
            return true;
        else {
            if (json instanceof String) {
                try {
                    new JSONObject((String) json);
                } catch (JSONException e) {
                    return false;
                }
                return true;
            }
        }

        return false;
    }
}
