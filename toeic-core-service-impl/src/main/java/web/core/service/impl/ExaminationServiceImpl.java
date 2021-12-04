package web.core.service.impl;

import web.core.dto.ExaminationDTO;
import web.core.persistence.entity.ExaminationEntity;
import web.core.service.ExaminationService;
import web.core.service.utils.SingletonDaoUtil;
import web.core.utils.ExaminationBeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExaminationServiceImpl implements ExaminationService {
    @Override
    public Object[] findExaminationByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<ExaminationDTO> result = new ArrayList<>();
        Object[] objects = SingletonDaoUtil.getExaminationDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        for(ExaminationEntity item: (List<ExaminationEntity>)objects[1]){
            ExaminationDTO dto = ExaminationBeanUtil.entity2Dto(item);
            result.add(dto);
        }
        objects[1] = result;
        return objects;
    }
}
