package main.service;

import main.entity.User;
import main.service.exception.ServiceException;

public interface GetUserService {
    User getUser(int userId) throws ServiceException;
}
