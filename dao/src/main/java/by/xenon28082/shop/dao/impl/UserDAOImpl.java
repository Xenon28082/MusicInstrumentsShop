package by.xenon28082.shop.dao.impl;

import by.xenon28082.shop.dao.UserDAO;
import by.xenon28082.shop.dao.config.DataBaseConfig;
import by.xenon28082.shop.dao.databaseConnection.ConnectionPool;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl extends AbstractDAO implements UserDAO {

    private static final String COUNT_USERS_QUERY = "SELECT COUNT(*) FROM users";
    private static final String SAVE_USER_QUERY = "INSERT INTO users (login, firstname, lastname, password) VALUES (?, ?, ?, ?)";
    private static final String FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY = "SELECT * FROM users WHERE login = ? AND password = ?";
    private static final String UPDATE_USER_ROLE_QUERY = "UPDATE users SET role_id = ? WHERE id = ?";
    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";

    public UserDAOImpl(final ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    public User save(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection(true);
            preparedStatement = connection.prepareStatement(SAVE_USER_QUERY);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getLastname());
            preparedStatement.setString(4, user.getPassword());
            int result = preparedStatement.executeUpdate();
            return find(user);
        } catch (SQLException | DaoException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            retrieve(connection);
        }
    }

    @Override
    public User find(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection(true);
            preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();
            user = new User();
            while (resultSet.next()) {
                user.setLogin(resultSet.getString(1));
                user.setId(resultSet.getLong(5));
                user.setRole(resultSet.getInt(6));
            }
            if (user.getLogin() != null) {
                return user;
            }
            return find(user);
        } catch (SQLException | DaoException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            retrieve(connection);
        }


    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public boolean update(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection(true);
            preparedStatement = connection.prepareStatement(UPDATE_USER_ROLE_QUERY);
            preparedStatement.setInt(1, user.getRole());
            preparedStatement.setLong(2, user.getId());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException | DaoException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            retrieve(connection);
        }
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public List<User> findAll(int row) {
        return null;
    }

    @Override
    public long countAll() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection(true);
            preparedStatement = connection.prepareStatement(COUNT_USERS_QUERY);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException | DaoException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            retrieve(connection);
        }

    }


    @Override
    public User findUserByLogin(String login) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection(true);
            preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            User foundUser = new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getLong(5),
                    resultSet.getInt(6)
            );
            return foundUser;
        } catch (SQLException | DaoException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            retrieve(connection);
        }
    }

    @Override
    public boolean changeUserPassword(User user, String newPassword) {
        return false;
    }
}
