package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateFinalOrderCommand implements Command {

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        String orderId = req.getParameter("orderId");
        try {
            if(orderService.updateFinalOrder(Long.parseLong(orderId))) {
                res.sendRedirect("FrontController?COMMAND=GET_ORDERS&getAll=true&message=success");
            }else{
                res.sendRedirect("FrontController?COMMAND=GET_ORDERS&getAll=true&message=failed");
            }
        } catch (ServiceException | IOException e) {
            throw new ControllerException(e);
        }
    }
}
