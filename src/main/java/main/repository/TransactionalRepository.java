package main.repository;

import main.repository.exception.TransactionalException;

import javax.transaction.Transactional;
import java.sql.Connection;

public interface TransactionalRepository {
    ThreadLocal<Connection> startTransaction() throws TransactionalException; //throws TransactionException;
    void commitTransaction() throws TransactionalException;

    void rollbackTransaction() throws TransactionalException;



}
