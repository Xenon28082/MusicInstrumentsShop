package by.xenon28082.shop.controller.commands.commandsImpl;

import by.xenon28082.shop.controller.commands.Command;
import by.xenon28082.shop.controller.exception.ControllerException;
import by.xenon28082.shop.controller.validators.Validator;
import by.xenon28082.shop.controller.validators.ValidatorImpl;
import by.xenon28082.shop.entity.FinalOrder;
import by.xenon28082.shop.entity.Order;
import by.xenon28082.shop.entity.Product;
import by.xenon28082.shop.service.OrderService;
import by.xenon28082.shop.service.ProductService;
import by.xenon28082.shop.service.ServiceFactory;
import by.xenon28082.shop.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ControlOrderCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControlOrderCommand.class);

    private final Validator validator = ValidatorImpl.getInstance();

    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();
    private final ProductService productService = ServiceFactory.getInstance().getProductService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException {
        LOGGER.info("Got to ControlOrderCommand");
        ArrayList<ArrayList<Order>> finalOrders = null;
        try {
            finalOrders = orderService.getFinalOrders();
            ArrayList<ArrayList<Product>> productsInFinalOrder = new ArrayList<>();
            if (finalOrders.size() == 0) {
                LOGGER.info("No finalOrders");
                res.sendRedirect("ordersStatusPage.jsp?message=noorders");
            } else {
                for (ArrayList<Order> orders :
                        finalOrders) {
                    ArrayList<Product> products = new ArrayList<>();
                    for (Order order :
                            orders) {
                        Product product = productService.findProductById(order.getProductId());
                        products.add(product);
                    }
                    productsInFinalOrder.add(products);
                }
                ArrayList<FinalOrder> finalOrder = new ArrayList<>();
                for (ArrayList<Product> products : productsInFinalOrder) {
                    FinalOrder newFinalOrder = new FinalOrder();
                    newFinalOrder.setProducts(products);
                    finalOrder.add(newFinalOrder);
                }
                for (int i = 0; i < finalOrder.size(); i++) {
                    finalOrder.get(i).setFinalOrderId(finalOrders.get(i).get(0).getOrderId());
                    finalOrder.get(i).setUserId(finalOrders.get(i).get(0).getUserId());
                }
                LOGGER.info("Success get finalOrders");
                req.setAttribute("finalOrders", productsInFinalOrder);
                req.setAttribute("finalOrdersIDs", finalOrders);
                req.setAttribute("userId", finalOrders.get(0).get(0).getUserId());
                req.setAttribute("test", finalOrder);
                req.getRequestDispatcher("ordersStatusPage.jsp").forward(req, res);
            }
        } catch (ServiceException | ServletException | IOException e) {
            LOGGER.info("ServiceException");
            throw new ControllerException(e);
        }
    }

}
