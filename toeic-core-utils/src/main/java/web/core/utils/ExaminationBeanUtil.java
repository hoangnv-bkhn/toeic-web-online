package web.core.utils;

import web.core.dto.ExaminationDTO;
import web.core.persistence.entity.ExaminationEntity;

public class ExaminationBeanUtil {
    public static ExaminationDTO entity2Dto(ExaminationEntity entity) {
        ExaminationDTO dto = new ExaminationDTO();
        dto.setExaminationId(entity.getExaminationId());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        return dto;
    }
    public static ExaminationEntity dto2Entity(ExaminationDTO dto) {
        ExaminationEntity entity = new ExaminationEntity();
        entity.setExaminationId(dto.getExaminationId());
        entity.setName(dto.getName());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        return entity;
    }
}