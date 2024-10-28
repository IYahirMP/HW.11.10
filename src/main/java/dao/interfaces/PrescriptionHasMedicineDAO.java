package dao.interfaces;

import models.PrescriptionHasMedicine;

import java.util.List;
import java.util.Optional;

public interface PrescriptionHasMedicineDAO extends JointTableCrud<PrescriptionHasMedicine> {
    int insert(PrescriptionHasMedicine obj);
    int update(PrescriptionHasMedicine obj);
    int delete(int prescriptionId, int medicineId);
    Optional<PrescriptionHasMedicine> select(int prescriptionId, int medicineId);
    List<PrescriptionHasMedicine> selectByPrescription(int prescriptionId);
    List<PrescriptionHasMedicine> selectByMedicine(int medicineId);

}
