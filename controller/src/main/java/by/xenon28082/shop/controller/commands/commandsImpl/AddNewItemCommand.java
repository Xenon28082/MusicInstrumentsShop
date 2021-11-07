package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.entity.Product;
import by.xenon28082.shop.service.ProductService;
import by.xenon28082.shop.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddNewItemCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        Product product = new Product(
                req.getParameter("productName"),
                Double.valueOf(req.getParameter("productPrice")),
                Long.parseLong(req.getParameter("productStock")),
                req.getParameter("productType"),
                req.getParameter("vendorSelect")
        );
        ProductService productService = new ProductServiceImpl();
        productService.addNewProduct(product);
        req.getRequestDispatcher("adminPage.jsp").forward(req, res);
    }
}
