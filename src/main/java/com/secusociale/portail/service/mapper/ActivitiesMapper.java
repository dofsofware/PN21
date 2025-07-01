package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.*;
import com.secusociale.portail.service.dto.ActivitiesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Activities} and its DTO {@link ActivitiesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ActivitiesMapper extends EntityMapper<ActivitiesDTO, Activities> {



    default Activities fromId(Long id) {
        if (id == null) {
            return null;
        }
        Activities activities = new Activities();
        activities.setId(id);
        return activities;
    }
}
