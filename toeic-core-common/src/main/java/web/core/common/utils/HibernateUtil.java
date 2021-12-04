package web.core.common.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Map;

public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.err.println("Initial Session Factory failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static Object[] buildNameQuery(Map<String, Object> property){
        StringBuilder nameQuery = new StringBuilder();
        if (property != null && property.size() > 0) {
            String[] params = new String[property.size()];
            Object[] values = new Object[property.size()];
            int i = 0;
            for (Map.Entry<String, Object> item: property.entrySet()) {
                params[i] = item.getKey();
                values[i] = item.getValue();
                i++;
            }
            for (int i1 = 0; i1 < params.length; i1++) {
                nameQuery.append(" and ").append("LOWER (").append(params[i1]).append(") LIKE '%' || :").append(params[i1]).append(" || '%' ");
            }
            return new Object[] {nameQuery.toString(), params, values};
        }
        return new Object[] {nameQuery.toString()};
    }

}
