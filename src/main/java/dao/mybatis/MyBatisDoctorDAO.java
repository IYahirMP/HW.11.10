package dao.mybatis;

import dao.interfaces.DoctorDAO;
import models.Doctor;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static dao.factories.MyBatisDAOFactory.createConnection;

public class MyBatisDoctorDAO implements DoctorDAO {

    /**
     * @param obj
     */
    @Override
    public int insert(Doctor obj) {
        try(SqlSession con = createConnection()){
            DoctorDAO mapper = con.getMapper(DoctorDAO.class);
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
    public int update(int id, Doctor obj) {
        try(SqlSession con = createConnection()){
            DoctorDAO mapper = con.getMapper(DoctorDAO.class);
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
            DoctorDAO mapper = con.getMapper(DoctorDAO.class);
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
    public Optional<Doctor> select(int id) {
        try(SqlSession con = createConnection()){
            DoctorDAO mapper = con.getMapper(DoctorDAO.class);
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
    public List<Doctor> selectAll() {

        try(SqlSession con = createConnection()){
            DoctorDAO mapper = con.getMapper(DoctorDAO.class);
            return mapper.selectAll();
        }catch(SQLException e){
            e.printStackTrace();
        }

        return List.of();
    }
}
