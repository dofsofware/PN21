package com.secusociale.portail.service.mapper;

import com.secusociale.portail.domain.JournalJob;
import com.secusociale.portail.service.dto.JournalJobDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link JournalJob} and its DTO {@link JournalJobDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface JournalJobMapper extends EntityMapper<JournalJobDTO, JournalJob> {

    @Override
    JournalJob toEntity(JournalJobDTO dto);

    @Override
    JournalJobDTO toDto(JournalJob entity);

    default JournalJob fromId(Long id) {
        if (id == null) {
            return null;
        }
        JournalJob journalJob = new JournalJob();
        journalJob.setId(id);
        return journalJob;
    }

}
