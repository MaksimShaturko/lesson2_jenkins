package main.service.impl;

import main.entity.User;
import main.repository.AddUserRepository;
import main.repository.RepositoryProvider;
import main.repository.TransactionalRepository;
import main.repository.exception.RepositoryException;
import main.repository.exception.TransactionalException;
import main.service.AddUserService;
import main.service.exception.ServiceException;

import java.sql.Connection;

public class AddUserServiceImpl implements AddUserService {
    private final RepositoryProvider PROVIDER = RepositoryProvider.getInstance();
    private final AddUserRepository ADD_USER_REPOSITORY = PROVIDER.getAddUserRepository();
    private final TransactionalRepository TRANSACTION = PROVIDER.getTransactionalRepository();

    @Override
    public boolean addUser(User user) throws ServiceException {
        try {
            ThreadLocal<Connection> threadLocal = TRANSACTION.startTransaction();
            if (ADD_USER_REPOSITORY.addUser(threadLocal, user)) {
                TRANSACTION.commitTransaction();
                return true;
            } else {
                TRANSACTION.rollbackTransaction();
                return false;
            }
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
