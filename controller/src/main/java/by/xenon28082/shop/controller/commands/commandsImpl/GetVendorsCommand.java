package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.service.ProductService;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.exception.ServiceException;
import by.xenon28082.shop.service.impl.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class GetVendorsCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetVendorsCommand.class);
    private final ProductService productService = ServiceFactory.getInstance().getProductService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException, ServiceException {
        LOGGER.info("Got to GetVendorsCommand");
        req.setAttribute("vendors", productService.getVendors());
        req.getRequestDispatcher("addNewItemForm.jsp").forward(req, res);
    }
}
