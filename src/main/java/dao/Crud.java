package dao;

import java.util.List;
import java.util.Optional;

public interface Crud<T> {
    public int insert(T obj);
    public int update(int id, T obj);
    public int delete(int id);
    public Optional<T> select(int id);
    public List<T> selectAll();
}
