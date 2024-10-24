package models.xml;

import models.Doctor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Doctors")
public class Doctors {

    private List<Doctor> doctors;

    @XmlElement(name = "Doctor")
    public List<Doctor> getDoctors() {
        return doctors;
    }

    public Doctors setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
        return this;
    }
}
