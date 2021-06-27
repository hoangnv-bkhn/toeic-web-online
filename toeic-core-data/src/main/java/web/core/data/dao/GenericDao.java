package web.core.data.dao;

import java.io.Serializable;
import java.util.List;

import javassist.tools.rmi.ObjectNotFoundException;

public interface GenericDao <ID extends Serializable, T>{

    List<T> findAll();
    T update(T entity);
    T save(T entity);
    T findById(ID id) throws ObjectNotFoundException;
    Object[] findByProperty(String property, Object value, String sortExpression, String sortDirection, Integer offset, Integer limit);
    Integer delete(List<ID> ids);
}
