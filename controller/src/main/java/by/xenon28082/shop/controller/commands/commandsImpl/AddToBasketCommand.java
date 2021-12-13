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
import java.util.Arrays;

public class AddToBasketCommand implements Command {

    private final Logger LOGGER = LoggerFactory.getLogger(AddToBasketCommand.class);
    private final Validator validator = ValidatorImpl.getInstance();


    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private final ProductService productService = ServiceFactory.getInstance().getProductService();

    private final String ID = "id";
    private final String PRODUCT_ID = "productId";
    private final String PRODUCT_AMOUNT = "productAmount";
    private final String PAGE = "page";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        LOGGER.info("Got to AddToBasketCommand");
        String userId = String.valueOf(req.getSession().getAttribute(ID));
        String productId = req.getParameter(PRODUCT_ID);
        String productAmount = req.getParameter(PRODUCT_AMOUNT);
        String page = req.getParameter(PAGE);
        try {
            if (validator.validateIsValidNumbers(Arrays.asList(userId, productId, productAmount))) {
                LOGGER.info("negative values");
                res.sendRedirect("FrontController?COMMAND=GET_PRODUCTS&page=" + page + "&shift=3&message=negative");
            } else {
                Product product = productService.findProductById(Long.parseLong(productId));
                if (product.getStock() < Long.parseLong(productAmount)) {
                    LOGGER.info("Stock is less then purchase amount");
                    res.sendRedirect("FrontController?COMMAND=GET_PRODUCTS&page=" + page + "&shift=3&message=less");
                } else {
                    Order order = new Order(Long.parseLong(userId), Long.parseLong(productId), Integer.parseInt(productAmount));
                    boolean isProductAdded = orderService.reserveProduct(order);
                    if (isProductAdded) {
                        productService.updateProduct(Long.parseLong(productId), Integer.parseInt(productAmount));
                        LOGGER.info("Order - " + order + "has been added(SUCCESS)");
                    } else {
                        LOGGER.info("Order - " + order + "failed to add (ERROR)");
                    }
                    res.sendRedirect("FrontController?COMMAND=GET_PRODUCTS&page=" + page + "&shift=3");
                }
            }
        } catch (IOException | ServiceException e) {
            throw new ControllerException(e);
        }

    }

}
