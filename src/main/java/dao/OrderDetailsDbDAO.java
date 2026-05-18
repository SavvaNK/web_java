package dao;

import domain.OrderDetails;
import domain.Product;
import exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * DAO для таблицы order_details.
 */
public class OrderDetailsDbDAO implements RepositoryDAO<OrderDetails> {
    private static final String SELECT_ALL = "SELECT id, product_id, count FROM order_details ORDER BY id ASC";
    private static final String SELECT_BY_ID = "SELECT id, product_id, count FROM order_details WHERE id = ?";
    private static final String INSERT = "INSERT INTO order_details(product_id, count) VALUES(?, ?)";
    private static final String UPDATE = "UPDATE order_details SET product_id = ?, count = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM order_details WHERE id = ?";

    private final ConnectionBuilder builder = new DbConnectionBuilder();
    private final ProductDbDAO productDao = new ProductDbDAO();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(OrderDetails orderDetails) throws DAOException {
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(INSERT, new String[]{"id"})) {
            pst.setLong(1, orderDetails.getProductId());
            pst.setInt(2, orderDetails.getCount());
            pst.executeUpdate();
            try (ResultSet keys = pst.getGeneratedKeys()) {
                return keys.next() ? keys.getLong("id") : -1L;
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(OrderDetails orderDetails) throws DAOException {
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(UPDATE)) {
            pst.setLong(1, orderDetails.getProductId());
            pst.setInt(2, orderDetails.getCount());
            pst.setLong(3, orderDetails.getId());
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
    public OrderDetails findById(Long id) throws DAOException {
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(SELECT_BY_ID)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next() ? fillOrderDetails(rs, productDao.findAll()) : null;
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<OrderDetails> findAll() throws DAOException {
        List<OrderDetails> details = new LinkedList<>();
        List<Product> products = productDao.findAll();
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(SELECT_ALL); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                details.add(fillOrderDetails(rs, products));
            }
            return details;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    private OrderDetails fillOrderDetails(ResultSet rs, List<Product> products) throws SQLException {
        Long productId = rs.getLong("product_id");
        OrderDetails details = new OrderDetails();
        details.setId(rs.getLong("id"));
        details.setProductId(productId);
        details.setCount(rs.getInt("count"));
        details.setProduct(productDao.findById(productId, products));
        return details;
    }

    public OrderDetails findById(Long id, List<OrderDetails> detailsList) {
        if (detailsList == null) {
            return null;
        }
        for (OrderDetails details : detailsList) {
            if (details.getId().equals(id)) {
                return details;
            }
        }
        return null;
    }
}
