package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
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

public class DeleteItemCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(DeleteItemCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        logger.info("Got to DeleteItemCommand");
        long productId = Long.parseLong(req.getParameter("productId"));
        long valueToDelete = Integer.parseInt(req.getParameter("deleteValue"));
        OrderService orderService = new OrderServiceImpl();
        if(orderService.findReservation(productId)){
            orderService.deleteAllByProductId(productId);
        }
        ProductService productService = new ProductServiceImpl();
        if(productService.updateProduct(productId, valueToDelete)){
            System.out.println("Delete complete");
            logger.info("Delete complete productId - " + productId + " deleteValue - " + valueToDelete + " (SUCCESS)");
        }
        else{
            logger.info("Delete complete productId - " + productId + " deleteValue - " + valueToDelete + " (FAILED)");
        }
        req.getRequestDispatcher("FrontController?COMMAND=GET_PRODUCTS").forward(req, res);

    }
}
