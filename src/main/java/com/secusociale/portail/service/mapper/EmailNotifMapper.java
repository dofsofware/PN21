package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.*;
import com.secusociale.portail.service.dto.EmailNotifDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EmailNotif} and its DTO {@link EmailNotifDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmailNotifMapper extends EntityMapper<EmailNotifDTO, EmailNotif> {



    default EmailNotif fromId(Long id) {
        if (id == null) {
            return null;
        }
        EmailNotif emailNotif = new EmailNotif();
        emailNotif.setId(id);
        return emailNotif;
    }
}
