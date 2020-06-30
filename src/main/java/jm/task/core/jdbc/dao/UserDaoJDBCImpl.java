package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;

    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    public void createUsersTable() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "create table users (id int auto_increment primary key, name varchar(30), lastname varchar(40), age int)"
        )) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "drop table users"
        )) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public void saveUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO users (name, lastname, age) VALUES (?,?,?)"
        )) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public void removeUserById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM users WHERE id = ?"
        )) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM users"
        )) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getInt("age")
                ));
            }

        } catch (SQLException e) {
            e.getStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM users"
        )) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
