package main.repository;

import main.repository.impl.AddUserRepositoryImpl;
import main.repository.impl.DeleteUserRepositoryImpl;
import main.repository.impl.GetAllUsersRepositoryImpl;
import main.repository.impl.TransactionalRepositoryImpl;

public class RepositoryProvider {
    private static RepositoryProvider instance = null;

    private RepositoryProvider() {
    }

    public static RepositoryProvider getInstance() {
        if (instance == null) {
            instance = new RepositoryProvider();
        }
        return instance;
    }

    public GetAllUsersRepository getAllUsersRepository() {
        GetAllUsersRepository getAllUsersRepository;
        getAllUsersRepository = new GetAllUsersRepositoryImpl();
        return getAllUsersRepository;
    }

    public DeleteUserRepository getDeleteUsersRepository() {
        DeleteUserRepository deleteUserRepository;
        deleteUserRepository = new DeleteUserRepositoryImpl();
        return deleteUserRepository;
    }

    public AddUserRepository getAddUserRepository() {
        AddUserRepository addUserRepository;
        addUserRepository = new AddUserRepositoryImpl();
        return addUserRepository;
    }

    public TransactionalRepository getTransactionalRepository() {
        TransactionalRepository transactionalRepository;
        transactionalRepository = TransactionalRepositoryImpl.getInstance();
        return transactionalRepository;
    }
}
