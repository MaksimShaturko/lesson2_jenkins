package main.repository;

import main.entity.User;
import main.repository.exception.RepositoryException;

import java.sql.Connection;

public interface AddUserRepository {
    boolean addUser(ThreadLocal<Connection> threadLocal, User user) throws RepositoryException;
}
