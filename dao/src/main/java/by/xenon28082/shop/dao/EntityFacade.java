package by.xenon28082.shop.dao;

import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface EntityFacade<T>{

    T save(T t) throws DaoException;

    T find(T t) throws DaoException;

    T findById(long id) throws DaoException;

    boolean update(T t) throws DaoException;

}
