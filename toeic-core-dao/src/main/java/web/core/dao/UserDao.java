package web.core.dao;

import web.core.data.dao.GenericDao;
import web.core.persistence.entity.UserEntity;

import java.util.List;

public interface UserDao extends GenericDao<Integer, UserEntity> {
    Object[] checkLogin(String name, String password);
    List<UserEntity> findByUsers(List<String> names);
}
