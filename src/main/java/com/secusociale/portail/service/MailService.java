package com.secusociale.portail.service;

import com.secusociale.portail.domain.EmailNotif;
import com.secusociale.portail.domain.ServerCheck;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.repository.EmailNotifRepository;
import com.secusociale.portail.repository.ServerCheckRepository;
import io.github.jhipster.config.JHipsterProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service for sending emails.
 * <p>
 * We use the {@link Async} annotation to send emails asynchronously.
 */
@Service
public class MailService {

    private final Logger log = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private EmailNotifRepository emailNotifRepository;

    @Autowired
    private ServerCheckRepository serverCheckRepository;
    private static final String USER = "user";

    private static final String PASSWORD = "temp_pass";

    private static final String BASE_URL = "baseUrl";

    private static final String EXCEPTION_TEXT = "exceptionText";
    private static final String WSDL_LOCATION = "wsdlLocation";

    private static final String FRONT_URL = "frontUrl";

    private final JHipsterProperties jHipsterProperties;

    private final JavaMailSender javaMailSender;

    private final MessageSource messageSource;

    private final SpringTemplateEngine templateEngine;

    public MailService(JHipsterProperties jHipsterProperties, JavaMailSender javaMailSender,
                       MessageSource messageSource, SpringTemplateEngine templateEngine) {

        this.jHipsterProperties = jHipsterProperties;
        this.javaMailSender = javaMailSender;
        this.messageSource = messageSource;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
            isMultipart, isHtml, to, subject, content);

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
            message.setTo(to);
            message.setFrom(jHipsterProperties.getMail().getFrom());
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            log.debug("Sent email to User '{}'", to);
        } catch (MailException | MessagingException e) {
            log.warn("Email could not be sent to user '{}'", to, e);
        }
    }

    @Async
    public void sendEmail(String[] to, String subject, String content, boolean isMultipart, boolean isHtml) {
        log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
            isMultipart, isHtml, Arrays.toString(to), subject, content);

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
            message.setTo(to);
            message.setFrom(jHipsterProperties.getMail().getFrom());
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            log.debug("Sent email to Users '{}'", Arrays.toString(to));
        } catch (MailException | MessagingException e) {
            log.warn("Email could not be sent to users '{}'", Arrays.toString(to), e);
        }
    }

    @Async
    public void sendEmails(Collection<String> to, String subject, String content, boolean isMultipart, boolean isHtml) {
        log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
            isMultipart, isHtml, to, subject, content);

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            StringBuilder sb = new StringBuilder();
            for (String email : to) {
                sb.append(email);
                sb.append(",");
            }
            mimeMessage.addRecipients(Message.RecipientType.TO, sb.toString());
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
            message.setFrom(jHipsterProperties.getMail().getFrom());
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            log.debug("Sent email to User '{}'", to);
        } catch (MailException | MessagingException e) {
            log.warn("Email could not be sent to user '{}'", to, e);
        }
    }


    @Async
    public void sendEmailFromTemplate(User user, String password, String templateName, String titleKey, String activationFrontUrl, String type) {
        if (user.getEmail() == null) {
            log.debug("Email doesn't exist for user '{}'", user.getLogin());
            return;
        }
        Locale locale = Locale.forLanguageTag("FR");
        Context context = new Context(locale);
        context.setVariable(USER, user);
        String typ = "activation";
        if (type.equalsIgnoreCase("activation"))
            typ = user.getActivationKey();
        if (type.equalsIgnoreCase("reset"))
            typ = user.getResetKey();
        context.setVariable(FRONT_URL, activationFrontUrl + "/" + typ);
        if (!StringUtils.isEmpty(password))
            context.setVariable(PASSWORD, password);
        context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
        String content = templateEngine.process(templateName, context);
        String subject = messageSource.getMessage(titleKey, null, locale);
        sendEmail(user.getEmail(), subject, content, false, true);
    }

    @Async
    public void sendEmailNotif(String templateName, String groupe, String subject, Integer faultCode, String exception, String wsdlLocation) {
        ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
        if (serverCheck != null && serverCheck.getEtat().equalsIgnoreCase("DOWN")) {
            log.debug("server has down state mean we already send mails");
        } else {
            EmailNotif emailNotif = emailNotifRepository.findByGroupe(groupe);
            if (emailNotif == null) {
                log.debug("group doesn't exist '{}'", groupe);
                return;
            }
            Locale locale = Locale.forLanguageTag("FR");
            Context context = new Context(locale);
            context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
            context.setVariable(EXCEPTION_TEXT, exception);
            context.setVariable(WSDL_LOCATION, wsdlLocation);
            String content = templateEngine.process(templateName, context);
            if (faultCode != 200) {
                sendEmails(Arrays.stream(emailNotif.getEmails().split(",")).collect(Collectors.toList()), subject, content, false, true);
                if (serverCheck != null) {
                    if (serverCheck.getEtat().equalsIgnoreCase("UP")) {
                        ServerCheck sc = new ServerCheck();
                        sc.code("PCRM").etat("DOWN").date(Instant.now());
                        serverCheckRepository.save(sc);
                    }
                } else {
                    ServerCheck sc = new ServerCheck();
                    sc.code("PCRM").etat("DOWN").date(Instant.now());
                    serverCheckRepository.save(sc);
                }
                emailNotif.setLastSendDate(Instant.now());
                emailNotifRepository.save(emailNotif);
            }
        }
    }

    @Async
    public void sendEmailNotif(String templateName, String groupe, String subject, Integer faultCode, String exception) {
        ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
        if (serverCheck != null && serverCheck.getEtat().equalsIgnoreCase("DOWN")) {
            log.debug("server has down state mean we already send mails");
        } else {
            EmailNotif emailNotif = emailNotifRepository.findByGroupe(groupe);
            if (emailNotif == null) {
                log.debug("group doesn't exist '{}'", groupe);
                return;
            }
            Locale locale = Locale.forLanguageTag("FR");
            Context context = new Context(locale);
            context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
            context.setVariable(EXCEPTION_TEXT, exception);
            String content = templateEngine.process(templateName, context);
            if (faultCode != 200) {
                sendEmails(Arrays.stream(emailNotif.getEmails().split(",")).collect(Collectors.toList()), subject, content, false, true);
                if (serverCheck != null) {
                    if (serverCheck.getEtat().equalsIgnoreCase("UP")) {
                        ServerCheck sc = new ServerCheck();
                        sc.code("PCRM").etat("DOWN").date(Instant.now());
                        serverCheckRepository.save(sc);
                    }
                } else {
                    ServerCheck sc = new ServerCheck();
                    sc.code("PCRM").etat("DOWN").date(Instant.now());
                    serverCheckRepository.save(sc);
                }
                emailNotif.setLastSendDate(Instant.now());
                emailNotifRepository.save(emailNotif);
            }
        }
    }

    @Async
    public void sendEmailNotif(String templateName, String groupe, String subject, Integer faultCode) {
        ServerCheck serverCheck = serverCheckRepository.findTopByCodeOrderByIdDesc("PCRM");
        if (serverCheck != null && serverCheck.getEtat().equalsIgnoreCase("DOWN")) {
            log.debug("server has down state mean we already send mails");
        } else {
            EmailNotif emailNotif = emailNotifRepository.findByGroupe(groupe);
            if (emailNotif == null) {
                log.debug("group doesn't exist '{}'", groupe);
                return;
            }
            Locale locale = Locale.forLanguageTag("FR");
            Context context = new Context(locale);
            context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
            String content = templateEngine.process(templateName, context);
            if (faultCode != 200) {
                sendEmails(Arrays.stream(emailNotif.getEmails().split(",")).collect(Collectors.toList()), subject, content, false, true);
                if (serverCheck != null) {
                    if (serverCheck.getEtat().equalsIgnoreCase("UP")) {
                        ServerCheck sc = new ServerCheck();
                        sc.code("PCRM").etat("DOWN").date(Instant.now());
                        serverCheckRepository.save(sc);
                    }
                } else {
                    ServerCheck sc = new ServerCheck();
                    sc.code("PCRM").etat("DOWN").date(Instant.now());
                    serverCheckRepository.save(sc);
                }
                emailNotif.setLastSendDate(Instant.now());
                emailNotifRepository.save(emailNotif);
            }
        }
    }


    @Async
    public void sendActivationEmail(User user, String password, String activationFrontUrl) {
        System.out.println("send mail");
        log.debug("Sending activation email to '{}'", user.getEmail());
        System.out.println("Sending activation email to " + user.getEmail());
        sendEmailFromTemplate(user, password, "mail/activationEmail", "email.activation.title", activationFrontUrl, "activation");
    }

    @Async
    public void sendPasswordResetMail(User user, String password, String backURL) {
        System.out.println("send mail");
        log.debug("Sending activation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, password, "mail/activationEmail", "email.activation.title", backURL, "reset");
    }

    @Async
    public void sendCreationEmail(User user) {
        log.debug("Sending creation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, null, "mail/creationEmail", "email.activation.title", null, "creation");
    }

    @Async
    public void sendPasswordResetMail(User user, String backURL) {
        log.debug("Sending password reset email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, null, "mail/passwordResetEmail", "email.reset.title", backURL, "reset");
    }
}
