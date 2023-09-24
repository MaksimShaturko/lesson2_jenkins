package main.repository;

import main.repository.impl.AddUserRepositoryImpl;
import main.repository.impl.DeleteUserRepositoryImpl;
import main.repository.impl.EditUserRepositoryImpl;
import main.repository.impl.GetAllUsersRepositoryImpl;
import main.repository.impl.GetUserRepositoryImpl;
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
        return new GetAllUsersRepositoryImpl();
    }

    public GetUserRepository getUserRepository() {
        return new GetUserRepositoryImpl();
    }

    public DeleteUserRepository getDeleteUsersRepository() {
        return new DeleteUserRepositoryImpl();
    }

    public AddUserRepository getAddUserRepository() {
        return new AddUserRepositoryImpl();
    }

    public EditUserRepository getEditUserRepository() {
        return new EditUserRepositoryImpl();
    }

    public TransactionalRepository getTransactionalRepository() {
        return TransactionalRepositoryImpl.getInstance();
    }
}
