package web.core.service.impl;

import web.core.dao.UserDao;
import web.core.daoimpl.UserDaoImpl;
import web.core.dto.UserDTO;
import web.core.persistence.entity.UserEntity;
import web.core.service.UserService;
import web.core.utils.UserBeanUtil;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public UserDTO isUserExist(UserDTO dto) {
        UserEntity entity = userDao.isUserExist(dto.getName(), dto.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }

    @Override
    public UserDTO findRoleByUser(UserDTO dto) {
        UserEntity entity = userDao.findRoleByUser(dto.getName(), dto.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }
}
