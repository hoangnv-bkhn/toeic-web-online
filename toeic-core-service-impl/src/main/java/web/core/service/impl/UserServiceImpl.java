package web.core.service.impl;

import javassist.tools.rmi.ObjectNotFoundException;
import org.apache.commons.lang.StringUtils;
import web.core.dto.CheckLoginDTO;
import web.core.dto.UserDTO;
import web.core.dto.UserImportDTO;
import web.core.persistence.entity.RoleEntity;
import web.core.persistence.entity.UserEntity;
import web.core.service.UserService;
import web.core.service.utils.SingletonDaoUtil;
import web.core.utils.UserBeanUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    @Override
    public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getUserDaoInstance().findByProperty(property, sortDirection, sortDirection, offset, limit, null);
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

    @Override
    public CheckLoginDTO checkLogin(String name, String password) {
        CheckLoginDTO checkLoginDTO = new CheckLoginDTO();
        if (name != null && password != null) {
            Object[] objects = SingletonDaoUtil.getUserDaoInstance().checkLogin(name, password);
            checkLoginDTO.setUserExist((boolean) objects[0]);
            if (checkLoginDTO.isUserExist()) {
                checkLoginDTO.setRoleName(objects[1].toString());
            }
        }
        return checkLoginDTO;
    }

    @Override
    public void validateImportUser(List<UserImportDTO> userImportDTOS) {
        List<String> names = new ArrayList<>();
        List<String> roles = new ArrayList<>();

        for (UserImportDTO item : userImportDTOS) {
            if (item.isValid()) {
                names.add(item.getUserName());
                if (!roles.contains(item.getRoleName())) {
                    roles.add(item.getRoleName());
                }
            }
        }

        Map<String, UserEntity> userEntityMap = new HashMap<>();
        Map<String, RoleEntity> roleEntityMap = new HashMap<>();
        if (names.size() > 0) {
            List<UserEntity> userEntities = SingletonDaoUtil.getUserDaoInstance().findByUsers(names);
            for (UserEntity userEntity : userEntities) {
                userEntityMap.put(userEntity.getName().toUpperCase(), userEntity);
            }
        }
        if (roles.size() > 0) {
            List<RoleEntity> roleEntities = SingletonDaoUtil.getRoleDaoInstance().findByRoles(roles);
            for (RoleEntity item : roleEntities) {
                roleEntityMap.put(item.getName(), item);
            }
        }

        for(UserImportDTO item : userImportDTOS) {
            String message = item.getError();
            if (item.isValid()) {
                UserEntity userEntity = userEntityMap.get(item.getUserName().toUpperCase());
                if (userEntity != null) {
                    message += "<br/>";
                    message += "Tên đăng nhập đã tồn tại";
                }

                RoleEntity roleEntity = roleEntityMap.get(item.getRoleName().toUpperCase());
                if (roleEntity == null) {
                    message += "<br/>";
                    message += "Vai trò không tồn tại";
                }
                if (StringUtils.isNotBlank(message)) {
                    item.setValid(false);
                    item.setError(message.substring(5));
                }
            }
        }
    }

    @Override
    public void saveUserImport(List<UserImportDTO> userImportDTOS) {
        for (UserImportDTO item : userImportDTOS) {
            if (item.isValid()) {
                UserEntity userEntity = new UserEntity();
                userEntity.setName(item.getUserName());
                userEntity.setFullname(item.getFullName());
                userEntity.setPassword(item.getPassword());
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                userEntity.setCreatedDate(timestamp);
                RoleEntity roleEntity = SingletonDaoUtil.getRoleDaoInstance().findEqualUnique("name", item.getRoleName().toUpperCase());
                userEntity.setRoleEntity(roleEntity);
                SingletonDaoUtil.getUserDaoInstance().save(userEntity);
            }
        }
    }
}
