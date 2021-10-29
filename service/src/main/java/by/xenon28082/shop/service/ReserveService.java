package by.xenon28082.shop.service;

import by.xenon28082.shop.entity.Reserve;

import java.sql.SQLException;
import java.util.List;

public interface ReserveService {
    boolean reserveProduct(Reserve reserve) throws SQLException;
    List<Reserve> getReservations(long userId) throws SQLException;
    boolean deleteReservation(long userId, long orderId);
}
