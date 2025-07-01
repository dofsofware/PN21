package com.secusociale.portail.config;

import com.secusociale.portail.domain.Declaration;
import com.secusociale.portail.domain.DnsExceptionHandler;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.service.DnsExceptionHandlerService;
import com.secusociale.portail.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;


public class CodeErreurSystem {

    private static final Logger logger = LoggerFactory.getLogger(CodeErreurSystem.class);

    private final DnsExceptionHandlerService dnsExceptionHandlerService;

    // Codes d'erreur liés aux déclarations
    public static final String SAVING_DECLARATION = "DEC001";
    public static final String INVALID_DECLARATION = "DEC002";

    // Codes d'erreur liés aux factures
    public static final String GENERATION_FACTURE = "FAC001";

    // Codes d'erreur liés au temps de présence
    public static final String ADDING_TEMPS_DE_PRESENCE = "TDP001";

    public CodeErreurSystem(DnsExceptionHandlerService dnsExceptionHandlerService) {
        this.dnsExceptionHandlerService = dnsExceptionHandlerService;
    }

    public static HashMap<String, Object>  buildErrorMessage(String code) {
        String message = "Une erreur est survenue, merci de réessayer plus tard";

        HashMap<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("code", "400");
        errorResponse.put("code Erreur ", code);
        errorResponse.put("message", message);

        return errorResponse;
    }

    public Exception handleException(String code, Exception originalException, User user, Declaration declaration,MailService mailService, SpringTemplateEngine templateEngine) {
        LocalDateTime now = LocalDateTime.now();
        String exception = originalException.getMessage();
        String StackTrace = Arrays.toString(originalException.getStackTrace());
        DnsExceptionHandler dnsExceptionHandler = new DnsExceptionHandler(user,exception+StackTrace, now,code,declaration.getRaisonSociale());
        dnsExceptionHandlerService.save(dnsExceptionHandler);
        logger.error("Erreur " + code + " - Exception attrapee: " + originalException.getMessage(), originalException);
        Context context = new Context();
        context.setVariable("userPrenom", user.getFirstName());
        context.setVariable("userNom",user.getLastName());
        context.setVariable("raisonSocial",declaration.getRaisonSociale());
        context.setVariable("errorCode",code);
        context.setVariable("dateCatching",now);
        context.setVariable("numeroUnique",declaration.getNumeroUnique());
        context.setVariable("exception",exception);
        context.setVariable("StackTrace",StackTrace);
        context.setVariable("operation","DNS");
        String contenu = templateEngine.process("mail/erreurSystem", context);
        String sujet = "Erreur Système";
        if(Objects.equals(code, CodeErreurSystem.INVALID_DECLARATION)){
            sujet = "Erreur Système [URGENT]";
        }
        mailService.sendEmail(Constants.EMAILS_DEV_BACK_END,sujet,contenu,false,true);

        return new BusinessException(code, buildErrorMessage(code), originalException);
    }

    public void handleException(String code, String exception, User user, Declaration declaration,MailService mailService, SpringTemplateEngine templateEngine) {
        LocalDateTime now = LocalDateTime.now();
        DnsExceptionHandler dnsExceptionHandler = new DnsExceptionHandler(user,exception, now,code,declaration.getRaisonSociale());
        dnsExceptionHandlerService.save(dnsExceptionHandler);
        Context context = new Context();
        context.setVariable("userPrenom", user.getFirstName());
        context.setVariable("userNom",user.getLastName());
        context.setVariable("raisonSocial",declaration.getRaisonSociale());
        context.setVariable("errorCode",code);
        context.setVariable("dateCatching",now);
        context.setVariable("numeroUnique",declaration.getNumeroUnique());
        context.setVariable("exception",exception);
        context.setVariable("StackTrace","Le taux AT n'a été retrouvé ni dans la base de données du portail, ni dans celle de PSRM.");
        context.setVariable("operation","DNS");
        String contenu = templateEngine.process("mail/erreurSystem", context);
        mailService.sendEmail(Constants.EMAILS_DEV_BACK_END,"Erreur Système",contenu,false,true);
    }

    public static class BusinessException extends Exception {
        private final String errorCode;

        public BusinessException(String errorCode, String message) {
            super(message);
            this.errorCode = errorCode;
        }

        public BusinessException(String errorCode, HashMap<String, Object> message, Throwable cause) {
            super(message.toString(), cause);
            this.errorCode = errorCode;
        }

        public String getErrorCode() {
            return errorCode;
        }
    }
}
