package web.core.dao;

import web.core.data.dao.GenericDao;
import web.core.persistence.entity.RoleEntity;

import java.util.List;

public interface RoleDao extends GenericDao<Integer, RoleEntity>{
    List<RoleEntity> findByRoles(List<String> roles);

}
