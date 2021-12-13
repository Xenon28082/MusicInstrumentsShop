package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
import by.xenon28082.shop.controller.validators.Validator;
import by.xenon28082.shop.controller.validators.ValidatorImpl;
import by.xenon28082.shop.entity.Order;
import by.xenon28082.shop.entity.Product;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.ProductService;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteItemCommand implements Command {
    private final Logger LOGGER = LoggerFactory.getLogger(DeleteItemCommand.class);

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private final ProductService productService = ServiceFactory.getInstance().getProductService();

    private final Validator validator = ValidatorImpl.getInstance();

    private final String PRODUCT_ID = "productId";
    private final String DELETE_VALUE = "deleteValue";
    private final String PAGE = "page";
    private final String ID = "id";


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        LOGGER.info("Got to DeleteItemCommand");
        long productId = Long.parseLong(req.getParameter(PRODUCT_ID));
        long valueToDelete = Integer.parseInt(req.getParameter(DELETE_VALUE));
        String page = req.getParameter(PAGE);
        long userId = (long) req.getSession().getAttribute(ID);

        List<Long> longParams = new ArrayList<>();
        longParams.add(productId);
        longParams.add(valueToDelete);
        try {
            Product product = productService.findProductById(productId);
            if (!(product.getStock() == 0 && valueToDelete == 0) && validator.validateIsValidNumbers(validator.convertToStringList(longParams))) {
                LOGGER.info("Negative values");
                res.sendRedirect("FrontController?COMMAND=GET_PRODUCTS&page=" + page + "&shift=3&message=negative");
            } else {
                Order foundOrder = orderService.findReservation(productId, userId);
                if (!validator.validateIsNull(foundOrder)) {
                    foundOrder.setAmount(0);
                    orderService.updateOrder(foundOrder);
                }

                if (product.getStock() < valueToDelete) {
                    res.sendRedirect("FrontController?COMMAND=GET_PRODUCTS&page=" + page + "&shift=3&message=more");
                } else {
                    if (productService.updateProduct(productId, valueToDelete)) {
                        LOGGER.info("Delete complete productId - " + productId + " deleteValue - " + valueToDelete + " (SUCCESS)");
                    } else {
                        LOGGER.info("Delete complete productId - " + productId + " deleteValue - " + valueToDelete + " (FAILED)");
                    }
                    res.sendRedirect("FrontController?COMMAND=GET_PRODUCTS&page=" + page + "&shift=3");
                }
            }
        } catch (IOException | ServiceException e) {
            LOGGER.info("ServiceException");
            throw new ControllerException(e);
        }
    }
}
