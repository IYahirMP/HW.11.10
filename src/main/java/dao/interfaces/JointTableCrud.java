package dao.interfaces;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface JointTableCrud<T> {
    int insert(T obj);
    int update(T obj);
    int delete(@Param("id1") int id1, @Param("id2") int id2);
    Optional<T> select(@Param("id1") int id1, @Param("id2") int id2);
    List<T> selectAll();
}
