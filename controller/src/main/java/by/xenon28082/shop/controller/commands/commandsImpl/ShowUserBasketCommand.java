package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.entity.Order;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.exception.ServiceException;
import by.xenon28082.shop.service.impl.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShowUserBasketCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutCommand.class);

    private final OrderService orderService = new OrderServiceImpl();//ServiceFactory.getInstance().getOrderService();

    private static final String ID = "id";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException, DaoException, ServiceException {
        LOGGER.info("Got to ShowUserBasketCommand");
        List<Order> reservs = orderService.getReservations((Long) req.getSession().getAttribute(ID));
        req.getSession(true).setAttribute("reservations", reservs);
        if (reservs.size() == 0) {
            LOGGER.info("Basket is empty");
            req.getRequestDispatcher("UserBasket.jsp?message=basketEmpty").forward(req, res);
        } else {
            req.getRequestDispatcher("UserBasket.jsp").forward(req, res);
        }

    }

}
