package web.core.data.daoimpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javassist.tools.rmi.ObjectNotFoundException;
import web.core.common.constant.CoreConstant;
import web.core.common.utils.HibernateUtil;
import web.core.data.dao.GenericDao;

public class AbstractDao <ID extends Serializable, T> implements GenericDao<ID, T>{

    private Class<T> persistenceClass;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.persistenceClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()) .getActualTypeArguments()[1];
    }

    public String getPersistenceClassName() {
        return persistenceClass.getSimpleName();
    }

    protected Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() {
        List<T> list = new ArrayList<>();
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("from ");
            sql.append(this.getPersistenceClassName());
            Query<T> query = session.createQuery(sql.toString());
            list = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T update(T entity) {
        T result = null;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Object object = session.merge(entity);
            result = (T) object;
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public T save(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public T findById(ID id) throws ObjectNotFoundException {
        T result = null;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            result = session.get(persistenceClass, id);
            if (result == null) {
                throw new ObjectNotFoundException("NOT FOUND "+id);
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] findByProperty(String property, Object value, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<T> list = new ArrayList<>();
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Object totalItems = 0;
        try {
            StringBuilder sql = new StringBuilder("from ");
            sql.append(getPersistenceClassName());
            if (property != null && value != null) {
                sql.append(" where ").append(property).append(" = :value");
            }
            if (sortExpression != null && sortDirection != null) {
                sql.append(" order by ").append(sortExpression);
                sql.append(" " + (sortDirection.equals(CoreConstant.SORT_ACS) ? "asc" : "desc"));
            }
            Query<T> query1 = session.createQuery(sql.toString());
            if (value != null) {
                query1.setParameter("value", value);
            }
            if (offset != null && offset >= 0){
                query1.setFirstResult(offset);
            }
            if (limit != null && limit > 0){
                query1.setMaxResults(limit);
            }
            list = query1.list();

//			totalItems = list.size();
            StringBuilder sql2 = new StringBuilder("select count(*) from ");
            sql2.append(getPersistenceClassName());
            if (property !=null && value != null) {
                sql2.append(" where ").append(property).append("= :value");
            }
            Query<T> query2 = session.createQuery(sql2.toString());
            if (value != null) {
                query2.setParameter("value", value);
            }
            totalItems = query2.list().get(0);
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return new Object[] {totalItems, list};
    }

    @Override
    public Integer delete(List<ID> ids) {
        Integer count = 0;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            for (ID id : ids) {
                T t = session.get(persistenceClass, id);
                session.delete(t);
                count++;
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return count;
    }

}
