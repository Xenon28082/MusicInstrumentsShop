package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.entity.Order;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.ProductService;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.exception.ServiceException;
import by.xenon28082.shop.service.impl.OrderServiceImpl;
import by.xenon28082.shop.service.impl.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddToBasketCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddToBasketCommand.class);

    private final OrderService reserveService = new OrderServiceImpl();//ServiceFactory.getInstance().getOrderService();
    private final ProductService productService = ServiceFactory.getInstance().getProductService();

    private static final String ID = "id";
    private static final String PRODUCT_ID = "productId";
    private static final String PRODUCT_AMOUNT = "productAmount";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        LOGGER.info("Got to AddToBasketCommand");
        try {
            long userId = (Long) req.getSession().getAttribute(ID);
            long productId =  Long.parseLong(req.getParameter(PRODUCT_ID));
            int productAmount = Integer.parseInt(req.getParameter(PRODUCT_AMOUNT));

            Order order = new Order(userId, productId, productAmount);
            boolean isProductAdded = reserveService.reserveProduct(order);
            productService.updateProduct(productId, productAmount);
            if(isProductAdded){
                LOGGER.info("Order - " + order + "has been added(SUCCESS)");
            }else{
                LOGGER.info("Order - " + order + "failed to add (ERROR)");
            }
        } catch (NumberFormatException | ServiceException | DaoException e) {
            System.out.println("Number exception happened");
        }
        res.sendRedirect("FrontController?COMMAND=GET_PRODUCTS");
//        req.getRequestDispatcher("FrontController?COMMAND=GET_PRODUCTS").forward(req, res);
    }

}
