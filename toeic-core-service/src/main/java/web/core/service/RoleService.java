package web.core.service;

import web.core.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> findAll();
}
