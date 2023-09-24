package main.repository.impl;

import main.repository.DeleteUserRepository;
import main.repository.exception.RepositoryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUserRepositoryImpl implements DeleteUserRepository {
    private final static String DELETE_USER = "DELETE from users WHERE user_id = ?";

    @Override
    public void deleteUser(ThreadLocal<Connection> threadLocal, int userId) throws RepositoryException {
        Connection connection = threadLocal.get();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_USER)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("Error while adding to db table 'visited_tours'", e);
        }
    }
}

