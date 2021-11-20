package by.xenon28082.shop.dao.impl;

import by.xenon28082.shop.dao.UserDAO;
import by.xenon28082.shop.dao.databaseConnection.DataBaseConfig;
import by.xenon28082.shop.entity.User;
import by.xenon28082.shop.entity.UserDTO;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static final String COUNT_USERS_QUERY = "SELECT COUNT(*) FROM users";
    private static final String SAVE_USER_QUERY = "INSERT INTO users (login, firstname, lastname, password) VALUES (?, ?, ?, ?)";
    private static final String FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY = "SELECT * FROM users WHERE login = ? AND password = ?";
    private static final String UPDATE_USER_ROLE_QUERY = "UPDATE users SET role_id = ? WHERE id = ?";
    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";

    @Override
    public User save(User user) {

        try {
            Connection connection = DataBaseConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER_QUERY);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getLastname());
            preparedStatement.setString(4, user.getPassword());
            int result = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return find(user);
    }

    @Override
    public User find(User user) {

        try {
            Connection connection = DataBaseConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            user = new User();
            while (resultSet.next()) {
                user.setLogin(resultSet.getString(1));
                user.setId(resultSet.getLong(5));
                user.setRole(resultSet.getInt(6));
            }
            if (user.getLogin() != null) {
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public boolean update(User user) {
        try {
            Connection connection = DataBaseConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_ROLE_QUERY);
            preparedStatement.setInt(1, user.getRole());
            preparedStatement.setLong(2, user.getId());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
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
    public long countAll() throws SQLException {
        Connection connection = DataBaseConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(COUNT_USERS_QUERY);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getLong(1);
    }


    @Override
    public User findUserByLogin(String login) {
        try {
            Connection connection = DataBaseConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            User foundUser = new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getLong(5),
                    resultSet.getInt(6)
                    );
            return foundUser;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean changeUserPassword(User user, String newPassword) {
        return false;
    }
}
