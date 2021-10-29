package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.service.ReserveService;
import by.xenon28082.shop.service.impl.ReserveServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteFromBasketCommand implements Command {
    ReserveService reserveService = new ReserveServiceImpl();
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        reserveService.deleteReservation((Long) req.getSession().getAttribute("id"), Long.parseLong(req.getParameter("productId")));
        req.getRequestDispatcher("FrontController?COMMAND=SHOW_BASKET").forward(req, res);
    }

    @Override
    public void print() {
        System.out.println("Delete from Basket command");
    }
}
