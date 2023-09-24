package main.repository;

import main.entity.User;
import main.repository.exception.RepositoryException;

import java.sql.Connection;

public interface EditUserRepository {
    boolean editUser(ThreadLocal<Connection> threadLocal, User user) throws RepositoryException;
}
