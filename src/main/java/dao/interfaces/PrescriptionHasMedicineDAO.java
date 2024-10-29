package dao.interfaces;

import models.PrescriptionHasMedicine;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface PrescriptionHasMedicineDAO extends JointTableCrud<PrescriptionHasMedicine> {
    int insert(PrescriptionHasMedicine obj);
    int update(PrescriptionHasMedicine obj);
    int delete(@Param("prescriptionId") int prescriptionId, @Param("medicineId") int medicineId);
    Optional<PrescriptionHasMedicine> select(@Param("prescriptionId") int prescriptionId, @Param("medicineId") int medicineId);
    List<PrescriptionHasMedicine> selectByPrescription(int prescriptionId);
    List<PrescriptionHasMedicine> selectByMedicine(int medicineId);

}
