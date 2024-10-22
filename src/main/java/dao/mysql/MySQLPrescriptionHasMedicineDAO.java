package dao.mysql;

import dao.PrescriptionHasMedicineDAO;
import dao.PrescriptionHasMedicineDAO;
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
     */
    @Override
    public int insert(PrescriptionHasMedicine obj) {/*
        String sql = "insert into PrescriptionHasMedicine (name, age, address, phone) values(?, ?, ?, ?)";

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
    public int update(int id, PrescriptionHasMedicine obj) {/*
        String sql = "update PrescriptionHasMedicine set " +
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
    public int delete(int id) {/*
        String sql = "delete from patient where patientId = ?";
        try(Connection con = createConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return -1;*/
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(int prescriptionId, int medicineId) {
        String sql = "delete from PrescriptionHasMedicine where prescriptionId = ? AND medicineId = ?";
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
     * @param id
     * @return
     */
    @Override
    public Optional<PrescriptionHasMedicine> select(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<PrescriptionHasMedicine> select(int prescriptionId, int medicineId) {
        String sql = "select * from prescriptionhasmedicine where prescriptionId = ? AND medicineId = ?";
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

    public PrescriptionHasMedicine constructObject(ResultSet rs) throws SQLException{
        return new PrescriptionHasMedicine()
                .setPrescriptionId(rs.getInt("prescriptionId"))
                .setMedicineId(rs.getInt("medicineId"))
                .setPrescribedDays(rs.getInt("prescribedDays"))
                .setPrescribedDose(rs.getString("prescribedDose"))
                .setPrescribedTiming(rs.getString("prescribedTiming"));
    }
}
