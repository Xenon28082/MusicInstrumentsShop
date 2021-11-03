package by.xenon28082.shop.dao;

import by.xenon28082.shop.entity.Reserve;

import java.sql.SQLException;
import java.util.List;

public interface ReserveDAO extends EntityFacade<Reserve>{
    List<Reserve> getOrders(long userId) throws SQLException;
    boolean deleteOrder(long userId, long orderId, int amount);
}
