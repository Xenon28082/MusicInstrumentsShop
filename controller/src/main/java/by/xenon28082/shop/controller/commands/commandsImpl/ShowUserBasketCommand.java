package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.entity.Order;
import by.xenon28082.shop.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShowUserBasketCommand implements Command {
    OrderServiceImpl reserveService = new OrderServiceImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        List<Order> reservs = reserveService.getReservations((Long )req.getSession().getAttribute("id"));
        req.getSession(true).setAttribute("reservations", reservs);
        System.out.println(reservs);
        req.getRequestDispatcher("UserBasket.jsp").forward(req, res);

    }

}
