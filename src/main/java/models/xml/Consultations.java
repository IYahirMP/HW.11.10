package models.xml;

import models.Consultation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Consultations")
public class Consultations {

    private List<Consultation> consultations;

    @XmlElement(name = "Consultation")
    public List<Consultation> getConsultations() {
        return consultations;
    }

    public Consultations setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
        return this;
    }
}
