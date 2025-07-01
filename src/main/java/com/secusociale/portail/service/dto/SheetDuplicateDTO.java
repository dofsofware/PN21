package com.secusociale.portail.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SheetDuplicateDTO {
    private String feuille;
    private String occurence1;
    private String occurence2;
    private Map<String, String> valeursDupliquees;
}
