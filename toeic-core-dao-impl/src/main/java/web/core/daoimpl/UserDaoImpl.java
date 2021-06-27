package web.core.daoimpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import web.core.dao.UserDao;
import web.core.data.daoimpl.AbstractDao;
import web.core.persistence.entity.UserEntity;

public class UserDaoImpl extends AbstractDao<Integer, UserEntity> implements UserDao {

    @Override
    public UserEntity findUserByUsernameAndPassword(String name, String password) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        UserEntity entity = new UserEntity();
        try {
            StringBuilder sql = new StringBuilder("FROM UserEntity WHERE name = :name AND password = :password ");
            Query<UserEntity> query = session.createQuery(sql.toString());
            query.setParameter("name", name);
            query.setParameter("password", password);
            entity = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return entity;
    }
}
