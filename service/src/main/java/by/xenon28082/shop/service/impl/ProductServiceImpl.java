package by.xenon28082.shop.service.impl;

import by.xenon28082.shop.dao.ProductDAO;
import by.xenon28082.shop.dao.impl.ProductDAOImpl;
import by.xenon28082.shop.entity.Product;
import by.xenon28082.shop.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> getProducts() {
        ProductDAO dao = new ProductDAOImpl();
        return dao.findAllProducts();
    }

    public List<Product> getProductsByType(String type){
        ProductDAO dao = new ProductDAOImpl();
        return dao.findProductsByType(type);
    }
}
