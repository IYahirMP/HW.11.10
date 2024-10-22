package models;

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

   public Prescription setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
        return this;
    }

    public int getPatientId() {
        return patientId;
    }

   public Prescription setPatientId(int patientId) {
        this.patientId = patientId;
        return this;
    }

    public String getDiagnose() {
        return diagnose;
    }

   public Prescription setDiagnose(String diagnose) {
        this.diagnose = diagnose;
        return this;
    }
}
