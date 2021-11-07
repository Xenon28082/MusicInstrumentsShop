package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.entity.Order;
import by.xenon28082.shop.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddToBasketCommand implements Command {
    OrderServiceImpl reserveService = new OrderServiceImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        try {
            long userId = (Long) req.getSession().getAttribute("id");
            long productId =  Long.parseLong(req.getParameter("productId"));
            int productAmount = Integer.parseInt(req.getParameter("productAmount"));
            Order order = new Order(userId, productId, productAmount);
            boolean isProductAdded = reserveService.reserveProduct(order);
        } catch (NumberFormatException e) {
            System.out.println("Number exception happened");
        }

        req.getRequestDispatcher("FrontController?COMMAND=GET_PRODUCTS").forward(req, res);
    }

}
