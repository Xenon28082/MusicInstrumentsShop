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
        System.out.println("Reserve");
        System.out.println("User id - " + reserve.getUserId());
        System.out.println("Prod id - " + reserve.getProductId());
        System.out.println("Amount - " + reserve.getAmount());
        System.out.println("______________________");
//        return dao.save(reserve);
        return dao.save(reserve) != null;
    }

    @Override
    public List<Reserve> getReservations(int userId) throws SQLException {
        ReserveDAO dao = new ReserveDAOImpl();
        return dao.getOrders(userId);
    }

    @Override
    public boolean deleteReservation(long userId, long orderId) {
        ReserveDAO dao = new ReserveDAOImpl();
        return dao.deleteOrder(userId, orderId);
    }
}
