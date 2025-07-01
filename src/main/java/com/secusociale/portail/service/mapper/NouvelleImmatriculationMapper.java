package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.*;
import com.secusociale.portail.service.dto.NouvelleImmatriculationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NouvelleImmatriculation} and its DTO {@link NouvelleImmatriculationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NouvelleImmatriculationMapper extends EntityMapper<NouvelleImmatriculationDTO, NouvelleImmatriculation> {



    default NouvelleImmatriculation fromId(Long id) {
        if (id == null) {
            return null;
        }
        NouvelleImmatriculation nouvelleImmatriculation = new NouvelleImmatriculation();
        nouvelleImmatriculation.setId(id);
        return nouvelleImmatriculation;
    }
}
