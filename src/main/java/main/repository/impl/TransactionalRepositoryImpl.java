package main.repository.impl;

import main.repository.TransactionalRepository;
import main.repository.exception.TransactionalException;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionalRepositoryImpl implements TransactionalRepository {
    private static final ThreadLocal<Connection> THREAD_LOCAL = new ThreadLocal<>();
    private static final TransactionalRepositoryImpl INSTANCE = new TransactionalRepositoryImpl();
    private static BasicDataSource ds = new BasicDataSource();

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC Driver is not found. Include it in your library path ");
        }
        ds.setUrl("jdbc:postgresql://192.168.1.74/andersen_trainee");
        ds.setUsername("maksim");
        ds.setPassword("02042010");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private TransactionalRepositoryImpl(){ }

    public static TransactionalRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public ThreadLocal<Connection> startTransaction() throws TransactionalException {
        Connection connection = null;
        try {
            connection = TransactionalRepositoryImpl.getConnection();
        } catch (SQLException e) {
            throw new TransactionalException(e); //"Error while trying to take connection from Connection Pool"
        }
        THREAD_LOCAL.set(connection);
        return THREAD_LOCAL;
    }

    @Override
    public void commitTransaction() throws TransactionalException {
        Connection connection = THREAD_LOCAL.get();
        try {
            connection.setAutoCommit(false);
            connection.commit();
        } catch (SQLException e) {
            throw new TransactionalException("Error while trying to commit connection: " + e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new TransactionalException("Error while trying to close connection: " + e);
        }
        THREAD_LOCAL.remove();
    }

    @Override
    public void rollbackTransaction() throws TransactionalException {
        Connection connection = THREAD_LOCAL.get();
        try {
            connection.setAutoCommit(false);
            connection.rollback();
        } catch (SQLException e) {
            throw new TransactionalException("Error while trying to rollback connection: " + e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        THREAD_LOCAL.remove();
    }
}
