package dao.mysql;

import dao.ServiceDAO;
import models.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dao.factories.MySQLDAOFactory.createConnection;

public class MySQLServiceDAO implements ServiceDAO {
    /**
     * @param obj
     */
    @Override
    public int insert(Service obj) {/*
        String sql = "insert into Service (name, age, address, phone) values(?, ?, ?, ?)";

        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, obj.getName());
            ps.setInt(2, obj.getAge());
            ps.setString(3, obj.getAddress());
            ps.setString(4, obj.getPhone());

            int affectedRows = ps.executeUpdate();
            return affectedRows;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return -1;*/
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @param id
     * @param obj
     */
    @Override
    public int update(int id, Service obj) {/*
        String sql = "update Service set " +
                "name = ?," +
                "age = ?," +
                "address = ?," +
                "phone = ? " +
                "where patientId = ?";

        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, obj.getName());
            ps.setInt(2, obj.getAge());
            ps.setString(3, obj.getAddress());
            ps.setString(4, obj.getPhone());
            ps.setInt(5, id);

            int affectedRows = ps.executeUpdate();
            return affectedRows;

        }catch(SQLException e){
            e.printStackTrace();
        }

        return -1;*/
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @param id
     */
    @Override
    public int delete(int id) {
        String sql = "delete from Service where serviceId = ?";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
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
    public Optional<Service> select(int id) {
        String sql = "select * from service where serviceId = ?";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return Optional.of(constructObject(rs));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    /**
     * @return
     */
    @Override
    public List<Service> selectAll() {
        String sql = "select * from service";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            List<Service> list = new ArrayList<>();
            while(rs.next()){
                list.add(constructObject(rs));
            }
            return list;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return List.of();
    }

    public Service constructObject(ResultSet rs) throws SQLException{
        return new Service()
                .setServiceId(rs.getInt("serviceId"))
                .setCost(rs.getInt("cost"))
                .setDescription(rs.getString("description"));
    }
}
