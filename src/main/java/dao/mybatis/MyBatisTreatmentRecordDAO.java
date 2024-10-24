package dao.mybatis;

import dao.interfaces.TreatmentRecordDAO;
import models.TreatmentRecord;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static dao.factories.MyBatisDAOFactory.createConnection;

public class MyBatisTreatmentRecordDAO implements TreatmentRecordDAO {

    /**
     * @param obj
     */
    @Override
    public int insert(TreatmentRecord obj) {
        try(SqlSession con = createConnection()){
            TreatmentRecordDAO mapper = con.getMapper(TreatmentRecordDAO.class);
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
    public int update(int id, TreatmentRecord obj) {
        try(SqlSession con = createConnection()){
            TreatmentRecordDAO mapper = con.getMapper(TreatmentRecordDAO.class);
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
            TreatmentRecordDAO mapper = con.getMapper(TreatmentRecordDAO.class);
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
    public Optional<TreatmentRecord> select(int id) {
        try(SqlSession con = createConnection()){
            TreatmentRecordDAO mapper = con.getMapper(TreatmentRecordDAO.class);
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
    public List<TreatmentRecord> selectAll() {
        try(SqlSession con = createConnection()){
            TreatmentRecordDAO mapper = con.getMapper(TreatmentRecordDAO.class);
            return mapper.selectAll();
        }catch(SQLException e){
            e.printStackTrace();
        }

        return List.of();
    }
}
