package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.*;
import com.secusociale.portail.service.dto.SalarieDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Salarie} and its DTO {@link SalarieDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SalarieMapper extends EntityMapper<SalarieDTO, Salarie> {



    default Salarie fromId(Long id) {
        if (id == null) {
            return null;
        }
        Salarie salarie = new Salarie();
        salarie.setId(id);
        return salarie;
    }
}
