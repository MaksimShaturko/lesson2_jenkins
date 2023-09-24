package main.service;

import main.entity.User;
import main.service.exception.ServiceException;

public interface EditUserService {
    boolean editUser(User user) throws ServiceException;
}
