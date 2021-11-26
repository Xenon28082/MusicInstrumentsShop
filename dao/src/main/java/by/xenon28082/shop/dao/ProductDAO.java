package by.xenon28082.shop.dao;

import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.entity.Product;
import by.xenon28082.shop.entity.Vendor;

import java.util.List;

public interface ProductDAO extends EntityFacade<Product> {

    List<Product> findAllProducts() throws DaoException;
    List<Product> findProductsByType(String type) throws DaoException;
    String findProductVendor(long id) throws DaoException;
    long findVendorId(String name) throws DaoException;
    List<Vendor> getVendors() throws DaoException;
    boolean update(long productId, long productAmount) throws DaoException;
}
