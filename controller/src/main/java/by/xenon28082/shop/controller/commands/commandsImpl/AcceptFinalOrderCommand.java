package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class AcceptFinalOrderCommand implements Command {
    private final Logger LOGGER = LoggerFactory.getLogger(AcceptFinalOrderCommand.class);

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    private final String ORDER_ID = "orderId";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        LOGGER.info("Got to AcceptFinalOrderCommand");
        String orderId = req.getParameter(ORDER_ID);
        try {
            if(orderService.acceptFinalOrder(Long.parseLong(orderId))){
                LOGGER.info("Order has been accepted");
                res.sendRedirect("FrontController?COMMAND=GET_ORDERS&message=success");
            }else{
                LOGGER.info("Failed to accept");
                res.sendRedirect("FrontController?COMMAND=GET_ORDERS&message=failed");
            }
        } catch (ServiceException | IOException e) {
            LOGGER.info("ServiceException");
            throw new ControllerException(e);
        }


    }
}
