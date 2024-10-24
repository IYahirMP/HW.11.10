package dao.interfaces;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface Crud<T> {
    public int insert(T obj);
    public int update(@Param("id") int id, @Param("obj") T obj);
    public int delete(int id);
    public Optional<T> select(int id);
    public List<T> selectAll();
}
