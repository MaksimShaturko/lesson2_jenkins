package main.service.impl;

import main.repository.DeleteUserRepository;
import main.repository.RepositoryProvider;
import main.repository.TransactionalRepository;
import main.repository.exception.RepositoryException;
import main.repository.exception.TransactionalException;
import main.service.DeleteUserService;
import main.service.exception.ServiceException;

import java.sql.Connection;

public class DeleteUserServiceImpl implements DeleteUserService {
    private final RepositoryProvider PROVIDER = RepositoryProvider.getInstance();
    private final DeleteUserRepository DELETE_USER_REPOSITORY = PROVIDER.getDeleteUsersRepository();
    private final TransactionalRepository TRANSACTION = PROVIDER.getTransactionalRepository();

    @Override
    public void deleteUser(int userId) throws ServiceException {
        try {
            ThreadLocal<Connection> threadLocal = TRANSACTION.startTransaction();
            DELETE_USER_REPOSITORY.deleteUser(threadLocal, userId);
            TRANSACTION.commitTransaction();
        } catch (TransactionalException e) {
            throw new ServiceException(e);
        } catch (RepositoryException e) {
            try {
                TRANSACTION.rollbackTransaction();
            } catch (TransactionalException e1) {
                throw new ServiceException(e1);
            }
            throw new ServiceException(e);
        }
    }
}
