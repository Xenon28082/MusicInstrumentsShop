package by.xenon28082.shop.dao.impl;

import by.xenon28082.shop.dao.ProductDAO;
import by.xenon28082.shop.dao.config.DataBaseConfig;
import by.xenon28082.shop.dao.databaseConnection.ConnectionPool;
import by.xenon28082.shop.dao.exception.DaoException;
import by.xenon28082.shop.entity.Product;
import by.xenon28082.shop.entity.Vendor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl extends AbstractDAO implements ProductDAO {

    private static final String FIND_ALL_PRODUCTS_QUERY = "SELECT * FROM products";
    private static final String FIND_PRODUCTS_BY_TYPE_QUERY = "SELECT * FROM products WHERE product_type = ?";
    private static final String FIND_PRODUCT_BY_ID_QUERY = "SELECT * FROM products WHERE product_id = ?";
    private static final String FIND_VENDOR_QUERY = "SELECT * FROM vendors WHERE vendor_id = ?";
    private static final String GET_VENDORS_QUERY = "SELECT * FROM vendors";
    private static final String FIND_VENDOR_BY_NAME_QUERY = "SELECT * FROM vendors WHERE vendor_name = ?";
    private static final String ADD_NEW_PRODUCTS_QUERY = "INSERT INTO products (product_name, price, in_stock, product_type, vendor_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT_QUERY = "UPDATE products SET in_stock = in_stock - ? WHERE product_id = ?";
    private static final String DELETE_PRODUCT_QUERY = "DELETE FROM products WHERE product_id = ?";

    public ProductDAOImpl(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    public Product save(Product product) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection(false);
            preparedStatement = connection.prepareStatement(ADD_NEW_PRODUCTS_QUERY);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setLong(3, product.getStock());
            preparedStatement.setString(4, product.getType());
            preparedStatement.setLong(5, findVendorId(product.getVendor()));

            int rows = preparedStatement.executeUpdate();
            return rows != 0 ? new Product() : null;
        } catch (SQLException | DaoException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            retrieve(connection);
        }
    }

    @Override
    public Product find(Product product) {
        return null;
    }

    @Override
    public Product findById(long id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection(true);
            preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            Product foundProduct = new Product();
            while (resultSet.next()) {
                foundProduct.setName(resultSet.getString(2));
                foundProduct.setPrice(resultSet.getLong(3));
                foundProduct.setStock(resultSet.getLong(4));
                foundProduct.setType(resultSet.getString(5));
                foundProduct.setVendor(resultSet.getString(6));
            }
            return foundProduct;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            retrieve(connection);
        }
    }

    @Override
    public boolean update(Product product) {
        return false;
    }


    @Override
    public boolean delete(long productId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {

            connection = getConnection(true);
            preparedStatement = connection.prepareStatement(DELETE_PRODUCT_QUERY);
            preparedStatement.setLong(1, productId);

            return preparedStatement.executeUpdate() != 0;

        } catch (SQLException | DaoException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            retrieve(connection);
        }
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
    public List<Product> findAllProducts() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection(true);
            preparedStatement = connection.prepareStatement(FIND_ALL_PRODUCTS_QUERY);
            ArrayList<Product> products = new ArrayList<>();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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
        } catch (SQLException | DaoException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            retrieve(connection);
        }
    }

    @Override
    public List<Product> findProductsByType(String type) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection(true);
            preparedStatement = connection.prepareStatement(FIND_PRODUCTS_BY_TYPE_QUERY);
            preparedStatement.setString(1, type);
            ArrayList<Product> products = new ArrayList<>();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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
        } catch (SQLException | DaoException e) {
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            retrieve(connection);
        }
    }

    @Override
    public String findProductVendor(long id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection(true);
            preparedStatement = connection.prepareStatement(FIND_VENDOR_QUERY);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString(2);
        } catch (SQLException | DaoException e) {
            throw new DaoException(e);
        }finally {
            close(resultSet);
            close(preparedStatement);
            retrieve(connection);
        }

    }

    @Override
    public long findVendorId(String name) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection(true);
            preparedStatement = connection.prepareStatement(FIND_VENDOR_BY_NAME_QUERY);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException | DaoException e) {
            throw new DaoException(e);
        }finally {
            close(resultSet);
            close(preparedStatement);
            retrieve(connection);
        }
    }

    @Override
    public List<Vendor> getVendors() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection(true);
            preparedStatement = connection.prepareStatement(GET_VENDORS_QUERY);
            resultSet = preparedStatement.executeQuery();
            List<Vendor> vendorNames = new ArrayList<>();
            while (resultSet.next()) {
                Vendor vendor = new Vendor(
                        resultSet.getLong(1),
                        resultSet.getString(2)
                );
                vendorNames.add(vendor);
            }
            return vendorNames;
        } catch (SQLException | DaoException e) {
            throw new DaoException(e);
        }finally {
            close(resultSet);
            close(preparedStatement);
            retrieve(connection);
        }
    }

    @Override
    public boolean update(long productId, long productAmount) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection(true);
            preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_QUERY);
            preparedStatement.setLong(1, productAmount);
            preparedStatement.setLong(2, productId);
            int rows = preparedStatement.executeUpdate();
            Product gotProduct = findById(productId);
            if (gotProduct.getStock() == 0) {
                return delete(productId);
            }
            return rows != 0;
        } catch (SQLException | DaoException e) {
            throw new DaoException(e);
        }finally {
            close(preparedStatement);
            retrieve(connection);
        }

    }
}
