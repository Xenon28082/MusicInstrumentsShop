package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.exception.ServiceException;
import by.xenon28082.shop.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ReservateProductCommand implements Command {

    private static final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    private static final String ADD = "add";
    private static final String DELETE = "delete";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException, ServiceException, DaoException {
        String choice = req.getParameter("isreserved");
        String orderId = req.getParameter("orderId");
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
        boolean hasAdded = orderService.reserveOrder(isReserved, Long.parseLong(orderId));

        if (hasAdded){
            System.out.println("Ok");
        }
        else{
            System.out.println("Not Ok");
        }
        res.sendRedirect("FrontController?COMMAND=SHOW_BASKET");
    }
}
