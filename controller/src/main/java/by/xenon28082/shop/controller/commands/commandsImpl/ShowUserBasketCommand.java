package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.entity.Reserve;
import by.xenon28082.shop.service.impl.ReserveServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShowUserBasketCommand implements Command {
    ReserveServiceImpl reserveService = new ReserveServiceImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
//        Reserve reserve = new Reserve((Long) req.getSession().getAttribute("id"), Long.parseLong(req.getParameter("productId")), 1);
        List<Reserve> reservs = reserveService.getReservations((Long )req.getSession().getAttribute("id"));
        req.getSession(true).setAttribute("reservations", reservs);
        req.getRequestDispatcher("UserBasket.jsp").forward(req, res);

    }

    @Override
    public void print() {
        System.out.println("ShowUserBasket");
    }
}
