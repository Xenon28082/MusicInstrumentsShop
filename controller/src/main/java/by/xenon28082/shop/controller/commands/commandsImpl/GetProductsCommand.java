package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
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

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException, ServiceException {
        LOGGER.info("Got to GetProductsCommand");
        String type = req.getParameter(TYPE);
        List<Product> products = null;

        if (type == null || type.equals(""))
            products = productService.getProducts();
        else
            products = productService.getProductsByType(type);

        req.setAttribute("items", products);
        req.setAttribute("type", type);
        if (products.size() == 0) {
//            res.sendRedirect("itemsPage.jsp?message=noProducts");
            req.getRequestDispatcher("itemsPage.jsp?message=noProducts").forward(req, res);
        } else {
//            res.sendRedirect("itemsPage.jsp");
            req.getRequestDispatcher("itemsPage.jsp").forward(req, res);
        }
    }

}
