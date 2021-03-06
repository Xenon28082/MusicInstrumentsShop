package by.xenon28082.shop.service.impl;

import by.xenon28082.shop.dao.UserDAO;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.entity.User;
import by.xenon28082.shop.entity.UserDTO;
import by.xenon28082.shop.service.UserService;
import by.xenon28082.shop.service.exception.ServiceException;
import by.xenon28082.shop.service.validators.Validator;
import by.xenon28082.shop.service.validators.ValidatorImpl;


import java.util.Arrays;

public class UserServiceImpl implements UserService {

    private final UserDAO dao;
    private final Validator validator = ValidatorImpl.getInstance();

    public UserServiceImpl(final UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public User registration(User user) throws ServiceException {
        try {
            if(validator.validateIsNull(user)){
                return null;
            }
            return dao.save(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public UserDTO logination(User user) throws ServiceException {
        try {
            if(validator.validateIsNull(user)){
                return null;
            }
            user = dao.find(user);
            return (user == null) ? null : new UserDTO(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public User findUserByLogin(String login) throws ServiceException {
        try {
            if(validator.validateIsEmpty(Arrays.asList(login))){
                return null;
            }
            return dao.findUserByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateUserRole(User user) throws ServiceException {
        try {
            if(validator.validateIsNull(user)){
                return false;
            }
            return dao.update(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateUserLogin(String newLogin, long userId) throws ServiceException {
        try {
            if(validator.validateIsEmpty(Arrays.asList(newLogin, String.valueOf(userId)))){
                return false;
            }
            return dao.updateLogin(newLogin, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateUserPassword(String newPassword, long userId) throws ServiceException {
        try {
            if(validator.validateIsEmpty(Arrays.asList(newPassword, String.valueOf(userId)))){
                return false;
            }
            return dao.updatePassword(newPassword, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
