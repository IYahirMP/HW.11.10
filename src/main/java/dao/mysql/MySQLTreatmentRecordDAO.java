package dao.mysql;

import dao.interfaces.TreatmentRecordDAO;
import models.TreatmentRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dao.factories.MySQLDAOFactory.createConnection;

public class MySQLTreatmentRecordDAO implements TreatmentRecordDAO {
    /**
     * @param obj
     */
    @Override
    public int insert(TreatmentRecord obj) {
        String sql = "insert into treatmentrecord (admissionId, time, notes) values(?, ?, ?)";

        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, obj.getAdmissionId());
            Timestamp.valueOf(obj.getTime());
            ps.setString(3, obj.getNotes());

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
    public int update(int id, TreatmentRecord obj) {
        String sql = "update treatmentrecord set " +
                "admissionId = ?," +
                "time = ?," +
                "notes = ?" +
                "where recordId = ?";

        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, obj.getAdmissionId());
            ps.setTimestamp(2, Timestamp.valueOf(obj.getTime()));
            ps.setString(3, obj.getNotes());
            ps.setInt(4, id);

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
        String sql = "delete from treatmentrecord where recordId = ?";
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
    public Optional<TreatmentRecord> select(int id) {
        String sql = "select * from treatmentrecord where recordId = ?";
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
    public List<TreatmentRecord> selectAll() {
        String sql = "select * from treatmentrecord";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            List<TreatmentRecord> list = new ArrayList<>();
            while(rs.next()){
                list.add(constructObject(rs));
            }
            return list;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return List.of();
    }

    public TreatmentRecord constructObject(ResultSet rs) throws SQLException {
        return new TreatmentRecord()
                .setRecordId(rs.getInt("recordId"))
                .setAdmissionId(rs.wasNull() ? null : rs.getInt("admissionId"))
                .setTime(rs.getTimestamp("time").toLocalDateTime())
                .setNotes(rs.getString("notes"));
    }
}
