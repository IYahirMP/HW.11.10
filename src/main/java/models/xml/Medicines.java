package models.xml;

import models.Medicine;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Medicines")
public class Medicines {

    private List<Medicine> medicines;

    @XmlElement(name = "Medicine")
    public List<Medicine> getMedicines() {
        return medicines;
    }

    public Medicines setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
        return this;
    }
}
