package com.secusociale.portail.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDTO {
    private String status;
    private int code;
    private Map<String, Object> body;
}
