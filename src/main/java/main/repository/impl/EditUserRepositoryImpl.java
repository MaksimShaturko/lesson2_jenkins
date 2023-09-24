package main.repository.impl;

import main.entity.User;
import main.repository.EditUserRepository;
import main.repository.exception.RepositoryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditUserRepositoryImpl implements EditUserRepository {
    private final String EDIT_USER = "UPDATE users SET name=?, surname=?, age=?, email=? WHERE user_id=?";

    @Override
    public boolean editUser(ThreadLocal<Connection> threadLocal, User user) throws RepositoryException {
        Connection connection = threadLocal.get();
        try (PreparedStatement ps = connection.prepareStatement(EDIT_USER)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setInt(3, user.getAge());
            ps.setString(4, user.getEmail());
            ps.setInt(5, user.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RepositoryException("Error while editing a user: " + e);
        }
    }
}
