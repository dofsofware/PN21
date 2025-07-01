package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.*;
import com.secusociale.portail.service.dto.DMTDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DMT} and its DTO {@link DMTDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DMTMapper extends EntityMapper<DMTDTO, DMT> {



    default DMT fromId(Long id) {
        if (id == null) {
            return null;
        }
        DMT dMT = new DMT();
        dMT.setId(id);
        return dMT;
    }
}
