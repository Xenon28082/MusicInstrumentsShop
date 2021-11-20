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

public class DeleteFromBasketCommand implements Command {
    private static final Logger logger = LoggerFactory.getLogger(DeleteFromBasketCommand.class);

    OrderService orderService = new OrderServiceImpl();
    ProductService productService = new ProductServiceImpl();
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        logger.info("Got to DeleteFromBasketCommand");

        long id = (Long) req.getSession().getAttribute("id");
        long orderId = Long.parseLong(req.getParameter("orderId"));
        long productId = Long.parseLong(req.getParameter("productId"));
        int amountToDelete = Integer.parseInt(req.getParameter("productAmount"));

        boolean isDeleted = orderService.deleteReservation(id, orderId, amountToDelete);
        productService.updateProduct(productId, -amountToDelete);
        if(isDeleted){
            logger.info("Delete order: id - " + id + " orderId - " + orderId + " toDelete - " + amountToDelete + " (SUCCESS)");
        }
        else{
            logger.info("Delete order: id - " + id + " orderId - " + orderId + " toDelete - " + amountToDelete + " (FAILED)");
        }

        req.getRequestDispatcher("FrontController?COMMAND=SHOW_BASKET").forward(req, res);
    }

}
