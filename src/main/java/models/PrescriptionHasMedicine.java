package models;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PrescriptionHasMedicine {
    @XmlElement(required = true)
    private int prescriptionId;
    @XmlElement(required = true)
    private int medicineId;
    @XmlElement(required = true)
    private int prescribedDays;
    @XmlElement(required = true)
    private String prescribedDose;
    @XmlElement(required = true)
    private String prescribedTiming;

    // Getters and Setters
    public int getPrescriptionId() {
        return prescriptionId;
    }

    public PrescriptionHasMedicine setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
        return this;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public PrescriptionHasMedicine setMedicineId(int medicineId) {
        this.medicineId = medicineId;
        return this;
    }

    public int getPrescribedDays() {
        return prescribedDays;
    }

    public PrescriptionHasMedicine setPrescribedDays(int prescribedDays) {
        this.prescribedDays = prescribedDays;
        return this;
    }

    public String getPrescribedDose() {
        return prescribedDose;
    }

    public PrescriptionHasMedicine setPrescribedDose(String prescribedDose) {
        this.prescribedDose = prescribedDose;
        return this;
    }

    public String getPrescribedTiming() {
        return prescribedTiming;
    }

    public PrescriptionHasMedicine setPrescribedTiming(String prescribedTiming) {
        this.prescribedTiming = prescribedTiming;
        return this;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder()
                .append("PrescriptionHasMedicine: {")
                .append("prescriptionId : ").append(prescriptionId)
                .append("\nmedicineId : ").append(medicineId)
                .append("\nprescribedDays : ").append(prescribedDays)
                .append("\nprescribedDose : ").append(prescribedDose)
                .append("\nprescribedTiming : ").append(prescribedTiming)
                .append("}");
        return sb.toString();
    }
}
