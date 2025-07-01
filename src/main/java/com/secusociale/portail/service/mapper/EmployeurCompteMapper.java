package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.*;
import com.secusociale.portail.service.dto.EmployeurCompteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EmployeurCompte} and its DTO {@link EmployeurCompteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmployeurCompteMapper extends EntityMapper<EmployeurCompteDTO, EmployeurCompte> {



    default EmployeurCompte fromId(Long id) {
        if (id == null) {
            return null;
        }
        EmployeurCompte employeurCompte = new EmployeurCompte();
        employeurCompte.setId(id);
        return employeurCompte;
    }
}
