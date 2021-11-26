package by.xenon28082.shop.dao.impl;

import by.xenon28082.shop.DaoFactory;
import by.xenon28082.shop.dao.ProductDAO;
import by.xenon28082.shop.dao.OrderDAO;
import by.xenon28082.shop.dao.config.DatabaseConfig2;
//import by.xenon28082.shop.dao.databaseConnection.DataBaseConfig;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.entity.Product;
import by.xenon28082.shop.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private static final String SAVE_RESERVATION_QUERY = "INSERT INTO orders (user_id, product_id, amount) VALUES (?,?,?)";
    private static final String GET_RESERVATIONS_QUERY = "SELECT * FROM orders WHERE user_id = ?";
    private static final String DELETE_RESERVATION_QUERY = "DELETE FROM orders WHERE user_id = ? AND order_id = ?";
    private static final String FIND_RESERVATION_QUERY = "SELECT * FROM orders WHERE user_id = ? AND product_id = ?";
    private static final String FIND_RESERVATION_BY_ID_QUERY = "SELECT * FROM orders WHERE order_id = ?";
    private static final String UPDATE_QUERY = "UPDATE orders SET amount = ? WHERE user_id = ? AND product_id = ?";
    private static final String FIND_RESERVATION_BY_PRODUCT_ID_QUERY = "SELECT * FROM orders WHERE product_id = ?";
    private static final String DELETE_ALL_BY_PRODUCT_ID_QUERY = "DELETE FROM orders WHERE product_id = ?";



    @Override
    public List<Order> getOrders(long userId){

        try {
            Connection connection = DatabaseConfig2.getConnection();
            ArrayList<Order> reservations = new ArrayList<>();


            PreparedStatement preparedStatement = connection.prepareStatement(GET_RESERVATIONS_QUERY);
            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            ProductDAO dao = DaoFactory.getInstance().getProductDao();
            while (resultSet.next()) {
                long productId = resultSet.getLong(3);
                Product searchProduct = dao.findById(productId);
                searchProduct.setId(productId);
                Order orderFound = new Order(
                        resultSet.getLong(2),
                        searchProduct,
                        resultSet.getInt(4)
                );
                orderFound.setOrderId(productId);
                orderFound.setOrderId(resultSet.getLong(1));
                reservations.add(orderFound);
            }
            return reservations;
        } catch (SQLException | DaoException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteOrder(long userId, long orderId, int amountToDelete) {
        try {

            Order foundOrder = findById(orderId);
            if (foundOrder.getAmount() < amountToDelete) {
                System.out.println("You can't delete more then you have");
                return false;
            }
            Order updatedOrder = new Order(
                    userId,
                    foundOrder.getProductId(),
                    foundOrder.getAmount() - amountToDelete
            );
            updatedOrder.setOrderId(orderId);
            if (updatedOrder.getAmount() != 0) {
                return update(updatedOrder);
            } else {
                Connection connection = DatabaseConfig2.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESERVATION_QUERY);
                preparedStatement.setLong(1, userId);
                preparedStatement.setLong(2, orderId);

                return preparedStatement.executeUpdate() != 0;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean findByProductId(long productId) {
        try {
            Connection connection = DatabaseConfig2.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATION_BY_PRODUCT_ID_QUERY);
            preparedStatement.setLong(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Order order = new Order(
                    resultSet.getLong(1),
                    resultSet.getLong(2),
                    resultSet.getLong(3),
                    resultSet.getInt(4)
            );
            if(order.getOrderId() != 0){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAllByProductId(long productId) {
        try {
            Connection connection = DatabaseConfig2.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_BY_PRODUCT_ID_QUERY);
            preparedStatement.setLong(1, productId);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Order save(Order order){
        try {
            Connection connection = DatabaseConfig2.getConnection();
            Order foundOrder = find(order);

            ProductDAO dao = DaoFactory.getInstance().getProductDao();
            Product foundProduct = dao.findById(order.getProductId());
            if (foundProduct.getStock() < order.getAmount()) {
                return null;
            }
            if (foundOrder != null) {
                Order updatedOrder = new Order(
                        order.getUserId(),
                        order.getProductId(),
                        order.getAmount() + foundOrder.getAmount()
                );
                updatedOrder.setOrderId(order.getOrderId());

                update(updatedOrder);
            } else {
                PreparedStatement preparedStatement = connection.prepareStatement(SAVE_RESERVATION_QUERY);
                preparedStatement.setLong(1, order.getUserId());
                preparedStatement.setLong(2, order.getProductId());
                preparedStatement.setLong(3, order.getAmount());
                int result = preparedStatement.executeUpdate();
            }
        } catch (SQLException | DaoException throwables) {
            throwables.printStackTrace();
        }

        return new Order();
    }

    @Override
    public Order find(Order order){
        try {
            Connection connection = DatabaseConfig2.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATION_QUERY);
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setLong(2, order.getProductId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                Order foundOrder = new Order(
                        resultSet.getLong(2),
                        resultSet.getLong(3),
                        resultSet.getInt(4)
                );
                foundOrder.setOrderId(resultSet.getLong(1));
                return foundOrder;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Order findById(long id) {
        try {
            Connection connection = DatabaseConfig2.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATION_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Order foundOrder = new Order(
                    resultSet.getLong(2),
                    resultSet.getLong(3),
                    resultSet.getInt(4)
            );
            return foundOrder;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Order order) {

        try {
            Connection connection = DatabaseConfig2.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setInt(1, order.getAmount());
            preparedStatement.setLong(2, order.getUserId());
            preparedStatement.setLong(3, order.getProductId());
            int rows = preparedStatement.executeUpdate();
            if (rows != 0) {
                return true;
            } else {
                return false;
            }
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
    public List<Order> findAll(int row) {
        return null;
    }

    @Override
    public long countAll() throws SQLException {
        return 0;
    }


}