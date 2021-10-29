package by.xenon28082.shop.dao.impl;

import by.xenon28082.shop.dao.ProductDAO;
import by.xenon28082.shop.dao.ReserveDAO;
import by.xenon28082.shop.dao.databaseConnection.DataBaseConfig;
import by.xenon28082.shop.entity.Product;
import by.xenon28082.shop.entity.Reserve;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReserveDAOImpl implements ReserveDAO {

    private static final String SAVE_RESERVATION_QUERY = "INSERT INTO orders (user_id, product_id, amount) VALUES (?,?,?)";
    private static final String GET_RESERVATIONS_QUERY = "SELECT * FROM orders WHERE user_id = ?";
    private static final String DELETE_RESERVATION_QUERY = "DELETE FROM orders WHERE user_id = ? AND order_id = ?";

    @Override
    public List<Reserve> getOrders(long userId) throws SQLException {
        Connection connection = DataBaseConfig.getConnection();
        ArrayList<Reserve> reservations = new ArrayList<>();

        PreparedStatement preparedStatement = connection.prepareStatement(GET_RESERVATIONS_QUERY);
        preparedStatement.setLong(1, userId);

        ResultSet resultSet = preparedStatement.executeQuery();
        ProductDAO dao = new ProductDAOImpl();
        while(resultSet.next()){
            System.out.println(resultSet.getLong(2));

            long productId = resultSet.getLong(3);

            Product searchProduct = dao.findOne(productId);

            System.out.println(resultSet.getInt(4));
            Reserve reserveFound = new Reserve(
                    resultSet.getLong(2),
                    searchProduct,
                    resultSet.getInt(4)
            );

            reserveFound.setOrderId(productId);
            reserveFound.setOrderId(resultSet.getLong(1));
            reservations.add(reserveFound);
        }

        return reservations;
    }

    @Override
    public boolean deleteOrder(long userId, long orderId) {
        try {
            Connection connection = DataBaseConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESERVATION_QUERY);
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, orderId);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Reserve save(Reserve reserve) throws SQLException {
        Connection connection = DataBaseConfig.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(SAVE_RESERVATION_QUERY);
        preparedStatement.setLong(1, reserve.getUserId());
        preparedStatement.setLong(2, reserve.getProductId());
        preparedStatement.setLong(3, reserve.getAmount());
        int result = preparedStatement.executeUpdate();
        return null;
    }

    @Override
    public Reserve find(Reserve reserve) throws SQLException {
        return null;
    }

    @Override
    public Reserve findOne(long id) {
        return null;
    }

    @Override
    public boolean update(Reserve reserve) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public List<Reserve> findAll(int row) {
        return null;
    }

    @Override
    public long countAll() throws SQLException {
        return 0;
    }


}
