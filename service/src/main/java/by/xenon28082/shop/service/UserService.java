package by.xenon28082.shop.service;

import by.xenon28082.shop.entity.User;
import by.xenon28082.shop.entity.UserDTO;
import by.xenon28082.shop.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    User registration(User user) throws SQLException, ServiceException;
    UserDTO logination(User user) throws SQLException, ServiceException;
    User findUserWithId(long id);
    User findUserByLogin(String login) throws ServiceException;
    boolean changePassword(User user, String newPassword, String login);
    boolean deleteUser(User user);
    List<User> getUsers(int row);
    long countUsers() throws SQLException, ServiceException;
    boolean updateUserRole(User user) throws ServiceException;
}
