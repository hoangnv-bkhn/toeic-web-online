package web.core.utils;

import web.core.dto.ExerciseDTO;
import web.core.persistence.entity.ExerciseEntity;

public class ExerciseBeanUtil {
    public static ExerciseDTO entity2Dto(ExerciseEntity entity) {
        ExerciseDTO dto = new ExerciseDTO();
        dto.setExerciseId(entity.getExerciseId());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setType(entity.getType());
        return dto;
    }

    public static ExerciseEntity dto2Entity(ExerciseDTO dto) {
        ExerciseEntity entity = new ExerciseEntity();
        entity.setExerciseId(dto.getExerciseId());
        entity.setName(dto.getName());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setType(dto.getType());
        return entity;
    }
}
