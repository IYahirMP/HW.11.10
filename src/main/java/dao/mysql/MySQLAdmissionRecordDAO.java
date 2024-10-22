package dao.mysql;

import dao.AdmissionRecordDAO;
import dao.PatientDAO;
import models.AdmissionRecord;
import models.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dao.factories.MySQLDAOFactory.createConnection;

public class MySQLAdmissionRecordDAO implements AdmissionRecordDAO{
    /**
     * @param obj
     */
    @Override
    public int insert(AdmissionRecord obj) {
        String sql = "insert into AdmissionRecord (patientId, consultationId, admissionDate, dischargeDate, roomNumber, bedNumber) values(?, ?, ?, ?, ?, ?)";

        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, obj.getPatientId());
            ps.setInt(2, obj.getConsultationId());
            ps.setDate(3, java.sql.Date.valueOf(obj.getAdmissionDate()));
            ps.setDate(4, java.sql.Date.valueOf(obj.getDischargeDate()));
            ps.setInt(5, obj.getRoomNumber());
            ps.setInt(6, obj.getBedNumber());

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
    public int update(int id, AdmissionRecord obj) {
        String sql = "update Patient set " +
                "patientId = ?," +
                "consultationId = ?," +
                "admissionDate = ?," +
                "dischargeDate = ?," +
                "roomNumber = ?," +
                "bedNumber = ?" +
                "where patientId = ?";

        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, obj.getPatientId());
            ps.setInt(2, obj.getConsultationId());
            ps.setDate(3, java.sql.Date.valueOf(obj.getAdmissionDate()));
            ps.setDate(4, java.sql.Date.valueOf(obj.getDischargeDate()));
            ps.setInt(5, obj.getRoomNumber());
            ps.setInt(6, obj.getBedNumber());
            ps.setInt(7, id);

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
        String sql = "delete from AdmissionRecord where admissionId = ?";
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
    public Optional<AdmissionRecord> select(int id) {
        String sql = "select * from AdmissionRecord where admissionId = ?";
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
    public List<AdmissionRecord> selectAll() {
        String sql = "select * from AdmissionRecord";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            List<AdmissionRecord> list = new ArrayList<>();
            while(rs.next()){
                list.add(constructObject(rs));
            }
            return list;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return List.of();
    }

    public AdmissionRecord constructObject(ResultSet rs) throws SQLException {
        return new AdmissionRecord()
                .setAdmissionId(rs.getInt("admissionId"))
                .setPatientId(rs.getInt("patientId"))
                .setConsultationId(rs.getInt("consultationId"))
                .setAdmissionDate(rs.getDate("admissionDate").toLocalDate())
                .setDischargeDate(rs.getDate("dischargeDate").toLocalDate())
                .setRoomNumber(rs.getInt("roomNumber"))
                .setBedNumber(rs.getInt("bedNumber"));
    }
}
