package by.xenon28082.shop.dao;

import by.xenon28082.shop.entity.User;
import by.xenon28082.shop.entity.UserDTO;

public interface UserDAO extends EntityFacade<User> {

    UserDTO findUserByLogin(User user);
    boolean changeUserPassword(User user, String newPassword);
}
