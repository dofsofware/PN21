package com.secusociale.portail.service.mapper;

import com.secusociale.portail.domain.DeclarationSituationPersonnelle;
import com.secusociale.portail.service.dto.DeclarationSituationPersonnelleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeclarationSituationPersonnelleMapper extends EntityMapper<DeclarationSituationPersonnelleDTO, DeclarationSituationPersonnelle> {}

