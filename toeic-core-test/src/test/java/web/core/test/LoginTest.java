package web.core.test;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import web.core.dao.UserDao;
import web.core.daoimpl.UserDaoImpl;
import web.core.persistence.entity.UserEntity;

public class LoginTest {

    private final Logger log = Logger.getLogger(this.getClass());

    @Test
    public void checkIsUserExists() {
        UserDao userDao = new UserDaoImpl();
        String name = "nguyenviethoang";
        String password = "123456";
        UserEntity entity = userDao.isUserExist(name, password);
        if (entity != null) {
            log.error("Login Success");
        } else {
            log.error("Login Failed");
        }
    }

    @Test
    public void checkFindRoleByUser() {
        UserDao userDao = new UserDaoImpl();
        String name = "nguyenviethoang";
        String password = "123456";
        UserEntity entity = userDao.findRoleByUser(name, password);
        log.error(entity.getRoleEntity().getRoleId()+" - "+entity.getRoleEntity().getName());
    }
}
