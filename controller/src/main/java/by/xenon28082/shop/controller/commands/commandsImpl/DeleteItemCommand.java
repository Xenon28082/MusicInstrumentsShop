package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.validators.Validator;
import by.xenon28082.shop.controller.validators.ValidatorImpl;
import by.xenon28082.shop.entity.Order;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.ProductService;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.exception.ServiceException;
import by.xenon28082.shop.service.impl.OrderServiceImpl;
import by.xenon28082.shop.service.impl.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteItemCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteItemCommand.class);

    private static final OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private final ProductService productService = ServiceFactory.getInstance().getProductService();

    private final Validator validator = ValidatorImpl.getInstance();

    private static final String PRODUCT_ID = "productId";
    private static final String DELETE_VALUE = "deleteValue";


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException, ServiceException {
        LOGGER.info("Got to DeleteItemCommand");
        long productId = Long.parseLong(req.getParameter(PRODUCT_ID));
        long valueToDelete = Integer.parseInt(req.getParameter(DELETE_VALUE));
        long userId = (long)req.getSession().getAttribute("id");

        List<Long> longParams = new ArrayList<>();
        longParams.add(productId);
        longParams.add(valueToDelete);
        if (validator.validateIsNotPositive(validator.convertToStringList(longParams))) {
            LOGGER.info("Negative values");
            res.sendRedirect("FrontController?COMMAND=GET_PRODUCTS&page=0&shift=3&message=negative");
        } else {
            Order foundOrder = orderService.findReservation(productId, userId);
            if (!validator.validateIsNull(foundOrder)) {
                foundOrder.setAmount(0);
                orderService.updateOrder(foundOrder);
            }
//            Order updatedOrder = orderService.findReservation(productId);
            if (productService.updateProduct(productId, valueToDelete)) {
                LOGGER.info("Delete complete productId - " + productId + " deleteValue - " + valueToDelete + " (SUCCESS)");
            } else {
                LOGGER.info("Delete complete productId - " + productId + " deleteValue - " + valueToDelete + " (FAILED)");
            }
            res.sendRedirect("FrontController?COMMAND=GET_PRODUCTS&page=0&shift=3");
        }
    }
}
