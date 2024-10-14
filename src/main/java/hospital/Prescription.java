package hospital;

public class Prescription {
    private int prescriptionId;
    private int patientId; // Foreign key from Patient
    private String diagnose;

    // Constructors, getters, and setters
    public Prescription() {}

    public Prescription(int prescriptionId, int patientId, String diagnose) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.diagnose = diagnose;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }
}
