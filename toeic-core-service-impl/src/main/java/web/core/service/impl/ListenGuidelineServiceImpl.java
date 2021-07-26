package web.core.service.impl;

import web.core.dto.ListenGuidelineDTO;
import web.core.persistence.entity.ListenGuidelineEntity;
import web.core.service.ListenGuidelineService;
import web.core.service.utils.SingletonDaoUtil;
import web.core.utils.ListenGuidelineBeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListenGuidelineServiceImpl implements ListenGuidelineService {

    @Override
    public Object[] findListenGuidelineByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<ListenGuidelineDTO> result = new ArrayList<>();
        Object[] objects = SingletonDaoUtil.getListenGuideDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit);
        for(ListenGuidelineEntity item: (List<ListenGuidelineEntity>)objects[1]){
            ListenGuidelineDTO dto = ListenGuidelineBeanUtil.entity2Dto(item);
            result.add(dto);
        }
        objects[1] = result;
        return objects;
    }
}
