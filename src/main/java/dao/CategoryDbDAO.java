package dao;

import domain.Category;
import exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * DAO для таблицы categories.
 */
public class CategoryDbDAO implements RepositoryDAO<Category> {
    private static final String SELECT_ALL = "SELECT id, category_name, description FROM categories ORDER BY category_name ASC";
    private static final String SELECT_BY_ID = "SELECT id, category_name, description FROM categories WHERE id = ?";
    private static final String INSERT = "INSERT INTO categories(category_name, description) VALUES(?, ?)";
    private static final String UPDATE = "UPDATE categories SET category_name = ?, description = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM categories WHERE id = ?";

    private final ConnectionBuilder builder = new DbConnectionBuilder();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Category category) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT, new String[]{"id"})) {
            pst.setString(1, category.getCategoryName());
            pst.setString(2, category.getDescription());
            pst.executeUpdate();
            try (ResultSet keys = pst.getGeneratedKeys()) {
                return keys.next() ? keys.getLong("id") : -1L;
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Category category) throws DAOException {
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(UPDATE)) {
            pst.setString(1, category.getCategoryName());
            pst.setString(2, category.getDescription());
            pst.setLong(3, category.getId());
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
    public Category findById(Long id) throws DAOException {
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(SELECT_BY_ID)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next() ? fillCategory(rs) : null;
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Category> findAll() throws DAOException {
        List<Category> categories = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                categories.add(fillCategory(rs));
            }
            return categories;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    private Category fillCategory(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("id"));
        category.setCategoryName(rs.getString("category_name"));
        category.setDescription(rs.getString("description"));
        return category;
    }

    public Category findById(Long id, List<Category> categories) {
        if (categories == null) {
            return null;
        }
        for (Category category : categories) {
            if (category.getId().equals(id)) {
                return category;
            }
        }
        return null;
    }
}
