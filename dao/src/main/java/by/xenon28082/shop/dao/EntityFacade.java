package by.xenon28082.shop.dao;

import by.xenon28082.shop.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface EntityFacade<T>{

    T save(T t) throws SQLException;

    T find(T t) throws SQLException;

    T findOne(long id);

    boolean update(T t);

    boolean delete(long id);

    List<T> findAll(int row);

    long countAll() throws SQLException;

}
