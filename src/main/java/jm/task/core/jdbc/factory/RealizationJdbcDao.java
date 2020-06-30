package jm.task.core.jdbc.factory;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class RealizationJdbcDao extends UserDaoFactory {
    @Override
    public UserDao realization() {
        return new UserDaoJDBCImpl(Util.getConnection());
    }
}
