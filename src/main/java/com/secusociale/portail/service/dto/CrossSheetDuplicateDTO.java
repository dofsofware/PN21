package com.secusociale.portail.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrossSheetDuplicateDTO {
    private String feuille1;
    private String occurrence1;
    private String feuille2;
    private String occurrence2;
    private Map<String, String> duplicateValues;
}
