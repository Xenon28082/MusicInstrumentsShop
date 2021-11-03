package by.xenon28082.shop.service.impl;

import by.xenon28082.shop.dao.ReserveDAO;
import by.xenon28082.shop.dao.impl.ReserveDAOImpl;
import by.xenon28082.shop.entity.Reserve;
import by.xenon28082.shop.service.ReserveService;

import java.sql.SQLException;
import java.util.List;

public class ReserveServiceImpl implements ReserveService {
    @Override
    public boolean reserveProduct(Reserve reserve) throws SQLException {
        ReserveDAO dao = new ReserveDAOImpl();
        return dao.save(reserve) != null;
    }

    @Override
    public List<Reserve> getReservations(long userId) throws SQLException {
        ReserveDAO dao = new ReserveDAOImpl();
        return dao.getOrders(userId);
    }

    @Override
    public boolean deleteReservation(long userId, long orderId, int amount) {
        ReserveDAO dao = new ReserveDAOImpl();
        return dao.deleteOrder(userId, orderId, amount);
    }
}
