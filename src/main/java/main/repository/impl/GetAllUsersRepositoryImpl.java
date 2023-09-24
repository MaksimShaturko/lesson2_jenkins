package main.repository.impl;

import main.entity.User;
import main.repository.GetAllUsersRepository;
import main.repository.exception.RepositoryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAllUsersRepositoryImpl implements GetAllUsersRepository {

    private final static String GET_ALL_USERS = "SELECT * from users";

    @Override
    public List<User> getAllUsers(ThreadLocal<Connection> threadLocal) throws RepositoryException {
        Connection connection = threadLocal.get();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_USERS)) {
            List<User> listOfUsers = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt(ColumnName.USERS_USER_ID);
                String name = rs.getString(ColumnName.USERS_NAME);
                String email = rs.getString(ColumnName.USERS_EMAIL);
                int age = rs.getInt(ColumnName.USERS_AGE);
                String surname = rs.getString(ColumnName.USERS_SURNAME);
                User user = new User(userId, name, surname, age, email);
                listOfUsers.add(user);
            }
            return listOfUsers;
        } catch (SQLException e) {
            throw new RepositoryException ("Error while getting all users: " + e);
        }


    }
}
