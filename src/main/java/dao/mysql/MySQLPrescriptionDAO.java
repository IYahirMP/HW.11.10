package dao.mysql;

import dao.interfaces.PrescriptionDAO;
import models.Prescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dao.factories.MySQLDAOFactory.createConnection;

public class MySQLPrescriptionDAO implements PrescriptionDAO {
    /**
     * @param obj
     */
    @Override
    public int insert(Prescription obj) {
        String sql = "insert into Prescription (patientId, diagnose) values(?, ?)";

        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, obj.getPatientId());
            ps.setString(2, obj.getDiagnose());

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
    public int update(int id, Prescription obj) {
        String sql = "update Prescription set " +
                "patientId = ?," +
                "diagnose = ? " +
                "where prescriptionId = ?";

        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, obj.getPatientId());
            ps.setString(2, obj.getDiagnose());
            ps.setInt(3, id);

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
        String sql = "delete from prescription where prescriptionId = ?";
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
    public Optional<Prescription> select(int id) {
        String sql = "select * from prescription where prescriptionId = ?";
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
    public List<Prescription> selectAll() {
        String sql = "select * from prescription";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            List<Prescription> list = new ArrayList<>();
            while(rs.next()){
                list.add(constructObject(rs));
            }
            return list;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return List.of();
    }

    public Prescription constructObject(ResultSet rs) throws SQLException {
        return new Prescription()
                .setPrescriptionId(rs.getInt("prescriptionId"))
                .setPatientId(rs.getInt("patientId"))
                .setDiagnose(rs.getString("diagnose"));
    }
}
