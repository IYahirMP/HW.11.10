package models.xml;

import models.Patient;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Patients")
public class Patients {

    private List<Patient> patients;

    @XmlElement(name = "Patient")
    public List<Patient> getPatients() {
        return patients;
    }

    public Patients setPatients(List<Patient> patients) {
        this.patients = patients;
        return this;
    }
}
