package by.xenon28082.shop.dao;

import by.xenon28082.shop.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO extends EntityFacade<Order>{
    List<Order> getOrders(long userId);
    boolean deleteOrder(long userId, long orderId, int amount);
}
