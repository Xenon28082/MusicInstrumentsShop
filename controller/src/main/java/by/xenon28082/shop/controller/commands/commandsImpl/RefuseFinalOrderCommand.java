package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RefuseFinalOrderCommand implements Command {
    private final Logger LOGGER = LoggerFactory.getLogger(RefuseFinalOrderCommand.class);
    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        String orderId = req.getParameter("orderId");
        try {
            if (orderService.refuseFinalOrder(Long.parseLong(orderId))) {
                res.sendRedirect("FrontController?COMMAND=GET_ORDERS&message=success");
            } else {
                res.sendRedirect("FrontController?COMMAND=GET_ORDERS&message=failed");
            }
        } catch (ServiceException | IOException e) {
            LOGGER.info("ServiceException");
            throw new ControllerException(e);
        }

    }
}
