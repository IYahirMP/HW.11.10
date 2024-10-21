package dao.mysql;

import dao.PatientDAO;
import models.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dao.factories.MySQLDAOFactory.createConnection;

public class MySQLDoctorDAO implements PatientDAO {
    /**
     * @param obj
     */
    @Override
    public int insert(Patient obj) {
        String sql = "insert into Patient (name, age, address, phone) values(?, ?, ?, ?)";

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

        return -1;
    }

    /**
     * @param id
     * @param obj
     */
    @Override
    public int update(int id, Patient obj) {
        String sql = "update Patient set " +
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

        return -1;
    }

    /**
     * @param id
     */
    @Override
    public int delete(int id) {
        String sql = "delete from patient where patientId = ?";
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
    public Optional<Patient> select(int id) {
        String sql = "select * from patient where patientId = ?";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Patient patient = new Patient();
                patient.setPatientId(rs.getInt("patientId"));
                patient.setName(rs.getString("name"));
                patient.setAddress(rs.getString("address"));
                patient.setPhone(rs.getString("phone"));
                return Optional.of(patient);
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
    public List<Patient> selectAll() {
        String sql = "select * from patient";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            List<Patient> list = new ArrayList<>();
            while(rs.next()){
                Patient patient = new Patient();
                patient.setPatientId(rs.getInt("patientId"));
                patient.setName(rs.getString("name"));
                patient.setAddress(rs.getString("address"));
                patient.setPhone(rs.getString("phone"));
                list.add(patient);
            }
            return list;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return List.of();
    }
}
