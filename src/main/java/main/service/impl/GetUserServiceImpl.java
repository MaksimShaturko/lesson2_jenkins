package main.service.impl;

import main.entity.User;
import main.repository.GetAllUsersRepository;
import main.repository.GetUserRepository;
import main.repository.RepositoryProvider;
import main.repository.TransactionalRepository;
import main.repository.exception.RepositoryException;
import main.repository.exception.TransactionalException;
import main.service.GetUserService;
import main.service.exception.ServiceException;

import java.sql.Connection;
import java.util.List;

public class GetUserServiceImpl implements GetUserService {
    private final static RepositoryProvider PROVIDER = RepositoryProvider.getInstance();
    private final static TransactionalRepository TRANSACTION = PROVIDER.getTransactionalRepository();
    private final static GetUserRepository GET_USER_REPOSITORY = PROVIDER.getUserRepository();

    @Override
    public User getUser(int userId) throws ServiceException {
        try {
            ThreadLocal<Connection> threadLocal = TRANSACTION.startTransaction();
            User user = GET_USER_REPOSITORY.getUser(threadLocal, userId);
            TRANSACTION.commitTransaction();
            return user;
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
