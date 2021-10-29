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
//        long id = 1;
        System.out.println("ProdId = " + Long.parseLong(req.getParameter("productId")));
        System.out.println("UserId = " + (Long) req.getSession().getAttribute("id"));
        Reserve reserve = new Reserve((Long) req.getSession().getAttribute("id"), Long.parseLong(req.getParameter("productId")), 1);
        boolean isProductAdded = reserveService.reserveProduct(reserve);

//        if (isProductAdded) {
            req.getRequestDispatcher("FrontController?COMMAND=GET_PRODUCTS").forward(req, res);
//        } else {
//            req.setAttribute("message", "Failed: add product to basket");
//
//        }
    }

    @Override
    public void print() {
        System.out.println("Add to Basket command");
    }
}
