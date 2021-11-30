package by.xenon28082.shop.service;

import by.xenon28082.shop.DaoFactory;
import by.xenon28082.shop.service.impl.OrderServiceImpl;
import by.xenon28082.shop.service.impl.ProductServiceImpl;
import by.xenon28082.shop.service.impl.UserServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory();

    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private final UserService userService = new UserServiceImpl(daoFactory.getUserDao());
    private final ProductService productService = new ProductServiceImpl(daoFactory.getProductDao());
    private final OrderService orderService = new OrderServiceImpl(daoFactory.getOrderDao());

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public UserService getUserService() {
        return userService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public OrderService getOrderService() { return orderService;}
}