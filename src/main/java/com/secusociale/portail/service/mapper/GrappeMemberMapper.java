package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.*;
import com.secusociale.portail.service.dto.GrappeMemberDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GrappeMember} and its DTO {@link GrappeMemberDTO}.
 */
@Mapper(componentModel = "spring", uses = {SalarieMapper.class})
public interface GrappeMemberMapper extends EntityMapper<GrappeMemberDTO, GrappeMember> {

    @Mapping(source = "salarie.id", target = "salarieId")
    GrappeMemberDTO toDto(GrappeMember grappeMember);

    @Mapping(source = "salarieId", target = "salarie")
    GrappeMember toEntity(GrappeMemberDTO grappeMemberDTO);

    default GrappeMember fromId(Long id) {
        if (id == null) {
            return null;
        }
        GrappeMember grappeMember = new GrappeMember();
        grappeMember.setId(id);
        return grappeMember;
    }
}
