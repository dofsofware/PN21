package com.secusociale.portail.service;

import com.opencsv.CSVWriter;
import com.secusociale.portail.config.ApplicationProperties;
import com.secusociale.portail.domain.ImmatriculationRecuperee;
import com.secusociale.portail.domain.OldImmatriculation;
import com.secusociale.portail.domain.User;
import com.secusociale.portail.repository.AgenceRepository;
import com.secusociale.portail.repository.ImmatriculationRecupereeRepository;
import com.secusociale.portail.repository.OldImmatriculationRepository;
import com.secusociale.portail.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for sending emails.
 * <p>
 * We use the {@link Async} annotation to send emails asynchronously.
 */
@Service
@RequiredArgsConstructor
public class ExportationService {

    private final Logger log = LoggerFactory.getLogger(ExportationService.class);

    @Value("${cssipres.docdir:/opt/tomcat/webapps/documents}")
    private String DOCDIR;

    private final ImmatriculationRecupereeRepository immatriculationRecupereeRepository;
    private final AgenceRepository agenceRepository;
    private final UserRepository userRepository;
    private final Environment environment;
    private final ApplicationProperties properties;

    public void extractAllActives(Long agentId) throws Exception, IOException {
        List<ImmatriculationRecuperee> all = agentId == null ? immatriculationRecupereeRepository.findAllActives() : immatriculationRecupereeRepository.findAllActivesByAgent(agentId);

        if (all.isEmpty()) {
            throw new Exception(Problem.builder().withTitle("Liste vide, aucun fichier à télécharger").build());
        }

        System.out.println("all size = " + all.size());

        List<String[]> fileContent = new ArrayList<>();

        String[] headers = new String[]{"type_identifiant", "numero_identifiant", "numero_unique", "raison_sociale", "ninea", "code_agence_css", "code_agence_ipres", "email_agent", "date"};
        fileContent.add(headers);
        List<String[]> data = all.stream().map(oi -> {
            String ac = agenceRepository.findByCode(oi.getAgenceCss()) == null ? "" : agenceRepository.findByCode(oi.getAgenceCss()).getNom();
            String ai = agenceRepository.findByCode(oi.getAgenceIpres()) == null ? "" : agenceRepository.findByCode(oi.getAgenceIpres()).getNom();
            String emailAgent = userRepository.findById(oi.getAgentId()).map(User::getEmail).orElse("");
            return new String[]{oi.getTypeIdentifiant(), oi.getNumeroIdentifiant(), oi.getNumeroUnique(), oi.getRaisonSociale(), oi.getNineaFile(), ac, ai, emailAgent, oi.getDate().toString()};
        }).collect(Collectors.toList());
        System.out.println(data);
        fileContent.addAll(data);
        try {
            File file = getFile();
            CSVWriter writer = new CSVWriter(new FileWriter(file), ',', '"', '"', "\n");
            writer.writeAll(fileContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        String docdir = environment.getProperty("cssipres.docdir") == null ? System.getProperty("cssipres.docdir") : DOCDIR;

        if (StringUtils.isEmpty(docdir)) {
            docdir = properties.getDocdir();
        }
        String nom = "myActives.csv";
        String dirdest = String.format("%s/%s", docdir, StringUtils.normalizeSpace(nom));
        File file = new File(dirdest);
//        if (file.exists()) {
//            boolean deleted = file.delete();
//            file = new File(dirdest);
//        }
        boolean b = file.getParentFile().mkdirs();// Will create parent directories if not exists
        try {
            boolean created = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("creation du fichier csv");
        return file;
    }
}
