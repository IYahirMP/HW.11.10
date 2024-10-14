package dao;

import java.util.List;
import java.util.Optional;

public interface Crud<T> {
    public void insert(T obj);
    public void update(int id, T obj);
    public void delete(int id);
    public Optional<T> select(int id);
    public List<T> selectAll();
}
