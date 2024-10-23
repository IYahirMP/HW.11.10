package models;

import com.fasterxml.jackson.annotation.*;
import models.xml.LocalDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Consultation {
    @XmlElement(required = true)
    private int consultationId;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlElement(required = true)
    private LocalDate date;

    @XmlElement(required = true)
    private int doctorId;

    @XmlElement(required = true)
    private int patientId;

    @XmlElement(required = true)
    private String diagnose;

    @XmlElement(required = true)
    private int prescriptionId;

    @XmlElement(required = true)
    private int admittedForTreatment;

    // Constructors, getters, and setters
    public Consultation() {}

    public Consultation(int consultationId, LocalDate date, int doctorId, int patientId, String diagnose, int prescriptionId, int admittedForTreatment) {
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

    public LocalDate getDate() {
        return date;
    }

    @JsonGetter("date")
    public String getJSONDate(){
        return date.toString();
    }

    @JsonSetter("date")
    public void setJSONDate(String date){
        this.date = LocalDate.parse(date);
    }

    public Consultation setDate(LocalDate date) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Consultation {\n")
                .append("  consultationId: ").append(consultationId).append("\n")
                .append("  date: ").append(date).append("\n")
                .append("  doctorId: ").append(doctorId).append("\n")
                .append("  patientId: ").append(patientId).append("\n")
                .append("  diagnose: ").append(diagnose).append("\n")
                .append("  prescriptionId: ").append(prescriptionId).append("\n")
                .append("  admittedForTreatment: ").append(admittedForTreatment).append("\n");
        return sb.toString();
    }
}
