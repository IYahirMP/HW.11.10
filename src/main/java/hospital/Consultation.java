package hospital;

public class Consultation {
    private int consultationId;
    private String date;
    private int doctorId; // Foreign key from Doctor
    private int patientId; // Foreign key from Patient
    private String diagnose;
    private int prescriptionId; // Foreign key from Prescription
    private boolean admittedForTreatment;

    // Constructors, getters, and setters
    public Consultation() {}

    public Consultation(int consultationId, String date, int doctorId, int patientId, String diagnose, int prescriptionId, boolean admittedForTreatment) {
        this.consultationId = consultationId;
        this.date = date;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.diagnose = diagnose;
        this.prescriptionId = prescriptionId;
        this.admittedForTreatment = admittedForTreatment;
    }

    public int getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(int consultationId) {
        this.consultationId = consultationId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public boolean isAdmittedForTreatment() {
        return admittedForTreatment;
    }

    public void setAdmittedForTreatment(boolean admittedForTreatment) {
        this.admittedForTreatment = admittedForTreatment;
    }
}
