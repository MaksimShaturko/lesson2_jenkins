package main.repository.impl;


import main.entity.User;
import main.repository.GetUserRepository;
import main.repository.exception.RepositoryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetUserRepositoryImpl implements GetUserRepository {
    private final static String GET_USER = "SELECT * from users WHERE user_id = ?";

    @Override
    public User getUser(ThreadLocal<Connection> threadLocal, int userId) throws RepositoryException {
        Connection connection = threadLocal.get();
        try (PreparedStatement ps = connection.prepareStatement(GET_USER)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            rs.next();
                User user = new User(userId, rs.getString(ColumnName.USERS_NAME), rs.getString(ColumnName.USERS_SURNAME),
                    rs.getInt(ColumnName.USERS_AGE), rs.getString(ColumnName.USERS_EMAIL));
            return user;
        } catch (SQLException e) {
            throw new RepositoryException ("Error while getting a user: " + e);
        }
    }
}
