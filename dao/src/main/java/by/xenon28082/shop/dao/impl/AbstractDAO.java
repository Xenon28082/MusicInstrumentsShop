package by.xenon28082.shop.dao.impl;


import by.xenon28082.shop.dao.databaseConnection.ConnectionPool;
import by.xenon28082.shop.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.util.Objects.nonNull;

public abstract class AbstractDAO {
    private ConnectionPool connectionPool = null;

    public AbstractDAO() {
    }

    public AbstractDAO(final ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    protected Connection getConnection(final boolean hasAutocommit) throws DaoException, SQLException {
        final Connection connection = connectionPool.take();
        connection.setAutoCommit(hasAutocommit);
        return connection;
    }

    protected void retrieve(final Connection connection) {
        connectionPool.retrieve(connection);
    }

    protected PreparedStatement getPreparedStatement(final String query, final Connection connection,
                                                     final List<Object> parameters) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(query);
        setPreparedStatementParameters(preparedStatement, parameters);
        return preparedStatement;
    }

    protected void setPreparedStatementParameters(final PreparedStatement preparedStatement,
                                                  final List<Object> parameters) throws SQLException {
        for (int i = 0, queryParameterIndex = 1; i < parameters.size(); i++, queryParameterIndex++) {
            final Object parameter = parameters.get(i);
            setPreparedStatementParameter(preparedStatement, queryParameterIndex, parameter);
        }
    }

    protected void setPreparedStatementParameter(final PreparedStatement preparedStatement,
                                                 final int queryParameterIndex, final Object parameter) throws SQLException {
        if (Long.class == parameter.getClass()) {
            preparedStatement.setLong(queryParameterIndex, (Long) parameter);
        } else if (Integer.class == parameter.getClass()) {
            preparedStatement.setInt(queryParameterIndex, (Integer) parameter);
        } else if (String.class == parameter.getClass()) {
            preparedStatement.setString(queryParameterIndex, (String) parameter);
        }
    }

    protected void close(final ResultSet... resultSets) {
        if (resultSets != null) {
            for (final ResultSet resultSet : resultSets) {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    protected void close(final PreparedStatement... preparedStatements) {
        if (preparedStatements != null) {
            for (final PreparedStatement preparedStatement : preparedStatements) {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    protected void processAbnormalCase(boolean isTrue, String message) throws DaoException {
        if (isTrue) {
            throw new DaoException(message);
        }
    }
}
