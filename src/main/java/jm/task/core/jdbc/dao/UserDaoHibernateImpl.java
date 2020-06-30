package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import jm.task.core.jdbc.util.HibernateSessionFactory;
import org.hibernate.Transaction;
import javax.persistence.PersistenceException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    @Override
    public void createUsersTable() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("create table users (id int auto_increment primary key, name varchar(30), lastname varchar(40), age int)").executeUpdate();
            transaction.commit();
        } catch (PersistenceException e) {
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("drop table users").executeUpdate();
            transaction.commit();
        } catch (PersistenceException e) {
        }
    }

    @Override
    public void saveUser(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(int id) {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.get(User.class, id));
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        String hql = "From " + User.class.getSimpleName();
        List<User> users = (List<User>) session.createQuery(hql).list();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM User u").executeUpdate();
        transaction.commit();
        session.close();
    }
}
