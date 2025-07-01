package com.secusociale.portail.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.secusociale.portail.domain.GeoInfo;
import com.secusociale.portail.repository.GeoInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeoInfoService {
    @Autowired
    private GeoInfoRepository geoInfoRepository;

    public void initializeGeoInfos() {
        if (geoInfoRepository.count() == 0) {
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config/liquibase/data/geoInfos.csv");
                 Reader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                CsvToBean<GeoInfo> csvToBean = new CsvToBeanBuilder<GeoInfo>(reader)
                    .withType(GeoInfo.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
                List<GeoInfo> geoInfos = csvToBean.parse();
                geoInfoRepository.saveAll(geoInfos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public GeoInfo searchMostRelevantGeoInfo( String searchValue) {
        if (searchValue == null || searchValue.trim().isEmpty()) {
            return null;
        }
        List<GeoInfo> geoInfos = geoInfoRepository.findAll();

        List<GeoInfo> relevantGeoInfos = geoInfos.stream()
            .filter(geoInfo -> isRelevant(geoInfo, searchValue))
            .collect(Collectors.toList());

        return findMostRelevantGeoInfo(relevantGeoInfos, searchValue);
    }

    public boolean isRelevant(GeoInfo geoInfo, String searchValue) {
        String search = searchValue.toLowerCase().trim();

        return !geoInfo.getCodeZoneIpres().isEmpty()
            && !geoInfo.getCodeSectorIpres().isEmpty()
            && !geoInfo.getAgenceIpres().isEmpty()
            && !geoInfo.getCodeZoneCss().isEmpty()
            && !geoInfo.getCodeSectorCss().isEmpty()
            && !geoInfo.getAgenceCss().isEmpty()
            && (geoInfo.getCodeZoneIpres().toLowerCase().contains(search)
            || geoInfo.getCodeSectorIpres().toLowerCase().contains(search)
            || geoInfo.getAgenceIpres().toLowerCase().contains(search)
            || geoInfo.getCodeZoneCss().toLowerCase().contains(search)
            || geoInfo.getCodeSectorCss().toLowerCase().contains(search)
            || geoInfo.getAgenceCss().toLowerCase().contains(search));
    }

    public GeoInfo findMostRelevantGeoInfo(List<GeoInfo> geoInfos, String searchValue) {
        if (searchValue == null || searchValue.trim().isEmpty()) {
            return null;
        }

        String search = searchValue.toLowerCase().trim();

        Comparator<GeoInfo> matchesComparator = Comparator.comparingInt(geoInfo -> countMatches(geoInfo, search));
        Comparator<GeoInfo> exactMatchesComparator = Comparator.comparingInt(geoInfo -> countExactMatches(geoInfo, search));
        Comparator<GeoInfo> duplicatesComparator = Comparator.comparingInt(geoInfo -> countDuplicates(geoInfos, geoInfo));
        Comparator<GeoInfo> idComparator = Comparator.comparing(GeoInfo::getId);

        return geoInfos.stream()
            .filter(geoInfo -> isRelevant(geoInfo, search))
            .max(exactMatchesComparator
                .thenComparing(matchesComparator)
                .thenComparing(duplicatesComparator)
                .thenComparing(idComparator))
            .orElse(null);
    }

    public int countMatches(GeoInfo geoInfo, String searchValue) {
        int count = 0;
        String search = searchValue.toLowerCase();

        if (geoInfo.getCodeZoneIpres().toLowerCase().contains(search)) count++;
        if (geoInfo.getCodeSectorIpres().toLowerCase().contains(search)) count++;
        if (geoInfo.getAgenceIpres().toLowerCase().contains(search)) count++;
        if (geoInfo.getCodeZoneCss().toLowerCase().contains(search)) count++;
        if (geoInfo.getCodeSectorCss().toLowerCase().contains(search)) count++;
        if (geoInfo.getAgenceCss().toLowerCase().contains(search)) count++;

        return count;
    }

    public int countExactMatches(GeoInfo geoInfo, String searchValue) {
        int count = 0;
        String search = searchValue.toLowerCase();

        if (geoInfo.getCodeZoneIpres().toLowerCase().equals(search)) count++;
        if (geoInfo.getCodeSectorIpres().toLowerCase().equals(search)) count++;
        if (geoInfo.getAgenceIpres().toLowerCase().equals(search)) count++;
        if (geoInfo.getCodeZoneCss().toLowerCase().equals(search)) count++;
        if (geoInfo.getCodeSectorCss().toLowerCase().equals(search)) count++;
        if (geoInfo.getAgenceCss().toLowerCase().equals(search)) count++;

        return count;
    }

    public int countDuplicates(List<GeoInfo> geoInfos, GeoInfo geoInfo) {
        return (int) geoInfos.stream()
            .filter(gi -> gi.equals(geoInfo))
            .count();
    }
}
