package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.*;
import com.secusociale.portail.service.dto.CarriereDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Carriere} and its DTO {@link CarriereDTO}.
 */
@Mapper(componentModel = "spring", uses = {SalarieMapper.class})
public interface CarriereMapper extends EntityMapper<CarriereDTO, Carriere> {

    @Mapping(source = "salarie.id", target = "salarieId")
    CarriereDTO toDto(Carriere carriere);

    @Mapping(source = "salarieId", target = "salarie")
    Carriere toEntity(CarriereDTO carriereDTO);

    default Carriere fromId(Long id) {
        if (id == null) {
            return null;
        }
        Carriere carriere = new Carriere();
        carriere.setId(id);
        return carriere;
    }
}
