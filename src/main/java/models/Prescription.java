package models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Prescription {
    @XmlElement(required = true)
    private int prescriptionId;

    @XmlElement(required = true)
    private int patientId;

    @XmlElement(required = true)
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

    @Override
    public String toString() {
        String sb = "Prescription{" +
                "prescriptionId=" + prescriptionId +
                ", patientId=" + patientId +
                ", diagnose=" + diagnose +
                "}";
        return sb;
    }
}
