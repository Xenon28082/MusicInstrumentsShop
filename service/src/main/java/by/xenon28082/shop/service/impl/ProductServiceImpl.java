package by.xenon28082.shop.service.impl;

import by.xenon28082.shop.dao.ProductDAO;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.dao.impl.comparators.ProductIdComparator;
import by.xenon28082.shop.entity.Product;
import by.xenon28082.shop.entity.Vendor;
import by.xenon28082.shop.service.ProductService;
import by.xenon28082.shop.service.exception.ServiceException;
import by.xenon28082.shop.service.validators.Validator;
import by.xenon28082.shop.service.validators.ValidatorImpl;

import java.util.Arrays;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO dao;
    private final Validator validator = ValidatorImpl.getInstance();

    public ProductServiceImpl(final ProductDAO productDAO){
        this.dao = productDAO;
    }

    @Override
    public List<Product> getProducts(int page, int shift) throws ServiceException {
        List<Product> products = null;
        try {
            products = dao.getProductsPage(page, shift);
            products.sort(new ProductIdComparator());
            return products;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    public List<Product> getProductsByType(String type) throws ServiceException {
        try {
            if(validator.validateIsEmpty(Arrays.asList(type))){
                return null;
            }
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
            if(validator.validateIsNull(product)){
                return null;
            }
            return dao.save(product);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateProduct(long productId, long amount) throws ServiceException {
        try {
            if (validator.validateIsEmpty(Arrays.asList(String.valueOf(productId), String.valueOf(amount)))){
                return false;
            }
            return dao.update(productId, amount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long countProducts() throws ServiceException {
        try {
            return dao.countProducts();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Product findProductById(long productId) throws ServiceException {
        try {
            return dao.findById(productId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }


}
