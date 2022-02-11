package by.xenon28082.shop.dao.databaseConnection.Impl;

import by.xenon28082.shop.dao.config.DatabaseConfig;
import by.xenon28082.shop.dao.databaseConnection.ConnectionPool;
import by.xenon28082.shop.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Logger;

public class ConnectionPoolImpl  implements ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPoolImpl.class.getName());
    private static final int CONNECTIONS_TOTAL = 4;
    private final DatabaseConfig dataBaseConfig;
    private final ArrayBlockingQueue<Connection> pool;
    private final ArrayBlockingQueue<Connection> taken;

    public ConnectionPoolImpl(final DatabaseConfig dataBaseConfig) {
        this.dataBaseConfig = dataBaseConfig;
        pool = new ArrayBlockingQueue<>(CONNECTIONS_TOTAL);
        taken = new ArrayBlockingQueue<>(CONNECTIONS_TOTAL);
        initConnectionPool();
    }

    private void initConnectionPool() {
        try {
            for (int i = 0; i < CONNECTIONS_TOTAL; i++) {
                pool.add(dataBaseConfig.getConnection());
            }
        } catch (SQLException e) {
            LOGGER.info("SQLException in initConnectionPool");
        }
        LOGGER.info("init pool.size() is " + pool.size());
        LOGGER.info("init taken.size() is " + taken.size());
    }

    @Override
    public Connection take() throws DaoException {
        Connection newConnection;
        try {
            LOGGER.info("#take()");
            newConnection = pool.take();
            taken.add(newConnection);
        } catch (InterruptedException e) {
            LOGGER.info("Exception: #take()");
            throw new DaoException(e);
        }
        LOGGER.info("pool.size() is " + pool.size());
        LOGGER.info("taken.size() is " + taken.size());
        return newConnection;
    }

    @Override
    public void retrieve(final Connection connection) {
        if (connection != null) {
            LOGGER.info("#retrieve(final Connection connection)");
            taken.remove(connection);
            pool.add(connection);
        } else {
            LOGGER.info("retrieve(Connection connection)");
            LOGGER.info("connection=null");
        }
        LOGGER.info("pool.size() is " + pool.size());
        LOGGER.info("taken.size() is " + taken.size());
    }
}