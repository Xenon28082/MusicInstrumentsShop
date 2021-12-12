package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
import by.xenon28082.shop.controller.validators.Validator;
import by.xenon28082.shop.controller.validators.ValidatorImpl;
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

public class AddItemCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddItemCommand.class);

    private static final OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private final ProductService productService = ServiceFactory.getInstance().getProductService();

    private final Validator validator = ValidatorImpl.getInstance();

    private static final String PRODUCT_ID = "productId";
    private static final String ADD_VALUE = "addValue";
    private static final String ID = "id";
    private static final String PAGE = "page";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        LOGGER.info("Got to DeleteItemCommand");
        long productId = Long.parseLong(req.getParameter(PRODUCT_ID));
        long valueToAdd = Integer.parseInt(req.getParameter(ADD_VALUE));
        String page = req.getParameter(PAGE);
        long userId = (long) req.getSession().getAttribute(ID);


        List<Long> longParams = new ArrayList<>();
        longParams.add(productId);
        longParams.add(valueToAdd);
        try {
            if (validator.validateIsNotPositive(validator.convertToStringList(longParams))) {
                LOGGER.info("Negative values");
                res.sendRedirect("FrontController?COMMAND=GET_PRODUCTS&message=negative&page=" + page + "&shift=3");
            } else {
                valueToAdd = -valueToAdd;
                if (orderService.findReservation(productId, userId) != null) {
                    orderService.deleteAllByProductId(productId);
                }

                if (productService.updateProduct(productId, valueToAdd)) {
                    LOGGER.info("Add complete productId - " + productId + " deleteValue - " + valueToAdd + " (SUCCESS)");
                    res.sendRedirect("FrontController?COMMAND=GET_PRODUCTS&page=" + page + "&shift=3&message=success");
                } else {
                    LOGGER.info("Add complete productId - " + productId + " deleteValue - " + valueToAdd + " (FAILED)");
                    res.sendRedirect("FrontController?COMMAND=GET_PRODUCTS&page=" + page + "&shift=3&message=failed");
                }

            }
        } catch (IOException | ServiceException e) {
            LOGGER.info("ServiceException");
            throw new ControllerException(e);
        }
    }
}
