package com.secusociale.portail.service.dto;

import com.secusociale.portail.domain.enumeration.StatutTP;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusUpdateRequest {
    @NotNull
    private StatutTP statut;

    private String motif;
}
