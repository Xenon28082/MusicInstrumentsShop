package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
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

    private final String VENDORS = "vendors";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        LOGGER.info("Got to GetVendorsCommand");
        try {
            req.setAttribute(VENDORS, productService.getVendors());
            req.getRequestDispatcher("addNewItemForm.jsp").forward(req, res);
        } catch (ServiceException | ServletException | IOException e) {
            LOGGER.info("ServiceException");
            throw new ControllerException(e);
        }

    }
}
