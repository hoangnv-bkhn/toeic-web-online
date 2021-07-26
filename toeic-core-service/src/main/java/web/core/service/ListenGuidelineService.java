package web.core.service;

import web.core.dto.ListenGuidelineDTO;

import java.util.Map;

public interface ListenGuidelineService {
    Object[] findListenGuidelineByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    ListenGuidelineDTO findListenGuidelineById(String property, Integer listenGuidelineId);
}
