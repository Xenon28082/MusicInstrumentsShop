package by.xenon28082.shop.dao.databaseConnection;

import by.xenon28082.shop.dao.exception.DaoException;

import java.sql.Connection;

public interface ConnectionPool {
    Connection take() throws DaoException;
    void retrieve(final Connection connection);
}
