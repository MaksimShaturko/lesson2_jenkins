package main.service;

import main.service.exception.ServiceException;

public interface DeleteUserService {
    void deleteUser(int userId) throws ServiceException;
}
