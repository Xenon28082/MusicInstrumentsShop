package by.xenon28082.shop.dao;

import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.entity.FinalOrder;
import by.xenon28082.shop.entity.Order;

import java.util.ArrayList;
import java.util.List;

public interface OrderDAO extends EntityFacade<Order> {

    List<FinalOrder> getUserFinalOrders(long userId) throws DaoException;

    List<Order> getOrders(long userId) throws DaoException;

    boolean deleteOrder(long userId, long orderId, int amount) throws DaoException;

    Order findByProductId(long productId, long userId) throws DaoException;

    boolean deleteAllByProductId(long productId) throws DaoException;

    boolean updateIsReserved(boolean isReserved, long orderId) throws DaoException;

    List<Order> getReservations(long userId) throws DaoException;

    boolean closeOrder(long userId, List<Order> products) throws DaoException;

    boolean checkOrderPresence(long userId, List<Order> products) throws DaoException;

    ArrayList<ArrayList<Order>> getFinalOrders(boolean getAllAccepted) throws DaoException;

    boolean acceptFinalOrder(long orderId) throws DaoException;

    boolean refuseFinalOrder(long orderId) throws DaoException;

    boolean updateFinalOrder(long orderId) throws DaoException;
}
