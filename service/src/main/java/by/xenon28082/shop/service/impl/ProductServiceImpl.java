package by.xenon28082.shop.service.impl;

import by.xenon28082.shop.dao.ProductDAO;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.dao.impl.ProductDAOImpl;
import by.xenon28082.shop.dao.impl.comparators.ProductIdComparator;
import by.xenon28082.shop.entity.Product;
import by.xenon28082.shop.entity.Vendor;
import by.xenon28082.shop.service.ProductService;
import by.xenon28082.shop.service.exception.ServiceException;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO dao;

    public ProductServiceImpl(final ProductDAO productDAO){
        this.dao = productDAO;
    }

    @Override
    public List<Product> getProducts() throws ServiceException {
        List<Product> products = null;
        try {
            products = dao.findAllProducts();
            products.sort(new ProductIdComparator());
            return products;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    public List<Product> getProductsByType(String type) throws ServiceException {
        try {
            return dao.findProductsByType(type);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vendor> getVendors() throws ServiceException {
        try {
            return dao.getVendors();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Product addNewProduct(Product product) throws ServiceException{
        try {
            return dao.save(product);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateProduct(long productId, long amount) throws ServiceException {
        try {
            return dao.update(productId, amount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
