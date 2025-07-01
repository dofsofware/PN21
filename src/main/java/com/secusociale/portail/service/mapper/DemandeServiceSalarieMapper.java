package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.*;
import com.secusociale.portail.service.dto.DemandeServiceSalarieDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DemandeServiceSalarie} and its DTO {@link DemandeServiceSalarieDTO}.
 */
@Mapper(componentModel = "spring", uses = {SalarieMapper.class})
public interface DemandeServiceSalarieMapper extends EntityMapper<DemandeServiceSalarieDTO, DemandeServiceSalarie> {

    @Mapping(source = "salarie.id", target = "salarieId")
    DemandeServiceSalarieDTO toDto(DemandeServiceSalarie demandeServiceSalarie);

    @Mapping(source = "salarieId", target = "salarie")
    DemandeServiceSalarie toEntity(DemandeServiceSalarieDTO demandeServiceSalarieDTO);

    default DemandeServiceSalarie fromId(Long id) {
        if (id == null) {
            return null;
        }
        DemandeServiceSalarie demandeServiceSalarie = new DemandeServiceSalarie();
        demandeServiceSalarie.setId(id);
        return demandeServiceSalarie;
    }
}
