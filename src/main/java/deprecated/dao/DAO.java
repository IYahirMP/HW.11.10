package deprecated.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    void save(T entity);
    Optional<T> findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
}
