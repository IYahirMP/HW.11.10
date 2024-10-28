package models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
        String sb = "PrescriptionHasMedicine: {" +
                "prescriptionId : " + prescriptionId +
                "\nmedicineId : " + medicineId +
                "\nprescribedDays : " + prescribedDays +
                "\nprescribedDose : " + prescribedDose +
                "\nprescribedTiming : " + prescribedTiming +
                "}";
        return sb;
    }
}
