package web.core.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import web.core.dao.ListenGuidelineDao;
import web.core.daoimpl.ListenGuidelineDaoImpl;

import java.util.HashMap;
import java.util.Map;

public class ListenGuidelineTest {

    ListenGuidelineDao listenGuidelineDao;

    @BeforeTest
    public void initData() {
        listenGuidelineDao = new ListenGuidelineDaoImpl();
    }

    @Test
    public void testFindByProperties(){
//        ListenGuidelineDao listenGuidelineDao = new ListenGuidelineDaoImpl();
//        Object[] result = listenGuidelineDao.findByProperty(null, null, null, null,4,2);

    }

    @Test
    public void checkApiFindByProperty(){
        Map<String, Object> property = new HashMap<String, Object>();
        property.put("title", "Bai huong dan 8");
        property.put("content", "Noi dung bai huong dan 8");
        Object[] objects = listenGuidelineDao.findByProperty(property, null, null, null, null, null);
    }
}
