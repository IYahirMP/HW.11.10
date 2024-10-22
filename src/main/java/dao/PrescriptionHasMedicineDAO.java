package dao;

import models.PrescriptionHasMedicine;

import java.util.Optional;

public interface PrescriptionHasMedicineDAO extends Crud<PrescriptionHasMedicine> {
    int delete(int prescriptionId, int medicineId);
    Optional<PrescriptionHasMedicine> select(int prescriptionId, int medicineId);
}
