package dao.mybatis;

import dao.interfaces.ConsultationDAO;
import models.Consultation;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static dao.factories.MyBatisDAOFactory.createConnection;

public class MyBatisConsultationDAO implements ConsultationDAO {

    /**
     * @param obj
     */
    @Override
    public int insert(Consultation obj) {
        try(SqlSession con = createConnection()){
            ConsultationDAO mapper = con.getMapper(ConsultationDAO.class);
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
    public int update(int id, Consultation obj) {
        try(SqlSession con = createConnection()){
            ConsultationDAO mapper = con.getMapper(ConsultationDAO.class);
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
            ConsultationDAO mapper = con.getMapper(ConsultationDAO.class);
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
    public Optional<Consultation> select(int id) {
        try(SqlSession con = createConnection()){
            ConsultationDAO mapper = con.getMapper(ConsultationDAO.class);
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
    public List<Consultation> selectAll() {

        try(SqlSession con = createConnection()){
            ConsultationDAO mapper = con.getMapper(ConsultationDAO.class);
            return mapper.selectAll();
        }catch(SQLException e){
            e.printStackTrace();
        }

        return List.of();
    }
}
