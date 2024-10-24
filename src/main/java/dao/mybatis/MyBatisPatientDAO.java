package dao.mybatis;

import dao.interfaces.PatientDAO;
import models.Patient;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static dao.factories.MyBatisDAOFactory.createConnection;

public class MyBatisPatientDAO implements PatientDAO {

    /**
     * @param obj
     */
    @Override
    public int insert(Patient obj) {
        try(SqlSession con = createConnection()){
            PatientDAO mapper = con.getMapper(PatientDAO.class);
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
    public int update(int id, Patient obj) {
        try(SqlSession con = createConnection()){
            PatientDAO mapper = con.getMapper(PatientDAO.class);
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
            PatientDAO mapper = con.getMapper(PatientDAO.class);
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
    public Optional<Patient> select(int id) {
        try(SqlSession con = createConnection()){
            PatientDAO mapper = con.getMapper(PatientDAO.class);
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
    public List<Patient> selectAll() {
        try(SqlSession con = createConnection()){
            PatientDAO mapper = con.getMapper(PatientDAO.class);
            return mapper.selectAll();
        }catch(SQLException e){
            e.printStackTrace();
        }

        return List.of();
    }
}
