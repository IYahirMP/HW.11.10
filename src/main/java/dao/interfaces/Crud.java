package dao.interfaces;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface Crud<T> {

    int insert(T obj);
    int update(@Param("id") int id, @Param("obj") T obj);
    int delete(int id);
    Optional<T> select(int id);
    List<T> selectAll();
}
