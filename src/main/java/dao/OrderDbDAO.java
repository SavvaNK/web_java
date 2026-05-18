package dao;

import domain.Order;
import domain.OrderDetails;
import exception.DAOException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * DAO для таблицы orders.
 */
public class OrderDbDAO implements RepositoryDAO<Order> {
    private static final String SELECT_ALL = "SELECT id, order_detail_id, number_order, order_date, status, total_order_amount FROM orders ORDER BY order_date DESC, id DESC";
    private static final String SELECT_BY_ID = "SELECT id, order_detail_id, number_order, order_date, status, total_order_amount FROM orders WHERE id = ?";
    private static final String INSERT = "INSERT INTO orders(order_detail_id, number_order, order_date, status, total_order_amount) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE orders SET order_detail_id = ?, number_order = ?, order_date = ?, status = ?, total_order_amount = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM orders WHERE id = ?";

    private final ConnectionBuilder builder = new DbConnectionBuilder();
    private final OrderDetailsDbDAO orderDetailsDao = new OrderDetailsDbDAO();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Order order) throws DAOException {
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(INSERT, new String[]{"id"})) {
            pst.setLong(1, order.getOrderDetailId());
            pst.setString(2, order.getNumberOrder());
            pst.setDate(3, Date.valueOf(order.getOrderDate()));
            pst.setString(4, order.getStatus());
            pst.setBigDecimal(5, order.getTotalOrderAmount());
            pst.executeUpdate();
            try (ResultSet keys = pst.getGeneratedKeys()) {
                return keys.next() ? keys.getLong("id") : -1L;
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Order order) throws DAOException {
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(UPDATE)) {
            pst.setLong(1, order.getOrderDetailId());
            pst.setString(2, order.getNumberOrder());
            pst.setDate(3, Date.valueOf(order.getOrderDate()));
            pst.setString(4, order.getStatus());
            pst.setBigDecimal(5, order.getTotalOrderAmount());
            pst.setLong(6, order.getId());
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
    public Order findById(Long id) throws DAOException {
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(SELECT_BY_ID)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next() ? fillOrder(rs, orderDetailsDao.findAll()) : null;
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Order> findAll() throws DAOException {
        List<Order> orders = new LinkedList<>();
        List<OrderDetails> detailsList = orderDetailsDao.findAll();
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(SELECT_ALL); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                orders.add(fillOrder(rs, detailsList));
            }
            return orders;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    private Order fillOrder(ResultSet rs, List<OrderDetails> detailsList) throws SQLException {
        Long orderDetailId = rs.getLong("order_detail_id");
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setOrderDetailId(orderDetailId);
        order.setNumberOrder(rs.getString("number_order"));
        order.setOrderDate(rs.getDate("order_date").toLocalDate());
        order.setStatus(rs.getString("status"));
        order.setTotalOrderAmount(rs.getBigDecimal("total_order_amount"));
        order.setOrderDetails(orderDetailsDao.findById(orderDetailId, detailsList));
        return order;
    }
}
