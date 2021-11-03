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
    private static final String DELETE_RESERVATION_QUERY = "UPDATE orders SET amount = amount - ? WHERE user_id = ? AND order_id = ?";//"DELETE FROM orders WHERE user_id = ? AND order_id = ?";
    private static final String FIND_RESERVATION = "SELECT * FROM orders WHERE user_id = ? AND product_id = ?";
    private static final String ADD_AMOUNT_TO_RESERVATION = "UPDATE orders SET amount = amount + ? WHERE order_id = ?";

    @Override
    public List<Reserve> getOrders(long userId) throws SQLException {
        Connection connection = DataBaseConfig.getConnection();
        ArrayList<Reserve> reservations = new ArrayList<>();


        PreparedStatement preparedStatement = connection.prepareStatement(GET_RESERVATIONS_QUERY);
        preparedStatement.setLong(1, userId);

        ResultSet resultSet = preparedStatement.executeQuery();
        ProductDAO dao = new ProductDAOImpl();
        while (resultSet.next()) {

            long productId = resultSet.getLong(3);

            Product searchProduct = dao.findOne(productId);

            Reserve reserveFound = new Reserve(
                    resultSet.getLong(2),
                    searchProduct,
                    resultSet.getInt(4)
            );

            reserveFound.setOrderId(productId);
            reserveFound.setOrderId(resultSet.getLong(1));
            reservations.add(reserveFound);
        }

        System.out.println(reservations);
        return reservations;
    }

    @Override
    public boolean deleteOrder(long userId, long orderId, int amount) {
        try {
            Connection connection = DataBaseConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESERVATION_QUERY);
            preparedStatement.setLong(1, amount);
            preparedStatement.setLong(2, userId);
            preparedStatement.setLong(3, orderId);

            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Reserve save(Reserve reserve) throws SQLException {
        Connection connection = DataBaseConfig.getConnection();
        Reserve foundReserve = find(reserve);

        if (foundReserve != null) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_AMOUNT_TO_RESERVATION);
            preparedStatement.setInt(1, reserve.getAmount());
            preparedStatement.setLong(2, foundReserve.getOrderId());
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                System.out.println("NET");
            } else {
                System.out.println("DA)");
            }
        } else {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_RESERVATION_QUERY);
            preparedStatement.setLong(1, reserve.getUserId());
            preparedStatement.setLong(2, reserve.getProductId());
            preparedStatement.setLong(3, reserve.getAmount());
            int result = preparedStatement.executeUpdate();
        }
        return null;
    }

    @Override
    public Reserve find(Reserve reserve) throws SQLException {
        Connection connection = DataBaseConfig.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATION);
        preparedStatement.setLong(1, reserve.getUserId());
        preparedStatement.setLong(2, reserve.getProductId());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {

            Reserve foundReserve = new Reserve(
                    resultSet.getLong(2),
                    resultSet.getLong(3),
                    resultSet.getInt(4)
            );
            foundReserve.setOrderId(resultSet.getLong(1));
            return foundReserve;
        }
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
