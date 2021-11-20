package by.xenon28082.shop.service.impl;

import by.xenon28082.shop.dao.ProductDAO;
import by.xenon28082.shop.dao.impl.ProductDAOImpl;
import by.xenon28082.shop.dao.impl.comparators.ProductIdComparator;
import by.xenon28082.shop.entity.Product;
import by.xenon28082.shop.entity.Vendor;
import by.xenon28082.shop.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> getProducts() {
        ProductDAO dao = new ProductDAOImpl();
        List<Product> products = dao.findAllProducts();
        products.sort(new ProductIdComparator());
        return products;
    }

    public List<Product> getProductsByType(String type) {
        ProductDAO dao = new ProductDAOImpl();
        return dao.findProductsByType(type);
    }

    @Override
    public List<Vendor> getVendors() {
        ProductDAO dao = new ProductDAOImpl();
        return dao.getVendors();
    }

    @Override
    public Product addNewProduct(Product product) throws SQLException {
        ProductDAO dao = new ProductDAOImpl();
        return dao.save(product);
    }

    @Override
    public boolean updateProduct(long productId, long amount) {
        ProductDAO productDAO = new ProductDAOImpl();
        return productDAO.update(productId, amount);
    }
}
