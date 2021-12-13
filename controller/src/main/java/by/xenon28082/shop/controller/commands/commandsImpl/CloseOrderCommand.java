package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
import by.xenon28082.shop.controller.validators.Validator;
import by.xenon28082.shop.controller.validators.ValidatorImpl;
import by.xenon28082.shop.entity.Order;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CloseOrderCommand implements Command {

    private final Validator validator = ValidatorImpl.getInstance();

    private final Logger LOGGER = LoggerFactory.getLogger(CloseOrderCommand.class);

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    private final String ORDERS = "orders";
    private final String ID = "id";
    private final String PAID = "paid";
    private final String FINAL_PRICE = "finalPrice";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        LOGGER.info("Got to CloseOrderCommand");
        String userId = String.valueOf(req.getSession().getAttribute(ID));
        List<Order> orders = (List<Order>) req.getSession().getAttribute(ORDERS);
        String paid = req.getParameter(PAID);
        String finalPrice = req.getParameter(FINAL_PRICE);
        try {
            if (validator.validateIsEmpty(Arrays.asList(paid))) {
                LOGGER.info("Empty field");
                res.sendRedirect("FrontController?COMMAND=SHOW_ORDER&message=empty");
            } else {
                if (validator.validateIsValidNumbers(Arrays.asList(paid))) {
                    LOGGER.info("Negative value");
                    res.sendRedirect("FrontController?COMMAND=SHOW_ORDER&message=negative");
                } else {
                    if (Double.parseDouble(paid) != Double.parseDouble(finalPrice)) {
                        LOGGER.info("Not equal paid value and price value");
                        res.sendRedirect("FrontController?COMMAND=SHOW_ORDER&message=notequal");
                    } else {
                        if (orderService.checkOrderPresence(Long.parseLong(userId), orders) &&
                                !orderService.getFinalOrder(Long.parseLong(userId), orders).isRefused()) {
                            LOGGER.info("Such order already exists");
                            res.sendRedirect("FrontController?COMMAND=SHOW_ORDER&message=exists");
                        } else {
                            boolean hasUpdated = orderService.closeOrder(Long.parseLong(userId), orders);
                            if (hasUpdated) {
                                LOGGER.info("Order has been send");
                                res.sendRedirect("FrontController?COMMAND=SHOW_ORDER&message=success");
                            } else {
                                LOGGER.info("Failed to send order");
                                res.sendRedirect("FrontController?COMMAND=SHOW_ORDER&message=failed");
                            }
                        }
                    }
                }
            }
        } catch (IOException | ServiceException e) {
            LOGGER.info("ServiceException");
            throw new ControllerException(e);
        }
    }
}
