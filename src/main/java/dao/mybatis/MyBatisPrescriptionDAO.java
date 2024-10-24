package dao.mybatis;

import dao.interfaces.PrescriptionDAO;
import models.Prescription;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static dao.factories.MyBatisDAOFactory.createConnection;

public class MyBatisPrescriptionDAO implements PrescriptionDAO {

    /**
     * @param obj
     */
    @Override
    public int insert(Prescription obj) {
        try(SqlSession con = createConnection()){
            PrescriptionDAO mapper = con.getMapper(PrescriptionDAO.class);
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
    public int update(int id, Prescription obj) {
        try(SqlSession con = createConnection()){
            PrescriptionDAO mapper = con.getMapper(PrescriptionDAO.class);
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
            PrescriptionDAO mapper = con.getMapper(PrescriptionDAO.class);
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
    public Optional<Prescription> select(int id) {
        try(SqlSession con = createConnection()){
            PrescriptionDAO mapper = con.getMapper(PrescriptionDAO.class);
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
    public List<Prescription> selectAll() {
        try(SqlSession con = createConnection()){
            PrescriptionDAO mapper = con.getMapper(PrescriptionDAO.class);
            return mapper.selectAll();
        }catch(SQLException e){
            e.printStackTrace();
        }

        return List.of();
    }
}
