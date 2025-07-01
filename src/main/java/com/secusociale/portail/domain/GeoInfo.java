package com.secusociale.portail.domain;

import lombok.Data;

import javax.persistence.*;
import com.opencsv.bean.CsvBindByName;

@Data
@Entity
@Table(name = "geo_infos")
public class GeoInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CsvBindByName(column = "code_zone_IPRES")
    @Column(name = "code_zone_ipres")
    private String codeZoneIpres;

    @CsvBindByName(column = "code_sector_IPRES")
    @Column(name = "code_sector_ipres")
    private String codeSectorIpres;

    @CsvBindByName(column = "agence_IPRES")
    @Column(name = "agence_ipres")
    private String agenceIpres;

    @CsvBindByName(column = "code_zone_CSS")
    @Column(name = "code_zone_css")
    private String codeZoneCss;

    @CsvBindByName(column = "code_sector_CSS")
    @Column(name = "code_sector_css")
    private String codeSectorCss;

    @CsvBindByName(column = "agence_CSS")
    @Column(name = "agence_css")
    private String agenceCss;
}
