package com.secusociale.portail.domain;

import lombok.Data;

import java.time.Instant;

@Data
public class JournalImmatriculationDTO {
    private Long id;
    private Instant dateAction;
    private String detail;
    private String typeJournal;
    private Long utilisateurId;
    private String utilisateurLogin;
    private Long immatriculationId;
    private String immatriculationNumero;

}
