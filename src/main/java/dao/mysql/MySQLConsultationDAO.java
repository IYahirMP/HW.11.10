package dao.mysql;

import dao.interfaces.ConsultationDAO;
import models.Consultation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dao.factories.MySQLDAOFactory.createConnection;

public class MySQLConsultationDAO implements ConsultationDAO {
    /**
     * @param obj
     */
    @Override
    public int insert(Consultation obj) {
        String sql = "insert into Consultation (date, doctorId, patientId, diagnose, prescriptionId, admittedForTreatment) values(?, ?, ?, ?, ?, ?)";

        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(obj.getDate()));
            ps.setInt(2, obj.getDoctorId());
            ps.setInt(3, obj.getPatientId());
            ps.setString(4, obj.getDiagnose());
            ps.setInt(5, obj.getPrescriptionId());
            ps.setInt(6, obj.isAdmittedForTreatment());

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
    public int update(int id, Consultation obj) {
        String sql = "update Consultation set " +
                "date = ?," +
                "doctorId = ?," +
                "patientId = ?," +
                "diagnose = ?," +
                "prescriptionId = ?," +
                "admittedForTreatment = ? " +
                "where consultationId = ?";

        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(obj.getDate()));
            ps.setInt(2, obj.getDoctorId());
            ps.setInt(3, obj.getPatientId());
            ps.setString(4, obj.getDiagnose());
            ps.setInt(5, obj.getPrescriptionId());
            ps.setInt(6, obj.isAdmittedForTreatment());
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
        String sql = "delete from consultation where consultationId = ?";
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
    public Optional<Consultation> select(int id) {
        String sql = "select * from consultation where consultationId = ?";
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
    public List<Consultation> selectAll() {
        String sql = "select * from consultation";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            List<Consultation> list = new ArrayList<>();
            while(rs.next()){
                list.add(constructObject(rs));
            }
            return list;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return List.of();
    }

    public Consultation constructObject(ResultSet rs) throws SQLException {
        return new Consultation()
                .setConsultationId(rs.getInt("consultationId"))
                .setDate(rs.getDate("date").toLocalDate())
                .setDoctorId(rs.getInt("doctorId"))
                .setPatientId(rs.getInt("patientId"))
                .setDiagnose(rs.getString("diagnose"))
                .setPrescriptionId(rs.getInt("prescriptionId"))
                .setAdmittedForTreatment(rs.getInt("admittedForTreatment"));
    }
}
