package dao.mybatis;

import dao.interfaces.MedicineDAO;
import models.Medicine;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static dao.factories.MyBatisDAOFactory.createConnection;

public class MyBatisMedicineDAO implements MedicineDAO {

    /**
     * @param obj
     */
    @Override
    public int insert(Medicine obj) {
        try(SqlSession con = createConnection()){
            MedicineDAO mapper = con.getMapper(MedicineDAO.class);
            return mapper.insert(obj);
        }catch(SQLException e){
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * @param id
     * @param obj
     */
    @Override
    public int update(int id, Medicine obj) {
        try(SqlSession con = createConnection()){
            MedicineDAO mapper = con.getMapper(MedicineDAO.class);
            return mapper.update(id, obj);
        }catch(SQLException e){
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * @param id
     */
    @Override
    public int delete(int id) {
        try(SqlSession con = createConnection()){
            MedicineDAO mapper = con.getMapper(MedicineDAO.class);
            return mapper.delete(id);
        }catch(SQLException e){
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Medicine> select(int id) {
        try(SqlSession con = createConnection()){
            MedicineDAO mapper = con.getMapper(MedicineDAO.class);
             return mapper.select(id);
        }catch(SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    /**
     * @return
     */
    @Override
    public List<Medicine> selectAll() {
        try(SqlSession con = createConnection()){
            MedicineDAO mapper = con.getMapper(MedicineDAO.class);
            return mapper.selectAll();
        }catch(SQLException e){
            e.printStackTrace();
        }

        return List.of();
    }
}
