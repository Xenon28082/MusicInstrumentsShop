package by.xenon28082.shop.controller.commands;

import by.xenon28082.shop.controller.exception.ControllerException;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface Command {
    void execute(HttpServletRequest req, HttpServletResponse res) throws ControllerException;

}
