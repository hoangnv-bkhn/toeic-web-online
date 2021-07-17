package web.core.test;

import org.testng.annotations.Test;
import web.core.dao.RoleDao;
import web.core.daoimpl.RoleDaoImpl;
import web.core.persistence.entity.RoleEntity;

public class ImportTest {

    @Test
    public void testImportFunction() {
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity entity = roleDao.findEqualUnique("name", "USER");
        System.out.println(entity.getName());
    }
}
