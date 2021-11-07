package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.service.ProductService;
import by.xenon28082.shop.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class GetVendorsCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        ProductService productService = new ProductServiceImpl();
        req.setAttribute("vendors", productService.getVendors());
        req.getRequestDispatcher("addNewItemForm.jsp").forward(req, res);
    }
}
