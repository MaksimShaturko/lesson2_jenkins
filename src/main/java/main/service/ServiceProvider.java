package main.service;

import main.repository.GetAllUsersRepository;
import main.service.impl.AddUserServiceImpl;
import main.service.impl.DeleteUserServiceImpl;
import main.service.impl.GetAllUsersServiceImpl;

public class ServiceProvider {

    private static ServiceProvider instance = null;
    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        if (instance == null) {
            instance = new ServiceProvider();
        }
        return instance;
    }

    public GetAllUsersService getGetAllUserService() {
        return new GetAllUsersServiceImpl();
    }

    public DeleteUserService getDeleteUserService() {
        return new DeleteUserServiceImpl();
    }
    public AddUserService getAddUserService() {
        return new AddUserServiceImpl();
    }
}
