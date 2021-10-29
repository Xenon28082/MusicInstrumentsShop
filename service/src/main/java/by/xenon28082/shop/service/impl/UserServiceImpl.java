package by.xenon28082.shop.service.impl;

import by.xenon28082.shop.dao.UserDAO;
import by.xenon28082.shop.dao.impl.UserDAOImpl;
import by.xenon28082.shop.entity.User;
import by.xenon28082.shop.entity.UserDTO;
import by.xenon28082.shop.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public User registration(User user) throws SQLException {
        UserDAO dao = new UserDAOImpl();
        return dao.save(user);
    }

    @Override
    public UserDTO logination(User user) throws SQLException {
        UserDAO dao = new UserDAOImpl();
        user = dao.find(user);
        return (user == null) ? null : new UserDTO(user);
    }

    @Override
    public User findUserWithId(long id) {
        return null;
    }

    @Override
    public boolean changePassword(User user, String newPassword, String login) {
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }

    @Override
    public List<User> getUsers(int row) {
        return null;
    }

    @Override
    public long countUsers() throws SQLException {
        UserDAO dao = new UserDAOImpl();
        return dao.countAll();
    }
}
