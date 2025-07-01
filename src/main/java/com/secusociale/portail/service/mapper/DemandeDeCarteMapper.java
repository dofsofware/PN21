package com.secusociale.portail.service.mapper;

import com.secusociale.portail.domain.demandeDeCarte.DemandeDeCarteEntity;
import com.secusociale.portail.service.dto.extraction.DemandeDeCarteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface DemandeDeCarteMapper extends EntityMapper<DemandeDeCarteDTO, DemandeDeCarteEntity> {

    default DemandeDeCarteEntity fromId(Long id){
        if (id == null){
            return null;
        }

        DemandeDeCarteEntity demandeDeCarteEntity = new DemandeDeCarteEntity();
        demandeDeCarteEntity.setId(id);
        return demandeDeCarteEntity;
    }
}
