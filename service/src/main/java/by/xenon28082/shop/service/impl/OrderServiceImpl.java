package by.xenon28082.shop.service.impl;

import by.xenon28082.shop.dao.OrderDAO;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.dao.impl.comparators.OrderIdComparator;
import by.xenon28082.shop.entity.Order;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.exception.ServiceException;
import by.xenon28082.shop.service.validators.Validator;
import by.xenon28082.shop.service.validators.ValidatorImpl;

import java.util.Arrays;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO dao;
    private final Validator validator = ValidatorImpl.getInstance();

    public OrderServiceImpl(final OrderDAO dao) {
        this.dao = dao;
    }

    @Override
    public boolean reserveProduct(Order order) throws ServiceException {
        try {
            if (validator.validateIsNull(order)) {
                return false;
            }
            return dao.save(order) != null;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getReservations(long userId) throws ServiceException {
        List<Order> orders = null;
        try {
            if (validator.validateIsNotPositive(userId)) {
                return null;
            }
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
            if (validator.validateIsNotPositive(Arrays.asList(String.valueOf(userId),
                    String.valueOf(orderId), String.valueOf(amount)))) {
                return false;
            }
            return dao.deleteOrder(userId, orderId, amount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean findReservation(long productId) throws ServiceException {
        try {
            if(validator.validateIsNotPositive(productId)){
                return false;
            }
            return dao.findByProductId(productId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteAllByProductId(long productId) throws ServiceException {
        try {
            if(validator.validateIsNotPositive(productId)){
                return false;
            }
            return dao.deleteAllByProductId(productId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean reserveOrder(boolean isReserved, long orderId) {
        if(validator.validateIsNotPositive(orderId)){
            return false;
        }
        return dao.updateIsReserved(isReserved, orderId);
    }
}
