package by.xenon28082.shop.dao.impl;

import by.xenon28082.shop.dao.ProductDAO;
import by.xenon28082.shop.dao.databaseConnection.DataBaseConfig;
import by.xenon28082.shop.entity.Product;
import by.xenon28082.shop.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private static final String FIND_ALL_PRODUCTS_QUERY = "SELECT * FROM products";
    private static final String FIND_PRODUCTS_BY_TYPE_QUERY = "SELECT * FROM products WHERE product_type = ?";
    private static final String FIND_PRODUCT_BY_ID_QUERY = "SELECT * FROM products WHERE product_id = ?";
    private static final String FIND_VENDOR_QUERY = "SELECT * FROM vendors WHERE vendor_id = ?";

    @Override
    public Product save(Product product) throws SQLException {
        return null;
    }

    @Override
    public Product find(Product product) throws SQLException {
        return null;
    }

    @Override
    public Product findOne(long id) {

        Connection connection = null;
        try {
            connection = DataBaseConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Product foundProduct = new Product();
            while(resultSet.next()){
                foundProduct.setName(resultSet.getString(2));
                foundProduct.setPrice(resultSet.getLong(3));
                foundProduct.setStock(resultSet.getLong(4));
                foundProduct.setType(resultSet.getString(5));
                foundProduct.setVendor(resultSet.getString(6));
            }
            return foundProduct;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public List<Product> findAll(int row) {
        return null;
    }

    @Override
    public long countAll() throws SQLException {
        return 0;
    }

    @Override
    public List<Product> findAllProducts() {
        try {
            Connection connection = DataBaseConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_PRODUCTS_QUERY);
            ArrayList<Product> products = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Product productFound = new Product(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getLong(4),
                        resultSet.getString(5),
                        findProductVendor(resultSet.getLong(6))
                );
                products.add(productFound);
            }

            return products;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> findProductsByType(String type) {
        try {
            Connection connection = DataBaseConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCTS_BY_TYPE_QUERY);
            preparedStatement.setString(1, type);
            ArrayList<Product> products = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Product productFound = new Product(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getLong(4),
                        resultSet.getString(5),
                        findProductVendor(resultSet.getLong(6))
                );
                products.add(productFound);
            }

            return products;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public String findProductVendor(long id) {
        try {
            Connection connection = DataBaseConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_VENDOR_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString(2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
