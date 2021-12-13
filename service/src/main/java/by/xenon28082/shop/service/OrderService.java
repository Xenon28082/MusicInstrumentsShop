package by.xenon28082.shop.service;

import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.entity.FinalOrder;
import by.xenon28082.shop.entity.Order;
import by.xenon28082.shop.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public interface OrderService {
    boolean reserveProduct(Order order) throws ServiceException;

    List<Order> getReservations(long userId) throws ServiceException;

    boolean deleteReservation(long userId, long orderId, int amount, boolean hasNotDeleted) throws ServiceException;

    Order findReservation(long productId, long userId) throws ServiceException;

    Order findOrderById(long orderId) throws ServiceException;

    boolean deleteAllByProductId(long productId) throws ServiceException;

    boolean reserveOrder(boolean isReserved, long orderId) throws ServiceException;

    List<Order> getUserOrders(long userId) throws ServiceException;

    boolean updateOrder(Order order) throws ServiceException;

    boolean closeOrder(long userId, List<Order> products) throws ServiceException;

    boolean checkOrderPresence(long userId, List<Order> products) throws ServiceException;

    ArrayList<ArrayList<Order>> getFinalOrders(boolean getAllAccepted) throws ServiceException;

    boolean acceptFinalOrder(long orderId) throws ServiceException;

    boolean refuseFinalOrder(long orderId) throws ServiceException;

    List<FinalOrder> getUserFinalOrders(long userId) throws ServiceException;

    boolean updateFinalOrder(long orderId) throws ServiceException;

    FinalOrder getFinalOrder(long userId, List<Order> orders) throws ServiceException;
}
