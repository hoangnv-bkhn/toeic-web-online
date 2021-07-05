package web.core.service.impl;

import javassist.tools.rmi.ObjectNotFoundException;
import web.core.dto.UserDTO;
import web.core.persistence.entity.UserEntity;
import web.core.service.UserService;
import web.core.service.utils.SingletonDaoUtil;
import web.core.utils.UserBeanUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    @Override
    public UserDTO isUserExist(UserDTO dto) {
        UserEntity entity = SingletonDaoUtil.getUserDaoInstance().findUserByUsernameAndPassword(dto.getName(), dto.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }

    @Override
    public UserDTO findRoleByUser(UserDTO dto) {
        UserEntity entity = SingletonDaoUtil.getUserDaoInstance().findUserByUsernameAndPassword(dto.getName(), dto.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }

    @Override
    public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getUserDaoInstance().findByProperty(property, sortDirection, sortDirection, offset, limit);
        List<UserDTO> userDTOS = new ArrayList<>();
        for (UserEntity item : (List<UserEntity>)objects[1]) {
            UserDTO userDTO = UserBeanUtil.entity2Dto(item);
            userDTOS.add(userDTO);
        }
        objects[1] = userDTOS;
        return objects;

    }

    @Override
    public UserDTO findById(Integer userId) throws ObjectNotFoundException {
        UserEntity entity = SingletonDaoUtil.getUserDaoInstance().findById(userId);
        UserDTO dto = UserBeanUtil.entity2Dto(entity);
        return dto;
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        Timestamp createdate = new Timestamp(System.currentTimeMillis());
        userDTO.setCreatedDate(createdate);
        UserEntity entity = UserBeanUtil.dto2Entity(userDTO);
        SingletonDaoUtil.getUserDaoInstance().save(entity);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        UserEntity entity = UserBeanUtil.dto2Entity(userDTO);
        entity = SingletonDaoUtil.getUserDaoInstance().update(entity);
        userDTO = UserBeanUtil.entity2Dto(entity);
        return  userDTO;
    }
}
