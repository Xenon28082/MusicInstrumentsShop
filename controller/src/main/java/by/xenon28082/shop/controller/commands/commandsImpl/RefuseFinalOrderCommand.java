package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RefuseFinalOrderCommand implements Command {
    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException, ServiceException, DaoException {
        String orderId = req.getParameter("orderId");
        boolean isUpdated = orderService.refuseFinalOrder(Long.parseLong(orderId));
        if(isUpdated){
            System.out.println("Ok");
        }else{
            System.out.println("Not ok");
        }
        res.sendRedirect("FrontController?COMMAND=GET_ORDERS");
    }
}
