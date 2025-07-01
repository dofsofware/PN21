package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.*;
import com.secusociale.portail.service.dto.GedUpdateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GedUpdate} and its DTO {@link GedUpdateDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GedUpdateMapper extends EntityMapper<GedUpdateDTO, GedUpdate> {



    default GedUpdate fromId(Long id) {
        if (id == null) {
            return null;
        }
        GedUpdate gedUpdate = new GedUpdate();
        gedUpdate.setId(id);
        return gedUpdate;
    }
}
