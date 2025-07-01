package com.secusociale.portail.service.mapper;


import com.secusociale.portail.domain.*;
import com.secusociale.portail.service.dto.CronSettingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CronSetting} and its DTO {@link CronSettingDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CronSettingMapper extends EntityMapper<CronSettingDTO, CronSetting> {



    default CronSetting fromId(Long id) {
        if (id == null) {
            return null;
        }
        CronSetting cronSetting = new CronSetting();
        cronSetting.setId(id);
        return cronSetting;
    }
}
