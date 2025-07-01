package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.DemandeService;
import com.secusociale.portail.service.dto.DemandeServiceDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link DemandeService} and its DTO {@link DemandeServiceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DemandeServiceMapper extends EntityMapper<DemandeServiceDTO, DemandeService> {



    default DemandeService fromId(Long id) {
        if (id == null) {
            return null;
        }
        DemandeService demandeService = new DemandeService();
        demandeService.setId(id);
        return demandeService;
    }
}
