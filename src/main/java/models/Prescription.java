package models;

import javax.xml.bind.annotation.*;

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
        StringBuilder sb = new StringBuilder()
                .append("Prescription{")
                .append("prescriptionId=").append(prescriptionId)
                .append(", patientId=").append(patientId)
                .append(", diagnose=").append(diagnose);
        return sb.append("}").toString();
    }
}
