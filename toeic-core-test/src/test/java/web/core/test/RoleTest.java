package web.core.test;

import java.util.ArrayList;
import java.util.List;

import javassist.tools.rmi.ObjectNotFoundException;
import org.testng.annotations.Test;

import web.core.dao.RoleDao;
import web.core.daoimpl.RoleDaoImpl;
import web.core.persistence.entity.RoleEntity;


public class RoleTest {

    @Test
    public void checkFindAll() {
        RoleDao roleDao = new RoleDaoImpl();
        List<RoleEntity> list = roleDao.findAll();
    }

    @Test
    public void checkUpdateRole() {
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleId(2);
        roleEntity.setName("USER");
        roleDao.update(roleEntity);
    }

    @Test
    public void checkSaveRole() {
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity entity = new RoleEntity();
        entity.setRoleId(3);
        entity.setName("MANAGER");
        roleDao.save(entity);
    }

    @Test
    public void checkFindById() throws ObjectNotFoundException {
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity entity = roleDao.findById(1);
    }

    @Test
    public void checkFindByProperty(){
        RoleDao roleDao = new RoleDaoImpl();
        String property = null;
        Object value = null;
        String sortExpression = null;
        String sortDirection = null;
        Object[] objects = roleDao.findByProperty(property, value, sortExpression, sortDirection);

    }

    @Test
    public void checkDelete() {
        List<Integer> listIds = new ArrayList<>();
        listIds.add(1);
        listIds.add(2);
        RoleDao roleDao = new RoleDaoImpl();
        Integer count = roleDao.delete(listIds);
    }

}
