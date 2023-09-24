package main.repository;

import main.entity.User;
import main.repository.exception.RepositoryException;

import java.sql.Connection;

public interface GetUserRepository {
    User getUser(ThreadLocal<Connection> threadLocal , int userId) throws RepositoryException;
}
