package by.xenon28082.shop;

import by.xenon28082.shop.dao.OrderDAO;
import by.xenon28082.shop.dao.ProductDAO;
import by.xenon28082.shop.dao.UserDAO;
import by.xenon28082.shop.dao.config.DataBaseConfig;
import by.xenon28082.shop.dao.databaseConnection.ConnectionPool;
import by.xenon28082.shop.dao.databaseConnection.Impl.ConnectionPoolImpl;
import by.xenon28082.shop.dao.impl.OrderDAOImpl;
import by.xenon28082.shop.dao.impl.ProductDAOImpl;
import by.xenon28082.shop.dao.impl.UserDAOImpl;

public class DaoFactory {

    private static final DaoFactory INSTANCE = new DaoFactory();

    private final ConnectionPool connectionPool = new ConnectionPoolImpl(new DataBaseConfig());
    private final UserDAO userDao = new UserDAOImpl(connectionPool);
    private final ProductDAO productDao = new ProductDAOImpl(connectionPool);
//    private final OrderDAO orderDao = new OrderDAOImpl(connectionPool);

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    public UserDAO getUserDao() {
        return userDao;
    }

    public ProductDAO getProductDao() {
        return productDao;
    }

//    public OrderDAO getOrderDao() {
//        return orderDao;
//    }
}
