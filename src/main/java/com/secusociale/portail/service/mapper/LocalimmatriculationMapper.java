package com.secusociale.portail.service.mapper;

import com.secusociale.portail.domain.Localimmatriculation;
import com.secusociale.portail.service.dto.LocalimmatriculationDTO;

/**
 * Mapper for the entity {@link Localimmatriculation} and its DTO {@link LocalimmatriculationDTO}.
 */
//@Mapper(componentModel = "spring", uses = {})
public interface LocalimmatriculationMapper /*extends EntityMapper<LocalimmatriculationDTO, Localimmatriculation>*/ {


    default Localimmatriculation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Localimmatriculation oldImmatriculation = new Localimmatriculation();
        oldImmatriculation.setId(id);
        return oldImmatriculation;
    }
}
