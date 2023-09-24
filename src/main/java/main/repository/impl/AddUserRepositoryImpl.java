package main.repository.impl;

import main.entity.User;
import main.repository.AddUserRepository;
import main.repository.exception.RepositoryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddUserRepositoryImpl implements AddUserRepository {
    private final String SAVE_USER = "INSERT INTO users (name, surname, age, email) VALUES (?,?,?,?)";

    @Override
    public boolean addUser(ThreadLocal<Connection> threadLocal, User user) throws RepositoryException {
        Connection connection = threadLocal.get();
        try (PreparedStatement ps = connection.prepareStatement(SAVE_USER)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setInt(3, user.getAge());
            ps.setString(4, user.getEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RepositoryException("Error while saving the user: " + e);
        }
    }
}


