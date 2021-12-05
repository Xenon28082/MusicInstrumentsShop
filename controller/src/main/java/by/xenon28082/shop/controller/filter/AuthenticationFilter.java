package by.xenon28082.shop.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class AuthenticationFilter implements Filter {

    private final String COMMAND = "COMMAND";
    private final String ROLE = "role";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (req.getParameter(COMMAND).matches("CREATE_NEW_USER") ||
                req.getParameter(COMMAND).matches("FIND_USER") ||
                req.getParameter(COMMAND).matches("GET_PRODUCTS") ||
                req.getParameter(COMMAND).matches("ADD_TO_BASKET") ||
                req.getParameter(COMMAND).matches("SHOW_BASKET") ||
                req.getParameter(COMMAND).matches("DELETE_FROM_BASKET") ||
                req.getParameter(COMMAND).matches("LOGOUT") ||
                req.getParameter(COMMAND).matches("ADD_NEW_ITEM") ||
                req.getParameter(COMMAND).matches("GET_VENDORS") ||
                req.getParameter(COMMAND).matches("DELETE_SOME") ||
                req.getParameter(COMMAND).matches("GET_USER_INFO") ||
                req.getParameter(COMMAND).matches("UPDATE_USER") ||
                req.getParameter(COMMAND).matches("ADD_SOME") ||
                req.getParameter(COMMAND).matches("UPDATE_USER_LOGIN") ||
                req.getParameter(COMMAND).matches("UPDATE_USER_PASSWORD") ||
                req.getParameter(COMMAND).matches("RESERVATE_PRODUCT") ||
                req.getParameter(COMMAND).matches("SHOW_ORDER") ||
                req.getParameter(COMMAND).matches("CLOSE_ORDER") ||
                req.getParameter(COMMAND).matches("GET_ORDERS") ||
                req.getParameter(COMMAND).matches("ACCEPT_FINAL_ORDER") ||
                req.getParameter(COMMAND).matches("REFUSE_FINAL_ORDER") ||
                req.getSession().getAttribute(ROLE) != null) {

            chain.doFilter(request, response);
        } else {
            res.sendRedirect("index.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}