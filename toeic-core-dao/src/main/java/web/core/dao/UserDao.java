package web.core.dao;

import web.core.data.dao.GenericDao;
import web.core.persistence.entity.UserEntity;

public interface UserDao extends GenericDao<Integer, UserEntity> {
    UserEntity findUserByUsernameAndPassword(String name, String password);
}
