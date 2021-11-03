package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.entity.Reserve;
import by.xenon28082.shop.service.impl.ReserveServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddToBasketCommand implements Command {
    ReserveServiceImpl reserveService = new ReserveServiceImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        try {
            Reserve reserve = new Reserve((Long) req.getSession().getAttribute("id"), Long.parseLong(req.getParameter("productId")), Integer.valueOf(req.getParameter("productAmount")));
            boolean isProductAdded = reserveService.reserveProduct(reserve);
        } catch (NumberFormatException e) {
            System.out.println("Number exception happened");
        }

        req.getRequestDispatcher("FrontController?COMMAND=GET_PRODUCTS").forward(req, res);
    }

    @Override
    public void print() {
        System.out.println("Add to Basket command");
    }
}
