package web.core.dao;

import web.core.data.dao.GenericDao;
import web.core.persistence.entity.UserEntity;

public interface UserDao extends GenericDao<Integer, UserEntity> {
    Object[] checkLogin(String name, String password);
}
