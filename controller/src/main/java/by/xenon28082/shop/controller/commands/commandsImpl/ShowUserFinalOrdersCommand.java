package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
import by.xenon28082.shop.entity.FinalOrder;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowUserFinalOrdersCommand implements Command {
    private final Logger LOGGER = LoggerFactory.getLogger(ShowUserFinalOrdersCommand.class);

    private final String ID = "id";


    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        LOGGER.info("Got to ShowUserFinalOrdersCommand");
        String userId = String.valueOf(req.getSession().getAttribute(ID));
        try {
            List<FinalOrder> userFinalOrders = orderService.getUserFinalOrders(Long.parseLong(userId));
            req.setAttribute("finalOrders", userFinalOrders);
            req.getRequestDispatcher("userFinalOrdersPage.jsp").forward(req,res);
        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}
