package web.core.service.impl;

import web.core.dao.ListenGuidelineDao;
import web.core.daoimpl.ListenGuidelineDaoImpl;
import web.core.dto.ListenGuidelineDTO;
import web.core.persistence.entity.ListenGuidelineEntity;
import web.core.service.ListenGuidelineService;
import web.core.utils.ListenGuidelineBeanUtil;

import java.util.ArrayList;
import java.util.List;

public class ListenGuidelineServiceImpl implements ListenGuidelineService {
    private ListenGuidelineDao listenGuidelineDao = new ListenGuidelineDaoImpl();
    @Override
    public Object[] findListenGuidelineByProperties(String property, Object value, String sortExpression, String sortDirection, Integer offset, Integer limit) {
//        List<ListenGuidelineDTO> result = new ArrayList<>();
//        Object[] objects = listenGuidelineDao.findByProperty(property, value, sortExpression, sortDirection, offset, limit);
//        for(ListenGuidelineEntity item: (List<ListenGuidelineEntity>)objects[1]){
//            ListenGuidelineDTO dto = ListenGuidelineBeanUtil.entity2Dto(item);
//            result.add(dto);
//        }
//        objects[1] = result;
        return null;
    }
}
