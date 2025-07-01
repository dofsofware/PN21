package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.*;
import com.secusociale.portail.service.dto.HelpersDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Helpers} and its DTO {@link HelpersDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HelpersMapper extends EntityMapper<HelpersDTO, Helpers> {



    default Helpers fromId(Long id) {
        if (id == null) {
            return null;
        }
        Helpers helpers = new Helpers();
        helpers.setId(id);
        return helpers;
    }
}
