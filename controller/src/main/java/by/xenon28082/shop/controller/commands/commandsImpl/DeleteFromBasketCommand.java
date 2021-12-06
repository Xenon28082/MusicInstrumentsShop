package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
import by.xenon28082.shop.controller.validators.Validator;
import by.xenon28082.shop.controller.validators.ValidatorImpl;
import by.xenon28082.shop.entity.Product;
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
import java.util.Arrays;
import java.util.List;

public class DeleteFromBasketCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteFromBasketCommand.class);

    private static final OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private final ProductService productService = ServiceFactory.getInstance().getProductService();
    private final Validator validator = ValidatorImpl.getInstance();

    private static final String ID = "id";
    private static final String ORDER_ID = "orderId";
    private static final String PRODUCT_ID = "productId";
    private static final String PRODUCT_AMOUNT = "productAmount";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        LOGGER.info("Got to DeleteFromBasketCommand");

        final long id = (Long) req.getSession().getAttribute(ID);
        final long orderId = Long.parseLong(req.getParameter(ORDER_ID));
        final long productId = Long.parseLong(req.getParameter(PRODUCT_ID));
        final int amountToDelete = Integer.parseInt(req.getParameter(PRODUCT_AMOUNT));

        boolean hasNotDeleted = false;
        try {
            hasNotDeleted = orderService.findReservation(productId, id).getAmount() != 0;


            if (hasNotDeleted && validator.validateIsNotPositive(
                    Arrays.asList(String.valueOf(amountToDelete))
            )) {
                LOGGER.info("Value is not positive");
                res.sendRedirect("FrontController?COMMAND=SHOW_BASKET&message=failed");
            } else {
                Product product = productService.findProductById(productId);
                if (product.getStock() < amountToDelete) {
                    LOGGER.info("Can't delete more then have");
                    res.sendRedirect("FrontController?COMMAND=SHOW_BASKET&message=more");
                } else {
                    boolean isDeleted = orderService.deleteReservation(id, orderId, amountToDelete, hasNotDeleted);
                    productService.updateProduct(productId, -amountToDelete);
                    if (isDeleted) {
                        LOGGER.info("Delete order: id - " + id + " orderId - " + orderId + " toDelete - " + amountToDelete + " (SUCCESS)");
                    } else {
                        LOGGER.info("Delete order: id - " + id + " orderId - " + orderId + " toDelete - " + amountToDelete + " (FAILED)");
                    }
                    res.sendRedirect("FrontController?COMMAND=SHOW_BASKET");
                }
            }
        } catch (ServiceException | IOException e) {
            throw new ControllerException(e);
        }
    }

}
