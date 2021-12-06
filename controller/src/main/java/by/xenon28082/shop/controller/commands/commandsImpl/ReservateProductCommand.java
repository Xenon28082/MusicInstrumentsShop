package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReservateProductCommand implements Command {
    private final Logger LOGGER = LoggerFactory.getLogger(ReservateProductCommand.class);
    private static final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    private static final String ADD = "add";
    private static final String DELETE = "delete";
    private static final String IS_RESERVED = "isreserved";
    private static final String ORDER_ID = "orderId";


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        LOGGER.info("Got to ReservateProductCommand");
        try {
            String choice = req.getParameter(IS_RESERVED);
            String orderId = req.getParameter(ORDER_ID);
            boolean isReserved = false;
            switch (choice) {
                case ADD: {
                    isReserved = true;
                    break;
                }
                case DELETE: {
                    isReserved = false;
                    break;
                }
            }
            boolean hasAdded = false;

            hasAdded = orderService.reserveOrder(isReserved, Long.parseLong(orderId));


            if (hasAdded) {
                res.sendRedirect("FrontController?COMMAND=SHOW_BASKET&message=success");
            } else {
                res.sendRedirect("FrontController?COMMAND=SHOW_BASKET&message=failed");
            }
        } catch (ServiceException | IOException e) {
            LOGGER.info("ServiceException");
            throw new ControllerException(e);
        }
    }
}
