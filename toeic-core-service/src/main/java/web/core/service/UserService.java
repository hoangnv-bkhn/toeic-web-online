package web.core.service;

import javassist.tools.rmi.ObjectNotFoundException;
import web.core.dto.CheckLoginDTO;
import web.core.dto.UserDTO;
import web.core.dto.UserImportDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    UserDTO findById(Integer userId) throws ObjectNotFoundException;
    void saveUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO);
    CheckLoginDTO checkLogin(String name, String password);
    void validateImportUser(List<UserImportDTO> userImportDTOS);

    void saveUserImport(List<UserImportDTO> userImportDTOS);
}
