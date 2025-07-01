package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.*;
import com.secusociale.portail.service.dto.SuiviJobDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SuiviJob} and its DTO {@link SuiviJobDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SuiviJobMapper extends EntityMapper<SuiviJobDTO, SuiviJob> {



    default SuiviJob fromId(Long id) {
        if (id == null) {
            return null;
        }
        SuiviJob suiviJob = new SuiviJob();
        suiviJob.setId(id);
        return suiviJob;
    }
}
