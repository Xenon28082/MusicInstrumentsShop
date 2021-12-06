package by.xenon28082.shop.service;

import by.xenon28082.shop.entity.User;
import by.xenon28082.shop.entity.UserDTO;
import by.xenon28082.shop.service.exception.ServiceException;


public interface UserService {
    User registration(User user) throws ServiceException;
    UserDTO logination(User user) throws ServiceException;
    User findUserByLogin(String login) throws ServiceException;
    boolean updateUserRole(User user) throws ServiceException;
    boolean updateUserLogin(String newLogin, long userId) throws ServiceException;
    boolean updateUserPassword(String newPassword, long userId) throws ServiceException;
}
