package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.*;
import com.secusociale.portail.service.dto.ConfigcompteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Configcompte} and its DTO {@link ConfigcompteDTO}.
 */
@Mapper(componentModel = "spring", uses = {AgenceMapper.class})
public interface ConfigcompteMapper extends EntityMapper<ConfigcompteDTO, Configcompte> {

    @Mapping(source = "agence.id", target = "agenceId")
    @Mapping(source = "agence.nom", target = "agenceNom")
    ConfigcompteDTO toDto(Configcompte configcompte);

    @Mapping(source = "agenceId", target = "agence")
    Configcompte toEntity(ConfigcompteDTO configcompteDTO);

    default Configcompte fromId(Long id) {
        if (id == null) {
            return null;
        }
        Configcompte configcompte = new Configcompte();
        configcompte.setId(id);
        return configcompte;
    }
}
