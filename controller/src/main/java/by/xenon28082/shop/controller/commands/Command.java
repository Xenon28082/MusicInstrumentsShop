package by.xenon28082.shop.controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface Command {
    void execute(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException;

    void print();
}
