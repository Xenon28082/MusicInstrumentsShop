package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.entity.Order;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CloseOrderCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(CloseOrderCommand.class);

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    private final String ORDERS = "orders";
    private final String ID = "id";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        LOGGER.info("Got to CloseOrderCommand");
        String userId = String.valueOf(req.getSession().getAttribute(ID));
        List<Order> orders = (List<Order>) req.getSession().getAttribute(ORDERS);
        try {
            if (orderService.checkOrderPresence(Long.parseLong(userId), orders)) {
                LOGGER.info("Such order already exists");
                res.sendRedirect("FrontController?COMMAND=SHOW_ORDER&message=exists");
            } else {
                boolean hasUpdated = orderService.closeOrder(Long.parseLong(userId), orders);
                if (hasUpdated) {
                    LOGGER.info("Order has been send");
                    res.sendRedirect("FrontController?COMMAND=SHOW_ORDER&message=success");
                } else {
                    LOGGER.info("Failed to send order");
                    res.sendRedirect("FrontController?COMMAND=SHOW_ORDER&message=failed");
                }
            }
        } catch (IOException | ServiceException e) {
            LOGGER.info("ServiceException");
            throw new ControllerException(e);
        }
    }
}
