package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.Agence;
import com.secusociale.portail.service.dto.AgenceDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Agence} and its DTO {@link AgenceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AgenceMapper extends EntityMapper<AgenceDTO, Agence> {



    default Agence fromId(Long id) {
        if (id == null) {
            return null;
        }
        Agence agence = new Agence();
        agence.setId(id);
        return agence;
    }
}
