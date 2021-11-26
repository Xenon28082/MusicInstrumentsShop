package by.xenon28082.shop.service;

import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.entity.Order;
import by.xenon28082.shop.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    boolean reserveProduct(Order order) throws SQLException, DaoException, ServiceException;
    List<Order> getReservations(long userId) throws SQLException, DaoException, ServiceException;
    boolean deleteReservation(long userId, long orderId, int amount) throws ServiceException;
    boolean findReservation(long productId) throws ServiceException;
    boolean deleteAllByProductId(long productId) throws ServiceException;
}
