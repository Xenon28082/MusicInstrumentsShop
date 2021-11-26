package by.xenon28082.shop.dao;

import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO extends EntityFacade<Order>{
    List<Order> getOrders(long userId) throws DaoException;
    boolean deleteOrder(long userId, long orderId, int amount) throws DaoException;
    boolean findByProductId(long productId) throws DaoException;
    boolean deleteAllByProductId(long productId) throws DaoException;
}
