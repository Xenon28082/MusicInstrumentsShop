package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.validators.Validator;
import by.xenon28082.shop.controller.validators.ValidatorImpl;
import by.xenon28082.shop.entity.Product;
import by.xenon28082.shop.service.ProductService;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.exception.ServiceException;
import by.xenon28082.shop.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddNewItemCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddNewItemCommand.class);

    private final ProductService productService = ServiceFactory.getInstance().getProductService();

    private final Validator validator = ValidatorImpl.getInstance();

    private static final String PRODUCT_NAME = "productName";
    private static final String PRODUCT_PRICE = "productPrice";
    private static final String PRODUCT_STOCK = "productStock";
    private static final String PRODUCT_TYPE = "productType";
    private static final String VENDOR_SELECT = "vendorSelect";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException, ServiceException {

        LOGGER.info("Got to AddNewItemCommand");


        String productName = req.getParameter(PRODUCT_NAME);
        String productPrice = req.getParameter(PRODUCT_PRICE);
        String productStock = req.getParameter(PRODUCT_STOCK);
        String productType = req.getParameter(PRODUCT_TYPE);
        String vendor = req.getParameter(VENDOR_SELECT);



        List<String> params = new ArrayList<>();
        params.add(productName);
        params.add(String.valueOf(productPrice));
        params.add(String.valueOf(productStock));
        params.add(productType);
        params.add(vendor);


        if (validator.validateIsNotPositive(params)) {
            LOGGER.info("Negative");
            res.sendRedirect("FrontController?COMMAND=GET_VENDORS&message=negative");
        } else {
            if (validator.validateIsEmpty(params)) {
                LOGGER.info("Empty");
                res.sendRedirect("FrontController?COMMAND=GET_VENDORS&message=empty");
            } else {
                Product product = new Product(
                        productName,
                        Double.parseDouble(productPrice),
                        Long.parseLong(productStock),
                        productType,
                        vendor
                );

                productService.addNewProduct(product);
                res.sendRedirect("FrontController?COMMAND=GET_VENDORS");
            }
        }
    }
}
