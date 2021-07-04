package web.core.service.impl;

import web.core.dao.RoleDao;
import web.core.daoimpl.RoleDaoImpl;
import web.core.dto.RoleDTO;
import web.core.persistence.entity.RoleEntity;
import web.core.service.RoleService;
import web.core.utils.RoleBeanUtil;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService {
    RoleDao roleDao = new RoleDaoImpl();

    @Override
    public List<RoleDTO> findAll() {
        List<RoleEntity> entities = roleDao.findAll();
        List<RoleDTO> dtos = new ArrayList<>();
        for (RoleEntity entity: entities) {
            RoleDTO dto = RoleBeanUtil.entity2Dto(entity);
            dtos.add(dto);
        }
        return dtos;
    }
}
