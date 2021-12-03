package by.xenon28082.shop.service;

import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.entity.Order;
import by.xenon28082.shop.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrderService {
    boolean reserveProduct(Order order) throws SQLException, DaoException, ServiceException;

    List<Order> getReservations(long userId) throws SQLException, DaoException, ServiceException;

    boolean deleteReservation(long userId, long orderId, int amount, boolean hasNotDeleted) throws ServiceException;

    Order findReservation(long productId, long userId) throws ServiceException;

    boolean deleteAllByProductId(long productId) throws ServiceException;

    boolean reserveOrder(boolean isReserved, long orderId) throws ServiceException;

    List<Order> getUserOrders(long userId) throws ServiceException;

    boolean updateOrder(Order order) throws ServiceException;

    boolean closeOrder(long userId, List<Order> products) throws ServiceException;

    boolean checkOrderPresence(long userId, List<Order> products) throws ServiceException;

    ArrayList<ArrayList<Order>> getFinalOrders() throws ServiceException;

    boolean acceptFinalOrder(long orderId) throws ServiceException;

    boolean refuseFinalOrder(long orderId) throws ServiceException;
}
