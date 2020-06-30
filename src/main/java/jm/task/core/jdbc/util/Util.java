package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName(PropertyReader.read("driver")).newInstance());
            StringBuilder urlFull = new StringBuilder();
            urlFull.
                    append(PropertyReader.read("url")).
                    append("&").
                    append("user=").
                    append(PropertyReader.read("username")).
                    append("&").
                    append("password=").
                    append(PropertyReader.read("password"));

            System.out.println("URL: " + urlFull + "\n");
            Connection connection = DriverManager.getConnection(urlFull.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }

    }

    public static Configuration getConfiguration() {
        final Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", PropertyReader.read("dialect"))
                .setProperty("hibernate.connection.driver_class", PropertyReader.read("driver"))
                .setProperty("hibernate.connection.url", PropertyReader.read("url"))
                .setProperty("hibernate.connection.username", PropertyReader.read("username"))
                .setProperty("hibernate.connection.password", PropertyReader.read("password"))
                .setProperty("hibernate.show_sql", PropertyReader.read("show_sql"))
                .setProperty("hibernate.hbm2ddl.auto", PropertyReader.read("hbm2ddl"));

        return configuration;
    }
}
