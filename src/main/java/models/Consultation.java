package models;

import java.sql.Date;

public class Consultation {
    private int consultationId;
    private Date date;
    private int doctorId; // Foreign key from Doctor
    private int patientId; // Foreign key from Patient
    private String diagnose;
    private int prescriptionId; // Foreign key from Prescription
    private int admittedForTreatment;

    // Constructors, getters, and setters
    public Consultation() {}

    public Consultation(int consultationId, Date date, int doctorId, int patientId, String diagnose, int prescriptionId, int admittedForTreatment) {
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

    public Consultation setConsultationId(int consultationId) {
        this.consultationId = consultationId;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Consultation setDate(Date date) {
        this.date = date;
        return this;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public Consultation setDoctorId(int doctorId) {
        this.doctorId = doctorId;
        return this;
    }

    public int getPatientId() {
        return patientId;
    }

    public Consultation setPatientId(int patientId) {
        this.patientId = patientId;
        return this;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public Consultation setDiagnose(String diagnose) {
        this.diagnose = diagnose;
        return this;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public Consultation setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
        return this;
    }

    public int isAdmittedForTreatment() {
        return admittedForTreatment;
    }

    public Consultation setAdmittedForTreatment(int admittedForTreatment) {
        this.admittedForTreatment = admittedForTreatment;
        return this;
    }
}
