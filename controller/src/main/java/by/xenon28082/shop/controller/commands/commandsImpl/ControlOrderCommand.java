package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
import by.xenon28082.shop.controller.validators.Validator;
import by.xenon28082.shop.controller.validators.ValidatorImpl;
import by.xenon28082.shop.entity.Order;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ControlOrderCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControlOrderCommand.class);

    private final Validator validator = ValidatorImpl.getInstance();

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        LOGGER.info("Got to ControlOrderCommand");
        ArrayList<ArrayList<Order>> finalOrders = null;
        try {
            finalOrders = orderService.getFinalOrders();

            if (finalOrders.size() == 0) {
                LOGGER.info("No finalOrders");
                res.sendRedirect("ordersStatusPage.jsp?message=noorders");
            } else {
                LOGGER.info("Success get finalOrders");
                req.setAttribute("finalOrders", finalOrders);
                req.getRequestDispatcher("ordersStatusPage.jsp").forward(req, res);
            }
        } catch (ServiceException | ServletException | IOException e) {
            LOGGER.info("ServiceException");
            throw new ControllerException(e);
        }
    }

}
