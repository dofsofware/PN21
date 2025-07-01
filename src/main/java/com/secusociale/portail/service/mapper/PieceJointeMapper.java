package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.*;
import com.secusociale.portail.service.dto.PieceJointeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PieceJointe} and its DTO {@link PieceJointeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PieceJointeMapper extends EntityMapper<PieceJointeDTO, PieceJointe> {



    default PieceJointe fromId(Long id) {
        if (id == null) {
            return null;
        }
        PieceJointe pieceJointe = new PieceJointe();
        pieceJointe.setId(id);
        return pieceJointe;
    }
}
