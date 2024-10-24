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


    public PrescriptionHasMedicine constructObject(ResultSet rs) throws SQLException{
        return new PrescriptionHasMedicine()
                .setPrescriptionId(rs.getInt("prescriptionId"))
                .setMedicineId(rs.getInt("medicineId"))
                .setPrescribedDays(rs.getInt("prescribedDays"))
                .setPrescribedDose(rs.getString("prescribedDose"))
                .setPrescribedTiming(rs.getString("prescribedTiming"));
    }

    /**
     * @param obj
     * @return
     */
    @Override
    public int insert(PrescriptionHasMedicine obj) {
        return 0;
    }

    /**
     * @param obj
     * @return
     */
    @Override
    public int update(PrescriptionHasMedicine obj) {
        return 0;
    }

    /**
     * @param prescriptionId
     * @param medicineId
     * @return
     */
    @Override
    public int delete(int prescriptionId, int medicineId) {
        return 0;
    }

    /**
     * @param prescriptionId
     * @param medicineId
     * @return
     */
    @Override
    public Optional<PrescriptionHasMedicine> select(int prescriptionId, int medicineId) {
        return Optional.empty();
    }

    /**
     * @return
     */
    @Override
    public List<PrescriptionHasMedicine> selectAll() {
        return List.of();
    }

    /**
     * @param prescriptionId
     * @return
     */
    @Override
    public List<PrescriptionHasMedicine> selectByPrescription(int prescriptionId) {
        return List.of();
    }

    /**
     * @param medicineId
     * @return
     */
    @Override
    public List<PrescriptionHasMedicine> selectByMedicine(int medicineId) {
        return List.of();
    }
}
