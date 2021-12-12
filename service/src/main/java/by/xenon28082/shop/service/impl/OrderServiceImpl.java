package by.xenon28082.shop.service.impl;

import by.xenon28082.shop.dao.OrderDAO;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.entity.FinalOrder;
import by.xenon28082.shop.entity.Order;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.exception.ServiceException;
import by.xenon28082.shop.service.validators.Validator;
import by.xenon28082.shop.service.validators.ValidatorImpl;

import java.util.ArrayList;
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
            return orders;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteReservation(long userId, long orderId, int amount, boolean hasNotDeleted) throws ServiceException {
        try {
            if (hasNotDeleted && validator.validateIsNotPositive(Arrays.asList(String.valueOf(userId),
                    String.valueOf(orderId), String.valueOf(amount)))) {
                return false;
            }
            return dao.deleteOrder(userId, orderId, amount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Order findReservation(long productId, long userId) throws ServiceException {
        try {
            if (validator.validateIsNotPositive(productId)) {
                return null;
            }
            return dao.findByProductId(productId, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Order findOrderById(long orderId) throws ServiceException {
        try {
            if (validator.validateIsNotPositive(orderId)) {
                return null;
            }
            return dao.findById(orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteAllByProductId(long productId) throws ServiceException {
        try {
            if (validator.validateIsNotPositive(productId)) {
                return false;
            }
            return dao.deleteAllByProductId(productId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean reserveOrder(boolean isReserved, long orderId) throws ServiceException {
        try {
            if (validator.validateIsNotPositive(orderId)) {
                return false;
            }
            return dao.updateIsReserved(isReserved, orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getUserOrders(long userId) throws ServiceException {
        try {
            if (validator.validateIsNotPositive(userId)) {
                return null;
            }
            return dao.getReservations(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateOrder(Order order) throws ServiceException {
        try {
            return dao.update(order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean closeOrder(long userId, List<Order> products) throws ServiceException {
        try {
            return dao.closeOrder(userId, products);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkOrderPresence(long userId, List<Order> products) throws ServiceException {
        try {
            return dao.checkOrderPresence(userId, products);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<ArrayList<Order>> getFinalOrders() throws ServiceException {
        try {
            return dao.getFinalOrders();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean acceptFinalOrder(long orderId) throws ServiceException {
        try {
            return dao.acceptFinalOrder(orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean refuseFinalOrder(long orderId) throws ServiceException {
        try {
            return dao.refuseFinalOrder(orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<FinalOrder> getUserFinalOrders(long userId) throws ServiceException {
        try {
            return dao.getUserFinalOrders(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
