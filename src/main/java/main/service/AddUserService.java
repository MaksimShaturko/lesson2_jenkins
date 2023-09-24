package main.service;

import main.entity.User;
import main.service.exception.ServiceException;

public interface AddUserService {
    boolean addUser(User user) throws ServiceException;
}
