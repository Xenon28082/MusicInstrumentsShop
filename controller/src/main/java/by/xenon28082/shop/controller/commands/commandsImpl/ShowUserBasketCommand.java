package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.entity.Order;
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
    OrderServiceImpl orderService = new OrderServiceImpl();
    private static final Logger logger = LoggerFactory.getLogger(LogoutCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        logger.info("Got to ShowUserBasketCommand");
        List<Order> reservs = orderService.getReservations((Long) req.getSession().getAttribute("id"));
        req.getSession(true).setAttribute("reservations", reservs);
        if (reservs.size() == 0) {
            logger.info("Basket is empty");
            req.getRequestDispatcher("UserBasket.jsp?message=basketEmpty").forward(req, res);
        }
        req.getRequestDispatcher("UserBasket.jsp").forward(req, res);

    }

}
