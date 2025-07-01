package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.*;
import com.secusociale.portail.service.dto.FactureImpayeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FactureImpayee} and its DTO {@link FactureImpayeeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FactureImpayeeMapper extends EntityMapper<FactureImpayeeDTO, FactureImpayee> {



    default FactureImpayee fromId(Long id) {
        if (id == null) {
            return null;
        }
        FactureImpayee factureImpayee = new FactureImpayee();
        factureImpayee.setId(id);
        return factureImpayee;
    }
}
