package com.secusociale.portail.service.dto.extraction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class IdentifiantEmployeur {
    String typeIdentifiant;
    String numeroIdentifiant;
    String descIdentifiant;
}
