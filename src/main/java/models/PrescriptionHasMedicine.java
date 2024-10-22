package models;

public class PrescriptionHasMedicine {
    private int prescriptionId;
    private int medicineId;
    private int prescribedDays;
    private String prescribedDose;
    private String prescribedTiming;

    // Getters and Setters
    public int getPrescriptionId() {
        return prescriptionId;
    }

    public PrescriptionHasMedicine setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
        return this;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public PrescriptionHasMedicine setMedicineId(int medicineId) {
        this.medicineId = medicineId;
        return this;
    }

    public int getPrescribedDays() {
        return prescribedDays;
    }

    public PrescriptionHasMedicine setPrescribedDays(int prescribedDays) {
        this.prescribedDays = prescribedDays;
        return this;
    }

    public String getPrescribedDose() {
        return prescribedDose;
    }

    public PrescriptionHasMedicine setPrescribedDose(String prescribedDose) {
        this.prescribedDose = prescribedDose;
        return this;
    }

    public String getPrescribedTiming() {
        return prescribedTiming;
    }

    public PrescriptionHasMedicine setPrescribedTiming(String prescribedTiming) {
        this.prescribedTiming = prescribedTiming;
        return this;
    }
}
