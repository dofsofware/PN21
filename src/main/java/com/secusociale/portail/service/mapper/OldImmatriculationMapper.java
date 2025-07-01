package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.*;
import com.secusociale.portail.service.dto.OldImmatriculationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OldImmatriculation} and its DTO {@link OldImmatriculationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OldImmatriculationMapper extends EntityMapper<OldImmatriculationDTO, OldImmatriculation> {



    default OldImmatriculation fromId(Long id) {
        if (id == null) {
            return null;
        }
        OldImmatriculation oldImmatriculation = new OldImmatriculation();
        oldImmatriculation.setId(id);
        return oldImmatriculation;
    }
}
