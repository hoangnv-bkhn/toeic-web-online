package web.core.dao;

import web.core.data.dao.GenericDao;
import web.core.persistence.entity.UserEntity;

public interface UserDao extends GenericDao<Integer, UserEntity> {
    UserEntity isUserExist(String name, String password);
    UserEntity findRoleByUser(String name, String password);
}
