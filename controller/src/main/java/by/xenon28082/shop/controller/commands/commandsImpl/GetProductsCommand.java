package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
import by.xenon28082.shop.entity.Product;
import by.xenon28082.shop.service.ProductService;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.UserService;
import by.xenon28082.shop.service.exception.ServiceException;
import by.xenon28082.shop.service.impl.ProductServiceImpl;
import by.xenon28082.shop.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GetProductsCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetProductsCommand.class);

    private final ProductService productService = ServiceFactory.getInstance().getProductService();

    private static final String TYPE = "TYPE";
    private static final String PAGE = "page";
    private static final String SHIFT = "shift";


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        LOGGER.info("Got to GetProductsCommand");
        String type = req.getParameter(TYPE);
        int shift = 0;
        int page = 0;
        try {
            page = Integer.parseInt(req.getParameter(PAGE));
            shift = Integer.parseInt(req.getParameter(SHIFT));
        } catch (NumberFormatException e) {
            LOGGER.info("Null param PAGE or SHIFT");
        }
        try {
            List<Product> products = null;

            long productsCount = 0;

            productsCount = productService.countProducts();


            if (type == null || type.equals(""))
                products = productService.getProducts(page, shift);
            else
                products = productService.getProductsByType(type);

            req.setAttribute("items", products);
            req.setAttribute("type", type);
            req.setAttribute("count", productsCount);
            if (products.size() == 0) {
                req.getRequestDispatcher("itemsPage.jsp?message=noProducts").forward(req, res);
            } else {
                req.getRequestDispatcher("itemsPage.jsp").forward(req, res);
            }
        } catch (ServiceException | ServletException | IOException e) {
            LOGGER.info("ServiceException");
            throw new ControllerException(e);
        }
    }

}
