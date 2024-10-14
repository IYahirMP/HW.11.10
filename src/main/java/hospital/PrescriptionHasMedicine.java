package hospital;

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

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getPrescribedDays() {
        return prescribedDays;
    }

    public void setPrescribedDays(int prescribedDays) {
        this.prescribedDays = prescribedDays;
    }

    public String getPrescribedDose() {
        return prescribedDose;
    }

    public void setPrescribedDose(String prescribedDose) {
        this.prescribedDose = prescribedDose;
    }

    public String getPrescribedTiming() {
        return prescribedTiming;
    }

    public void setPrescribedTiming(String prescribedTiming) {
        this.prescribedTiming = prescribedTiming;
    }
}
