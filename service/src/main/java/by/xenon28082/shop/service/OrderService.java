package by.xenon28082.shop.service;

import by.xenon28082.shop.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    boolean reserveProduct(Order order) throws SQLException;
    List<Order> getReservations(long userId) throws SQLException;
    boolean deleteReservation(long userId, long orderId, int amount);
    boolean findReservation(long productId);
    boolean deleteAllByProductId(long productId);
}
