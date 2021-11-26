package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
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

    private final OrderService orderService = new OrderServiceImpl();//ServiceFactory.getInstance().getOrderService();
    private final ProductService productService = ServiceFactory.getInstance().getProductService();

    private final Validator validator = ValidatorImpl.getInstance();

    private static final String PRODUCT_ID = "productId";
    private static final String ADD_VALUE = "addValue";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException, ServiceException {
        LOGGER.info("Got to DeleteItemCommand");
        long productId = Long.parseLong(req.getParameter(PRODUCT_ID));
        long valueToAdd = Integer.parseInt(req.getParameter(ADD_VALUE));

        System.out.println("Id - " + productId);
        System.out.println("delete - " + valueToAdd);

        List<Long> longParams = new ArrayList<>();
        longParams.add(productId);
        longParams.add(valueToAdd);
        if (validator.validateIsNotPositive(validator.convertToStringList(longParams))) {
            LOGGER.info("Negative values");
            res.sendRedirect("FrontController?COMMAND=GET_PRODUCTS&message=negative");
        } else {
            valueToAdd = -valueToAdd;
            if (orderService.findReservation(productId)) {
                orderService.deleteAllByProductId(productId);
            }

            if (productService.updateProduct(productId, valueToAdd)) {
                LOGGER.info("Add complete productId - " + productId + " deleteValue - " + valueToAdd + " (SUCCESS)");
            } else {
                LOGGER.info("Add complete productId - " + productId + " deleteValue - " + valueToAdd + " (FAILED)");
            }
            res.sendRedirect("FrontController?COMMAND=GET_PRODUCTS");
//        req.getRequestDispatcher("FrontController?COMMAND=GET_PRODUCTS").forward(req, res);
        }
    }
}
