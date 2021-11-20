package by.xenon28082.shop.dao;

import by.xenon28082.shop.entity.User;
import by.xenon28082.shop.entity.UserDTO;

public interface UserDAO extends EntityFacade<User> {

    User findUserByLogin(String login);
    boolean changeUserPassword(User user, String newPassword);
}
