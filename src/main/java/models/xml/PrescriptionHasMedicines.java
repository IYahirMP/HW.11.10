package models.xml;

import models.PrescriptionHasMedicine;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "PrescriptionHasMedicines")
public class PrescriptionHasMedicines {

    private List<PrescriptionHasMedicine> prescriptionHasMedicines;

    @XmlElement(name = "PrescriptionHasMedicine")
    public List<PrescriptionHasMedicine> getPrescriptionHasMedicines() {
        return prescriptionHasMedicines;
    }

    public PrescriptionHasMedicines setPrescriptionHasMedicines(List<PrescriptionHasMedicine> prescriptionHasMedicines) {
        this.prescriptionHasMedicines = prescriptionHasMedicines;
        return this;
    }
}
