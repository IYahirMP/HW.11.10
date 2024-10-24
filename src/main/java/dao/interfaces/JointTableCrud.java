package dao.interfaces;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface JointTableCrud<T> {
    public int insert(T obj);
    public int update(T obj);
    public int delete(@Param("id1") int id1, @Param("id2") int id2);
    public Optional<T> select(@Param("id1") int id1, @Param("id2") int id2);
    public List<T> selectAll();
}
