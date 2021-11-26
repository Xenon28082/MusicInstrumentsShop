package by.xenon28082.shop.dao.exception;


public class DaoException extends Exception {
    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message) {
        super(message);
    }
}