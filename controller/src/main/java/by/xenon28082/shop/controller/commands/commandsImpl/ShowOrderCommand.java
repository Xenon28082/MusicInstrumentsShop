package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.validators.Validator;
import by.xenon28082.shop.controller.validators.ValidatorImpl;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.entity.Order;
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
import java.util.List;

public class ShowOrderCommand implements Command {

    private final Logger LOGGER = LoggerFactory.getLogger(ShowOrderCommand.class);

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private final Validator validator = ValidatorImpl.getInstance();

    private static final String USER_ID = "userId";
    private static final String ORDER = "order";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException, ServiceException, DaoException {
        LOGGER.info("Got to ShowOrderCommand");

        String userId = String.valueOf(req.getSession().getAttribute("id"));
        List<Order> orders = orderService.getUserOrders(Long.parseLong(userId));
        if (orders.isEmpty()) {
            LOGGER.info("There is no ordered products");
            res.sendRedirect("orderPage.jsp?message=noOrder");
        } else {
            req.getSession().setAttribute("orders", orders);
            req.getRequestDispatcher("orderPage.jsp").forward(req, res);
        }

    }
}
