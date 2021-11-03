package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.entity.Product;
import by.xenon28082.shop.service.ProductService;
import by.xenon28082.shop.service.UserService;
import by.xenon28082.shop.service.impl.ProductServiceImpl;
import by.xenon28082.shop.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GetProductsCommand implements Command {

    ProductService service = (ProductService) new ProductServiceImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        String type = null;
        type = req.getParameter("TYPE");
        List<Product> products = null;

        if(type != null)
            products = service.getProductsByType(type);
        else
            products = service.getProducts();

        req.setAttribute("items", products);
        req.setAttribute("type", type);
        req.getRequestDispatcher("itemsPage.jsp").forward(req, res);
    }

    @Override
    public void print() {
        System.out.println("Get products comand");
    }
}
