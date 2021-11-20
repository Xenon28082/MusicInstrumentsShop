package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.entity.Product;
import by.xenon28082.shop.service.ProductService;
import by.xenon28082.shop.service.UserService;
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
    private static final Logger logger = LoggerFactory.getLogger(GetProductsCommand.class);
    ProductService service = new ProductServiceImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        logger.info("Got to GetProductsCommand");
        String type = req.getParameter("TYPE");
        List<Product> products = null;

        if (type == null || type.equals(""))
            products = service.getProducts();
        else
            products = service.getProductsByType(type);

        req.setAttribute("items", products);
        req.setAttribute("type", type);
        if (products.size() == 0) {
            req.getRequestDispatcher("itemsPage.jsp?message=noProducts").forward(req, res);
        } else
            req.getRequestDispatcher("itemsPage.jsp").forward(req, res);
    }

}
