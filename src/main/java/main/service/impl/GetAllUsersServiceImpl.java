package main.service.impl;

import main.repository.GetAllUsersRepository;
import main.repository.RepositoryProvider;
import main.repository.TransactionalRepository;
import main.repository.exception.RepositoryException;
import main.repository.exception.TransactionalException;
import main.service.GetAllUsersService;
import main.entity.User;
import main.service.exception.ServiceException;

import java.sql.Connection;
import java.util.List;

public class GetAllUsersServiceImpl implements GetAllUsersService {
    private final static RepositoryProvider PROVIDER = RepositoryProvider.getInstance();
    private final static TransactionalRepository TRANSACTION = PROVIDER.getTransactionalRepository();
    private final static GetAllUsersRepository GET_ALL_USERS_REPOSITORY = PROVIDER.getAllUsersRepository();

    @Override
    public List<User> getAllUsers() throws ServiceException {

        try {
            ThreadLocal<Connection> threadLocal = TRANSACTION.startTransaction();
            List<User> listOfUsers = GET_ALL_USERS_REPOSITORY.getAllUsers(threadLocal);
            TRANSACTION.commitTransaction();
            return listOfUsers;
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
