package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.entity.Order;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.ProductService;
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
    OrderService reserveService = new OrderServiceImpl();
    ProductService productService = new ProductServiceImpl();
    private static final Logger logger = LoggerFactory.getLogger(AddToBasketCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        logger.info("Got to AddToBasketCommand");
        try {
            long userId = (Long) req.getSession().getAttribute("id");
            long productId =  Long.parseLong(req.getParameter("productId"));
            int productAmount = Integer.parseInt(req.getParameter("productAmount"));
            Order order = new Order(userId, productId, productAmount);
            boolean isProductAdded = reserveService.reserveProduct(order);
            productService.updateProduct(productId, productAmount);
            if(isProductAdded){
                logger.info("Order - " + order + "has been added(SUCCESS)");
            }else{
                logger.info("Order - " + order + "failed to add (ERROR)");
            }
        } catch (NumberFormatException e) {
            System.out.println("Number exception happened");
        }
        req.getRequestDispatcher("FrontController?COMMAND=GET_PRODUCTS").forward(req, res);
    }

}
