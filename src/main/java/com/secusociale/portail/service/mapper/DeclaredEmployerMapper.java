package com.secusociale.portail.service.mapper;
import com.secusociale.portail.domain.DeclaredEmployer;
import com.secusociale.portail.service.dto.DeclaredEmployerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeclaredEmployerMapper {
    DeclaredEmployerMapper INSTANCE = Mappers.getMapper(DeclaredEmployerMapper.class);

    DeclaredEmployerDTO toDto(DeclaredEmployer declaredEmployer);
    DeclaredEmployer toEntity(DeclaredEmployerDTO declaredEmployerDTO);
}
