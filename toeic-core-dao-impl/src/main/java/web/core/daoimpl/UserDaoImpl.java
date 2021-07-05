package web.core.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import web.core.dao.UserDao;
import web.core.data.daoimpl.AbstractDao;
import web.core.persistence.entity.UserEntity;

public class UserDaoImpl extends AbstractDao<Integer, UserEntity> implements UserDao {

    @Override
    public Object[] checkLogin(String name, String password) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        boolean isUserExist = false;
        String roleName = null;
        try {
            StringBuilder sql = new StringBuilder("from UserEntity ue where ue.name = :name and ue.password = :password");
            Query query = session.createQuery(sql.toString());
            query.setParameter("name", name);
            query.setParameter("password", password);
            if (query.list().size() > 0) {
                isUserExist = true;
                UserEntity userEntity = (UserEntity) query.uniqueResult();
                roleName = userEntity.getRoleEntity().getName();
            }
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
            return new Object[]{isUserExist, roleName};
    }
}
