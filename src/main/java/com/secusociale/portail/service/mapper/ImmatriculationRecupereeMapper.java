package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.*;
import com.secusociale.portail.service.dto.ImmatriculationRecupereeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ImmatriculationRecuperee} and its DTO {@link ImmatriculationRecupereeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ImmatriculationRecupereeMapper extends EntityMapper<ImmatriculationRecupereeDTO, ImmatriculationRecuperee> {



    default ImmatriculationRecuperee fromId(Long id) {
        if (id == null) {
            return null;
        }
        ImmatriculationRecuperee immatriculationRecuperee = new ImmatriculationRecuperee();
        immatriculationRecuperee.setId(id);
        return immatriculationRecuperee;
    }
}
