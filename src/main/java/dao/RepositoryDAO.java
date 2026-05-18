package dao;

import exception.DAOException;
import java.util.List;

/**
 * Обобщенный интерфейс репозитория для работы с таблицами базы данных.
 */
public interface RepositoryDAO<T> {
    Long insert(T o) throws DAOException;

    void update(T o) throws DAOException;

    void delete(Long id) throws DAOException;

    T findById(Long id) throws DAOException;

    List<T> findAll() throws DAOException;
}
