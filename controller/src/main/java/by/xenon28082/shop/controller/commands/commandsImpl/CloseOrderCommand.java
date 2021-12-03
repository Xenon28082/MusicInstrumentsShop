package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.entity.Order;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CloseOrderCommand implements Command {

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException, ServiceException, DaoException {
        String userId = String.valueOf(req.getSession().getAttribute("id"));
        List<Order> orders = (List<Order>) req.getSession().getAttribute("orders");
        if (orderService.checkOrderPresence(Long.parseLong(userId), orders)) {
            res.sendRedirect("FrontController?COMMAND=SHOW_ORDER&message=exists");
        } else {
            boolean hasUpdated = orderService.closeOrder(Long.parseLong(userId), orders);
            if (hasUpdated) {
                res.sendRedirect("FrontController?COMMAND=SHOW_ORDER&message=success");
            } else {
                res.sendRedirect("FrontController?COMMAND=SHOW_ORDER&message=failed");
            }
        }
    }
}
