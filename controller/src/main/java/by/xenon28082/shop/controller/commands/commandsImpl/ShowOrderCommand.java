package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
import by.xenon28082.shop.controller.validators.Validator;
import by.xenon28082.shop.controller.validators.ValidatorImpl;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.entity.Order;
import by.xenon28082.shop.entity.Product;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.ProductService;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowOrderCommand implements Command {

    private final Logger LOGGER = LoggerFactory.getLogger(ShowOrderCommand.class);

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private final ProductService productService = ServiceFactory.getInstance().getProductService();
    private final Validator validator = ValidatorImpl.getInstance();

    private static final String ID = "id";

    private double getSum(List<Product> products) {
        double sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        return sum;
    }

    private List<Product> getProductsFromOrder(List<Order> orders) throws ServiceException {
        List<Product> products = new ArrayList<>();
        for (Order order : orders) {
            Product product = productService.findProductById(order.getProductId());
            products.add(product);
        }
        return products;
    }

    private List<Order> setProductsToOrder(List<Product> products, List<Order> orders) {
        for (int i = 0; i < products.size(); i++) {
            orders.get(i).setProduct(products.get(i));
        }
        return orders;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        LOGGER.info("Got to ShowOrderCommand");

        String userId = String.valueOf(req.getSession().getAttribute(ID));
        List<Order> orders = null;
        try {
            orders = orderService.getUserOrders(Long.parseLong(userId));
            if (orders.isEmpty()) {
                LOGGER.info("There is no ordered products");
                res.sendRedirect("orderPage.jsp?message=noOrder");
            } else {
                List<Product> products = getProductsFromOrder(orders);
                orders = setProductsToOrder(products, orders);
                double sum = getSum(products);
                req.getSession().setAttribute("orders", orders);
                req.setAttribute("orderPrice", sum);
                req.getRequestDispatcher("orderPage.jsp").forward(req, res);
            }
        } catch (ServiceException | IOException | ServletException e) {
            LOGGER.info("ServiceException");
            throw new ControllerException(e);
        }


    }
}
