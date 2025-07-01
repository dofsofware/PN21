package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.*;
import com.secusociale.portail.service.dto.FactureDNSDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FactureDNS} and its DTO {@link FactureDNSDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FactureDNSMapper extends EntityMapper<FactureDNSDTO, FactureDNS> {

    FactureDNSDTO toDto(FactureDNS factureDNS);

    FactureDNS toEntity(FactureDNSDTO dto);

    default FactureDNS fromId(Long id) {
        if (id == null) {
            return null;
        }
        FactureDNS factureDNS = new FactureDNS();
        factureDNS.setId(id);
        return factureDNS;
    }
}
