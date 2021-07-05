package web.core.service;

import javassist.tools.rmi.ObjectNotFoundException;
import web.core.dto.UserDTO;

import java.util.Map;

public interface UserService {
    UserDTO isUserExist(UserDTO dto);
    UserDTO findRoleByUser(UserDTO dto);
    Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    UserDTO findById(Integer userId) throws ObjectNotFoundException;
    void saveUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO);
}
