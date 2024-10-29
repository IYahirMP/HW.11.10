package dao.mysql;

import dao.interfaces.EmergencyContactDAO;
import models.EmergencyContact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dao.factories.MySQLDAOFactory.createConnection;

public class MySQLEmergencyContactDAO implements EmergencyContactDAO {
    /**
     * @param obj
     */
    @Override
    public int insert(EmergencyContact obj) {
        String sql = "insert into emergencyContact (name, phone, address, patientId) values(?, ?, ?, ?)";

        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getPhone());
            ps.setString(3, obj.getAddress());
            ps.setInt(4, obj.getPatientId());

            int affectedRows = ps.executeUpdate();
            return affectedRows;
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
    public int update(int id, EmergencyContact obj) {
        String sql = "update emergencyContact set " +
                "name = ?," +
                "phone = ?," +
                "address = ?," +
                "patientId = ? " +
                "where emergencyContactId = ?";

        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getPhone());
            ps.setString(3, obj.getAddress());
            ps.setInt(4, obj.getPatientId());
            ps.setInt(5, id);

            int affectedRows = ps.executeUpdate();
            return affectedRows;

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
        String sql = "delete from emergencyContact where emergencyContactId = ?";
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
    public Optional<EmergencyContact> select(int id) {
        String sql = "select * from emergencyContact where emergencyContactId = ?";
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
    public List<EmergencyContact> selectAll() {
        String sql = "select * from emergencyContact";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            List<EmergencyContact> list = new ArrayList<>();
            while(rs.next()){
                list.add(constructObject(rs));
            }
            return list;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return List.of();
    }

    public EmergencyContact constructObject(ResultSet rs) throws SQLException {
        return new EmergencyContact()
                .setEmergencyContactId(rs.getInt("emergencyContactId"))
                .setName(rs.getString("name"))
                .setPhone(rs.getString("phone"))
                .setAddress(rs.getString("address"))
                .setPatientId(rs.getInt("patientId"));
    }
}
