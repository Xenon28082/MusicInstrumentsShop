package by.xenon28082.shop.service;

import by.xenon28082.shop.entity.Product;
import by.xenon28082.shop.entity.Vendor;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    List<Product> getProductsByType(String type);
    List<Vendor> getVendors();
    Product addNewProduct(Product product) throws SQLException;
}
