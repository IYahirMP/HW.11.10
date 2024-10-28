package models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Doctor {
    @XmlElement(required = true)
    private int doctorId;

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private String phone;

    // Constructors, getters, and setters
    public Doctor() {}

    public Doctor(int doctorId, String name, String phone) {
        this.doctorId = doctorId;
        this.name = name;
        this.phone = phone;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public Doctor setDoctorId(int doctorId) {
        this.doctorId = doctorId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Doctor setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Doctor setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public String toString() {
        String builder = "Doctor [doctorId=" +
                doctorId +
                ", name=" +
                name +
                ", phone=" +
                phone;
        return builder;
    }
}
