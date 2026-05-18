package dao;

import domain.Category;
import domain.Product;
import exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * DAO для таблицы products.
 */
public class ProductDbDAO implements RepositoryDAO<Product> {
    private static final String SELECT_ALL = "SELECT id, category_id, product_name, description, price FROM products ORDER BY product_name ASC";
    private static final String SELECT_BY_ID = "SELECT id, category_id, product_name, description, price FROM products WHERE id = ?";
    private static final String INSERT = "INSERT INTO products(category_id, product_name, description, price) VALUES(?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE products SET category_id = ?, product_name = ?, description = ?, price = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM products WHERE id = ?";

    private final ConnectionBuilder builder = new DbConnectionBuilder();
    private final CategoryDbDAO categoryDao = new CategoryDbDAO();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Product product) throws DAOException {
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(INSERT, new String[]{"id"})) {
            pst.setLong(1, product.getCategoryId());
            pst.setString(2, product.getProductName());
            pst.setString(3, product.getDescription());
            pst.setBigDecimal(4, product.getPrice());
            pst.executeUpdate();
            try (ResultSet keys = pst.getGeneratedKeys()) {
                return keys.next() ? keys.getLong("id") : -1L;
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Product product) throws DAOException {
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(UPDATE)) {
            pst.setLong(1, product.getCategoryId());
            pst.setString(2, product.getProductName());
            pst.setString(3, product.getDescription());
            pst.setBigDecimal(4, product.getPrice());
            pst.setLong(5, product.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(DELETE)) {
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Product findById(Long id) throws DAOException {
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(SELECT_BY_ID)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next() ? fillProduct(rs, categoryDao.findAll()) : null;
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Product> findAll() throws DAOException {
        List<Product> products = new LinkedList<>();
        List<Category> categories = categoryDao.findAll();
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(SELECT_ALL); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                products.add(fillProduct(rs, categories));
            }
            return products;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    private Product fillProduct(ResultSet rs, List<Category> categories) throws SQLException {
        Long categoryId = rs.getLong("category_id");
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setCategoryId(categoryId);
        product.setProductName(rs.getString("product_name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setCategory(categoryDao.findById(categoryId, categories));
        return product;
    }

    public Product findById(Long id, List<Product> products) {
        if (products == null) {
            return null;
        }
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }
}
