package dao.mybatis;

import dao.interfaces.PrescriptionHasMedicineDAO;
import models.PrescriptionHasMedicine;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static dao.factories.MyBatisDAOFactory.createConnection;

public class MyBatisPrescriptionHasMedicineDAO implements PrescriptionHasMedicineDAO {

    /**
     * @param obj
     * @return
     */
    @Override
    public int insert(PrescriptionHasMedicine obj) {
        try (SqlSession con = createConnection()) {
            PrescriptionHasMedicineDAO mapper = con.getMapper(PrescriptionHasMedicineDAO.class);
            int rowsAffected = mapper.insert(obj);
con.commit();
return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * @param obj
     * @return
     */
    @Override
    public int update(PrescriptionHasMedicine obj) {
        try (SqlSession con = createConnection()) {
            PrescriptionHasMedicineDAO mapper = con.getMapper(PrescriptionHasMedicineDAO.class);
            int rowsAffected = mapper.update(obj);
con.commit();
return rowsAffected;
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
        try (SqlSession con = createConnection()) {
            PrescriptionHasMedicineDAO mapper = con.getMapper(PrescriptionHasMedicineDAO.class);
            int deletedRows = mapper.delete(prescriptionId, medicineId);
            con.commit();
            return deletedRows;
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
    public Optional<PrescriptionHasMedicine> select(int prescriptionId, int medicineId) {
        try (SqlSession con = createConnection()) {
            PrescriptionHasMedicineDAO mapper = con.getMapper(PrescriptionHasMedicineDAO.class);
            return mapper.select(prescriptionId, medicineId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    /**
     * @return
     */
    @Override
    public List<PrescriptionHasMedicine> selectAll() {
        try (SqlSession con = createConnection()) {
            PrescriptionHasMedicineDAO mapper = con.getMapper(PrescriptionHasMedicineDAO.class);
            return mapper.selectAll();
        } catch (SQLException e) {
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
        try (SqlSession con = createConnection()) {
            PrescriptionHasMedicineDAO mapper = con.getMapper(PrescriptionHasMedicineDAO.class);
            return mapper.selectByPrescription(prescriptionId);
        } catch (SQLException e) {
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
        try (SqlSession con = createConnection()) {
            PrescriptionHasMedicineDAO mapper = con.getMapper(PrescriptionHasMedicineDAO.class);
            return mapper.selectByMedicine(medicineId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
