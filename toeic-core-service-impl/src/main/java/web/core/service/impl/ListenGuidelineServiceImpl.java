package web.core.service.impl;

import web.core.service.ListenGuidelineService;

public class ListenGuidelineServiceImpl implements ListenGuidelineService {

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
