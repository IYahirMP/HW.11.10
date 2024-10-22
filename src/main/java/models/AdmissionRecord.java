package models;

import models.xml.LocalDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement
@XmlType(propOrder={"admissionId", "patientId", "consultationId", "admissionDate", "dischargeDate", "roomNumber", "bedNumber"})
@XmlAccessorType(XmlAccessType.FIELD)
public class AdmissionRecord {
    @XmlElement(name = "admissionId")
    private int admissionId;

    @XmlElement(name = "patientId")
    private int patientId; // Foreign key from Patient

    @XmlElement(name = "consultationId")
    private int consultationId; // Foreign key from BasicConsultation

    @XmlElement(name = "admissionDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate admissionDate;

    @XmlElement(name = "dischargeDate")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate dischargeDate;

    @XmlElement(name = "roomNumber")
    private int roomNumber;

    @XmlElement(name = "bedNumber")
    private int bedNumber;

    // Constructors, getters, and setters
    public AdmissionRecord() {}

    public AdmissionRecord(int admissionId, int patientId, int consultationId, LocalDate admissionDate, LocalDate dischargeDate, int roomNumber, int bedNumber) {
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

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public AdmissionRecord setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
        return this;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public AdmissionRecord setDischargeDate(LocalDate dischargeDate) {
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
