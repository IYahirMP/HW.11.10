package models.xml;

import models.Prescription;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Prescriptions")
public class Prescriptions {

    private List<Prescription> prescriptions;

    @XmlElement(name = "Prescription")
    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public Prescriptions setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
        return this;
    }
}
