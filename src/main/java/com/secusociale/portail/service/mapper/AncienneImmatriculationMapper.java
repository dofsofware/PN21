package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.OldImmatriculation;
import com.secusociale.portail.service.dto.custom.AncienneImmatriculationDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link OldImmatriculation} and its DTO {@link AncienneImmatriculationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AncienneImmatriculationMapper extends EntityMapper<AncienneImmatriculationDTO, OldImmatriculation> {

    default AncienneImmatriculationDTO fromId(Long id) {
        if (id == null) {
            return null;
        }
        AncienneImmatriculationDTO oldImmatriculation = new AncienneImmatriculationDTO();
        oldImmatriculation.setId(id);
        return oldImmatriculation;
    }
}
