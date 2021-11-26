package by.xenon28082.shop.service.impl;

import by.xenon28082.shop.DaoFactory;
import by.xenon28082.shop.dao.OrderDAO;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.dao.impl.OrderDAOImpl;
import by.xenon28082.shop.dao.impl.comparators.OrderIdComparator;
import by.xenon28082.shop.entity.Order;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.exception.ServiceException;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO dao = DaoFactory.getInstance().getOrderDao();



    @Override
    public boolean reserveProduct(Order order) throws ServiceException {
        try {
            return dao.save(order) != null;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getReservations(long userId) throws ServiceException {
        List<Order> orders = null;
        try {
            orders = dao.getOrders(userId);
            orders.sort(new OrderIdComparator());
            return orders;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteReservation(long userId, long orderId, int amount) throws ServiceException {
        try {
            return dao.deleteOrder(userId, orderId, amount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean findReservation(long productId) throws ServiceException {
        try {
            return dao.findByProductId(productId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteAllByProductId(long productId) throws ServiceException {
        try {
            return dao.deleteAllByProductId(productId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
