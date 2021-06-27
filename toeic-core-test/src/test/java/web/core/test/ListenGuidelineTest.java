package web.core.test;

import org.testng.annotations.Test;
import web.core.dao.ListenGuidelineDao;
import web.core.dao.UserDao;
import web.core.daoimpl.ListenGuidelineDaoImpl;
import web.core.daoimpl.UserDaoImpl;

public class ListenGuidelineTest {
    @Test
    public void testFindByProperties(){
        ListenGuidelineDao listenGuidelineDao = new ListenGuidelineDaoImpl();
        Object[] result = listenGuidelineDao.findByProperty(null, null, null, null,4,2);

    }
}
