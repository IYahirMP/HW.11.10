package models;

import java.sql.Date;

public class AdmissionRecord {
    private int admissionId;
    private int patientId; // Foreign key from Patient
    private int consultationId; // Foreign key from BasicConsultation
    private Date admissionDate;
    private Date dischargeDate;
    private int roomNumber;
    private int bedNumber;

    // Constructors, getters, and setters
    public AdmissionRecord() {}

    public AdmissionRecord(int admissionId, int patientId, int consultationId, Date admissionDate, Date dischargeDate, int roomNumber, int bedNumber) {
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

    public AdmissionRecord setAdmissionId(int admissionId) {
        this.admissionId = admissionId;
        return this;
    }

    public int getPatientId() {
        return patientId;
    }

    public AdmissionRecord setPatientId(int patientId) {
        this.patientId = patientId;
        return this;
    }

    public int getConsultationId() {
        return consultationId;
    }

    public AdmissionRecord setConsultationId(int consultationId) {
        this.consultationId = consultationId;
        return this;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public AdmissionRecord setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
        return this;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public AdmissionRecord setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
        return this;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public AdmissionRecord setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public AdmissionRecord setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AdmissionRecord [admissionId=");
        builder.append(admissionId);
        builder.append(", patientId=");
        builder.append(patientId);
        builder.append(", consultationId=");
        builder.append(consultationId);
        builder.append(", admissionDate=");
        builder.append(admissionDate);
        builder.append(", dischargeDate=");
        builder.append(dischargeDate);
        builder.append(", roomNumber=");
        builder.append(roomNumber);
        builder.append(", bedNumber=");
        builder.append(bedNumber);
        builder.append("]");
        return builder.toString();
    }
}
