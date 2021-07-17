package web.core.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import web.core.dao.RoleDao;
import web.core.data.daoimpl.AbstractDao;
import web.core.persistence.entity.RoleEntity;

import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends AbstractDao<Integer, RoleEntity> implements RoleDao{

    @Override
    public List<RoleEntity> findByRoles(List<String> roles) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        List<RoleEntity> roleEntities = new ArrayList<RoleEntity>();
        try {
            String sql = "from RoleEntity re where re.name in (:roles)";
            Query<RoleEntity> query = session.createQuery(sql);
            query.setParameterList("roles", roles);
            roleEntities = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return roleEntities;
    }
}
