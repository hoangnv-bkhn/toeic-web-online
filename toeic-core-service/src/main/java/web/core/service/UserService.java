package web.core.service;

import javassist.tools.rmi.ObjectNotFoundException;
import web.core.dto.CheckLoginDTO;
import web.core.dto.UserDTO;

import java.util.Map;

public interface UserService {
    Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    UserDTO findById(Integer userId) throws ObjectNotFoundException;
    void saveUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO);
    CheckLoginDTO checkLogin(String name, String password);
}
