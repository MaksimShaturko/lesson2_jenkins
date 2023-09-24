package main.service;

import main.entity.User;
import main.service.exception.ServiceException;

import java.util.List;

public interface GetAllUsersService {
    List<User> getAllUsers() throws ServiceException;
}
