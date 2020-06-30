package jm.task.core.jdbc.factory;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.util.PropertyReader;

public abstract class UserDaoFactory {

    enum typeDAO {
        JDBC,
        Hibernate
    }
    public static UserDao createUserDao() {
        typeDAO daotype = typeDAO.valueOf(PropertyReader.read("daotype"));
        UserDaoFactory userDaoFactory = null;
        switch (daotype) {
            case JDBC:
                userDaoFactory = new RealizationJdbcDao();
                break;
            case Hibernate:
                userDaoFactory = new RealizationHibernateDao();
                break;
        }
        return userDaoFactory.realization();
    }
    abstract public UserDao realization();
}
