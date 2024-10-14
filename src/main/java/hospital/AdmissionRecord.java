package hospital;

public class AdmissionRecord {
    private int admissionId;
    private int patientId; // Foreign key from Patient
    private int consultationId; // Foreign key from BasicConsultation
    private String admissionDate;
    private String dischargeDate;
    private int roomNumber;
    private int bedNumber;

    // Constructors, getters, and setters
    public AdmissionRecord() {}

    public AdmissionRecord(int admissionId, int patientId, int consultationId, String admissionDate, String dischargeDate, int roomNumber, int bedNumber) {
        this.admissionId = admissionId;
        this.patientId = patientId;
        this.consultationId = consultationId;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.roomNumber = roomNumber;
        this.bedNumber = bedNumber;
    }

    public int getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(int admissionId) {
        this.admissionId = admissionId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(int consultationId) {
        this.consultationId = consultationId;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }
}
