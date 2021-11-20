package by.xenon28082.shop.service.impl;

import by.xenon28082.shop.dao.OrderDAO;
import by.xenon28082.shop.dao.impl.OrderDAOImpl;
import by.xenon28082.shop.dao.impl.comparators.OrderIdComparator;
import by.xenon28082.shop.entity.Order;
import by.xenon28082.shop.service.OrderService;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public boolean reserveProduct(Order order) throws SQLException {
        OrderDAO dao = new OrderDAOImpl();
        return dao.save(order) != null;
    }

    @Override
    public List<Order> getReservations(long userId) throws SQLException {
        OrderDAO dao = new OrderDAOImpl();
        List<Order> orders = dao.getOrders(userId);
        orders.sort(new OrderIdComparator());
        return orders;
    }

    @Override
    public boolean deleteReservation(long userId, long orderId, int amount) {
        OrderDAO dao = new OrderDAOImpl();
        return dao.deleteOrder(userId, orderId, amount);
    }

    @Override
    public boolean findReservation(long productId) {
        OrderDAO orderDAO = new OrderDAOImpl();
        return orderDAO.findByProductId(productId);
    }

    @Override
    public boolean deleteAllByProductId(long productId) {
        OrderDAO orderDAO = new OrderDAOImpl();
        return orderDAO.deleteAllByProductId(productId);
    }
}
