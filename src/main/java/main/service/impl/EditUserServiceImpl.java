package main.service.impl;

import main.entity.User;
import main.repository.EditUserRepository;
import main.repository.RepositoryProvider;
import main.repository.TransactionalRepository;
import main.repository.exception.RepositoryException;
import main.repository.exception.TransactionalException;
import main.service.EditUserService;
import main.service.exception.ServiceException;

import java.sql.Connection;

public class EditUserServiceImpl implements EditUserService {
    private final static RepositoryProvider PROVIDER = RepositoryProvider.getInstance();
    private final static TransactionalRepository TRANSACTION = PROVIDER.getTransactionalRepository();
    private final static EditUserRepository EDIT_USER_REPOSITORY = PROVIDER.getEditUserRepository();

    @Override
    public boolean editUser(User user) throws ServiceException {
        try {
            ThreadLocal<Connection> threadLocal = TRANSACTION.startTransaction();
            if (EDIT_USER_REPOSITORY.editUser(threadLocal, user)) {
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
