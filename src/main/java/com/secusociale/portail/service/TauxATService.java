package com.secusociale.portail.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.secusociale.portail.domain.Taux_AT;
import com.secusociale.portail.repository.Taux_ATRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;

@Service
public class TauxATService {

    @Autowired
    private Taux_ATRepository tauxATRepository;

    public void initializeTauxAT() {
        if (tauxATRepository.count() == 0) {
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config/liquibase/data/taux_at.csv");
                 Reader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                CsvToBean<Taux_AT> csvToBean = new CsvToBeanBuilder<Taux_AT>(reader)
                    .withType(Taux_AT.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

                List<Taux_AT> tauxATList = csvToBean.parse();
                tauxATRepository.saveAll(tauxATList);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Taux_AT searchMostRelevantTauxAT(String searchValue) {
        if (searchValue == null || searchValue.trim().isEmpty()) {
            return null;
        }

        String searchLower = searchValue.toLowerCase().trim();
        List<Taux_AT> allTauxAT = tauxATRepository.findAll();

        return allTauxAT.stream()
            .filter(tauxAT -> tauxAT.getSecteurActivite() != null || tauxAT.getActivitePrincipale() != null)
            .map(tauxAT -> {
                int relevanceScore = calculateRelevanceScore(tauxAT, searchLower);
                return new RelevanceResult(tauxAT, relevanceScore);
            })
            .max(Comparator.comparingInt(RelevanceResult::getScore))
            .filter(result -> result.getScore() > 0)
            .map(RelevanceResult::getTauxAT)
            .orElse(null);
    }

    private int calculateRelevanceScore(Taux_AT tauxAT, String searchValue) {
        int score = 0;

        if (tauxAT.getSecteurActivite() != null) {
            String secteurLower = tauxAT.getSecteurActivite().toLowerCase();
            if (secteurLower.equals(searchValue)) {
                score += 100;
            } else if (secteurLower.contains(searchValue)) {
                score += 50;
            }
        }

        if (tauxAT.getActivitePrincipale() != null) {
            String activiteLower = tauxAT.getActivitePrincipale().toLowerCase();
            if (activiteLower.equals(searchValue)) {
                score += 75;
            } else if (activiteLower.contains(searchValue)) {
                score += 25;
            }
        }

        return score;
    }

    private static class RelevanceResult {
        private final Taux_AT tauxAT;
        private final int score;

        public RelevanceResult(Taux_AT tauxAT, int score) {
            this.tauxAT = tauxAT;
            this.score = score;
        }

        public Taux_AT getTauxAT() {
            return tauxAT;
        }

        public int getScore() {
            return score;
        }
    }
}
