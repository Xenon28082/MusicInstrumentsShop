package by.xenon28082.shop.service;

import by.xenon28082.shop.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    List<Product> getProductsByType(String type);

}
