package by.xenon28082.shop.service;

import by.xenon28082.shop.entity.Product;
import by.xenon28082.shop.entity.Vendor;
import by.xenon28082.shop.service.exception.ServiceException;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(int page, int shift) throws ServiceException;
    List<Product> getProductsByType(String type) throws ServiceException;
    List<Vendor> getVendors() throws ServiceException;
    Product addNewProduct(Product product) throws ServiceException;
    boolean updateProduct(long productId, long amount) throws ServiceException;
    long countProducts() throws ServiceException;
    Product findProductById(long productId) throws ServiceException;
}
