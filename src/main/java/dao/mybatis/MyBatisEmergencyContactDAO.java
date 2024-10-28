package dao.mybatis;

import dao.interfaces.EmergencyContactDAO;
import models.EmergencyContact;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static dao.factories.MyBatisDAOFactory.createConnection;

public class MyBatisEmergencyContactDAO implements EmergencyContactDAO {

    /**
     * @param obj
     */
    @Override
    public int insert(EmergencyContact obj) {
        try (SqlSession con = createConnection()) {
            EmergencyContactDAO mapper = con.getMapper(EmergencyContactDAO.class);
            int rowsAffected = mapper.insert(obj);
con.commit();
return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * @param id
     * @param obj
     */
    @Override
    public int update(int id, EmergencyContact obj) {
        try (SqlSession con = createConnection()) {
            EmergencyContactDAO mapper = con.getMapper(EmergencyContactDAO.class);
            int rowsAffected = mapper.update(id, obj);
con.commit();
return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * @param id
     */
    @Override
    public int delete(int id) {
        try (SqlSession con = createConnection()) {
            EmergencyContactDAO mapper = con.getMapper(EmergencyContactDAO.class);
            int rowsAffected = mapper.delete(id);
            con.commit();
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<EmergencyContact> select(int id) {
        try (SqlSession con = createConnection()) {
            EmergencyContactDAO mapper = con.getMapper(EmergencyContactDAO.class);
            return mapper.select(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    /**
     * @return
     */
    @Override
    public List<EmergencyContact> selectAll() {

        try (SqlSession con = createConnection()) {
            EmergencyContactDAO mapper = con.getMapper(EmergencyContactDAO.class);
            return mapper.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return List.of();
    }
}
