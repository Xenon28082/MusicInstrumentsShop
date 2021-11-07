package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteFromBasketCommand implements Command {
    OrderService orderService = new OrderServiceImpl();
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        long id = (Long) req.getSession().getAttribute("id");
        long productId = Long.parseLong(req.getParameter("productId"));
        int amountToDelete = Integer.parseInt(req.getParameter("productAmount"));

        orderService.deleteReservation(id, productId, amountToDelete);
        req.getRequestDispatcher("FrontController?COMMAND=SHOW_BASKET").forward(req, res);
    }

}
