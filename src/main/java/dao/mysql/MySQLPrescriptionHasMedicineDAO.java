package dao.mysql;

import dao.interfaces.PrescriptionHasMedicineDAO;
import models.PrescriptionHasMedicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dao.factories.MySQLDAOFactory.createConnection;

public class MySQLPrescriptionHasMedicineDAO implements PrescriptionHasMedicineDAO {
    /**
     * @param obj
     * @return
     */
    @Override
    public int insert(PrescriptionHasMedicine obj) {
        String sql = "insert into prescriptionhasmedicine (prescriptionId, medicineId, prescribedDays, prescribedDose, prescribedTiming) values(?, ?, ?, ?, ?)";

        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, obj.getPrescriptionId());
            ps.setInt(2, obj.getMedicineId());
            ps.setInt(3, obj.getPrescribedDays());
            ps.setString(4, obj.getPrescribedDose());
            ps.setString(5, obj.getPrescribedTiming());

            int affectedRows = ps.executeUpdate();
            return affectedRows;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public int update(PrescriptionHasMedicine obj) {
        String sql = "UPDATE prescriptionhasmedicine SET " +
                "prescribedDays = ?," +
                "prescribedDose = ?," +
                "prescribedTiming = ? " +
                "WHERE prescriptionId = ? AND medicineId = ?";

        try (Connection con = createConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, obj.getPrescribedDays());
            ps.setString(2, obj.getPrescribedDose());
            ps.setString(3, obj.getPrescribedTiming());
            ps.setInt(4, obj.getPrescriptionId());
            ps.setInt(5, obj.getMedicineId());

            int affectedRows = ps.executeUpdate();
            return affectedRows;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }


    /**
     * @param prescriptionId
     * @param medicineId
     * @return
     */
    @Override
    public int delete(int prescriptionId, int medicineId) {
        String sql = "delete from prescriptionhasmedicine where prescriptionId = ? and medicineId = ?";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, prescriptionId);
            ps.setInt(2, medicineId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * @param prescriptionId
     * @param medicineId
     * @return
     */
    @Override
    public Optional<PrescriptionHasMedicine> select(int prescriptionId, int medicineId) {
        String sql = "select * from prescriptionhasmedicine where prescriptionId = ? and medicineId = ?";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, prescriptionId);
            ps.setInt(2, medicineId);

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
    public List<PrescriptionHasMedicine> selectAll() {
        String sql = "select * from prescriptionhasmedicine";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            List<PrescriptionHasMedicine> list = new ArrayList<>();
            while(rs.next()){
                list.add(constructObject(rs));
            }
            return list;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return List.of();
    }

    /**
     * @param prescriptionId
     * @return
     */
    @Override
    public List<PrescriptionHasMedicine> selectByPrescription(int prescriptionId) {
        String sql = "select * from prescriptionhasmedicine where prescriptionId = ?";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, prescriptionId);

            ResultSet rs = ps.executeQuery();
            List<PrescriptionHasMedicine> list = new ArrayList<>();
            while(rs.next()){
                list.add(constructObject(rs));
            }
            return list;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return List.of();
    }

    /**
     * @param medicineId
     * @return
     */
    @Override
    public List<PrescriptionHasMedicine> selectByMedicine(int medicineId) {
        String sql = "select * from prescriptionhasmedicine where medicineId = ?";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, medicineId);

            ResultSet rs = ps.executeQuery();
            List<PrescriptionHasMedicine> list = new ArrayList<>();
            while(rs.next()){
                list.add(constructObject(rs));
            }
            return list;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return List.of();
    }

    public PrescriptionHasMedicine constructObject(ResultSet rs) throws SQLException {
        return new PrescriptionHasMedicine()
                .setPrescriptionId(rs.getInt("prescriptionId"))
                .setMedicineId(rs.getInt("medicineId"))
                .setPrescribedDays(rs.getInt("prescribedDays"))
                .setPrescribedDose(rs.getString("prescribedDose"))
                .setPrescribedTiming(rs.getString("prescribedTiming"));
    }
}
