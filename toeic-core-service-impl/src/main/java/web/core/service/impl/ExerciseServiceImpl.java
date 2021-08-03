package web.core.service.impl;

import web.core.dto.ExerciseDTO;
import web.core.persistence.entity.ExerciseEntity;
import web.core.service.ExerciseService;
import web.core.service.utils.SingletonDaoUtil;
import web.core.utils.ExerciseBeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExerciseServiceImpl implements ExerciseService {
    @Override
    public Object[] findExerciseByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<ExerciseDTO> result = new ArrayList<>();
        Object[] objects = SingletonDaoUtil.getExerciseDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit);
        for(ExerciseEntity item: (List<ExerciseEntity>)objects[1]){
            ExerciseDTO dto = ExerciseBeanUtil.entity2Dto(item);
            result.add(dto);
        }
        objects[1] = result;
        return objects;
    }
}
