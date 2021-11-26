package by.xenon28082.shop.service.impl;

import by.xenon28082.shop.dao.UserDAO;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.dao.impl.UserDAOImpl;
import by.xenon28082.shop.entity.User;
import by.xenon28082.shop.entity.UserDTO;
import by.xenon28082.shop.service.UserService;
import by.xenon28082.shop.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDAO dao;

    public UserServiceImpl(final UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public User registration(User user) throws ServiceException {
        try {
            return dao.save(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public UserDTO logination(User user) throws ServiceException {
        try {
            user = dao.find(user);
            return (user == null) ? null : new UserDTO(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public User findUserWithId(long id) {
        return null;
    }

    @Override
    public User findUserByLogin(String login) throws ServiceException {
        try {
            return dao.findUserByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
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
    public long countUsers() throws SQLException, ServiceException {
        try {
            return dao.countAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateUserRole(User user) throws ServiceException {
        try {
            return dao.update(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
