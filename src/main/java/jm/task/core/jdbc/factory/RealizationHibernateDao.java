package jm.task.core.jdbc.factory;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class RealizationHibernateDao extends UserDaoFactory {
    @Override
    public UserDao realization() {
        return new UserDaoHibernateImpl();
    }
}
