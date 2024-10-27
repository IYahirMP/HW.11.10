package models;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.*;
@XmlRootElement
@XmlType(propOrder={"patientId", "name", "age", "address", "phone"})
@XmlAccessorType(XmlAccessType.FIELD)

@JsonRootName(value = "PatientList")
public class Patient {
    @XmlElement(name = "patientId")
    private int patientId;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "age")
    private int age;

    @XmlElement(name="address")
    private String address;

    @XmlElement(name="phone")
    private String phone;

    // Constructors, getters, and setters
    public Patient() {}

    public Patient(int patientId, String name, int age, String address, String phone) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
    }


    public int getPatientId() {
        return patientId;
    }

    public Patient setPatientId(int patientId) {
        this.patientId = patientId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Patient setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Patient setAge(int age) {
        this.age = age;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Patient setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Patient setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public String toString() {
        String builder = "Patient [patientId=" +
                patientId +
                ", name=" +
                name +
                ", age=" +
                age +
                ", address=" +
                address +
                ", phone=" +
                phone +
                "]";
        return builder;
    }
}
