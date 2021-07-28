package web.core.service.impl;

import org.hibernate.exception.ConstraintViolationException;
import web.core.dto.ListenGuidelineDTO;
import web.core.persistence.entity.ListenGuidelineEntity;
import web.core.service.ListenGuidelineService;
import web.core.service.utils.SingletonDaoUtil;
import web.core.utils.ListenGuidelineBeanUtil;

import java.sql.Timestamp;
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

    @Override
    public ListenGuidelineDTO findListenGuidelineById(String property, Integer listenGuidelineId) {
        ListenGuidelineEntity entity = SingletonDaoUtil.getListenGuideDaoInstance().findEqualUnique(property, listenGuidelineId);
        ListenGuidelineDTO dto = ListenGuidelineBeanUtil.entity2Dto(entity);
        return dto;
    }

    @Override
    public void saveListenGuideline(ListenGuidelineDTO dto) throws ConstraintViolationException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        dto.setCreatedDate(timestamp);
        ListenGuidelineEntity entity = ListenGuidelineBeanUtil.dto2Entity(dto);
        SingletonDaoUtil.getListenGuideDaoInstance().save(entity);
    }

    @Override
    public ListenGuidelineDTO updateListenGuideline(ListenGuidelineDTO dto) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        dto.setModifiedDate(timestamp);
        ListenGuidelineEntity entity = ListenGuidelineBeanUtil.dto2Entity(dto);
        entity = SingletonDaoUtil.getListenGuideDaoInstance().update(entity);
        dto = ListenGuidelineBeanUtil.entity2Dto(entity);
        return dto;
    }

    @Override
    public Integer delete(List<Integer> ids) {
        Integer result = SingletonDaoUtil.getListenGuideDaoInstance().delete(ids);
        return result;
    }
}
