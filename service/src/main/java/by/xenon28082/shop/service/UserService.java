package by.xenon28082.shop.service;

import by.xenon28082.shop.entity.User;
import by.xenon28082.shop.entity.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    User registration(User user) throws SQLException;
    UserDTO logination(User user) throws SQLException;
    User findUserWithId(long id);
    boolean changePassword(User user, String newPassword, String login);
    boolean deleteUser(User user);
    List<User> getUsers(int row);
    long countUsers() throws SQLException;
}
