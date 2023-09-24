package main.repository;

import main.entity.User;
import main.repository.exception.RepositoryException;

import java.sql.Connection;
import java.util.List;

public interface GetAllUsersRepository {
    List<User> getAllUsers(ThreadLocal<Connection> threadLocal) throws RepositoryException;
}
