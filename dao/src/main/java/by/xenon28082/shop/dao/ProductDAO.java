package by.xenon28082.shop.dao;

import by.xenon28082.shop.entity.Product;
import by.xenon28082.shop.entity.Vendor;

import java.util.List;

public interface ProductDAO extends EntityFacade<Product> {

    List<Product> findAllProducts();
    List<Product> findProductsByType(String type);
    String findProductVendor(long id);
    long findVendorId(String name);
    List<Vendor> getVendors();
    boolean update(long productId, long productAmount);
}
