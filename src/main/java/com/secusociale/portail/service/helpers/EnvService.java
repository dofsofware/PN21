package com.secusociale.portail.service.helpers;


import org.springframework.beans.factory.annotation.Value;

public class EnvService {

    @Value("${app.security.sault}")
    public static String sault;

}
