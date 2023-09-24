package main.repository;

import main.repository.exception.RepositoryException;

import java.sql.Connection;

public interface DeleteUserRepository {
    void deleteUser(ThreadLocal<Connection> threadLocal, int userId) throws RepositoryException;
}
