package models;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EmergencyContact {
    @XmlElement(required = true)
    private int emergencyContactId;

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private String phone;

    @XmlElement(required = true)
    private String address;

    @XmlElement(required = true)
    private int patientId;

    // Constructors, getters, and setters
    public EmergencyContact() {}

    public EmergencyContact(int emergencyContactId, String name, String phone, String address, int patientId) {
        this.emergencyContactId = emergencyContactId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.patientId = patientId;
    }

    public int getEmergencyContactId() {
        return emergencyContactId;
    }

    public EmergencyContact setEmergencyContactId(int emergencyContactId) {
        this.emergencyContactId = emergencyContactId;
        return this;
    }

    public String getName() {
        return name;
    }

    public EmergencyContact setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public EmergencyContact setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public EmergencyContact setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getPatientId() {
        return patientId;
    }

    public EmergencyContact setPatientId(int patientId) {
        this.patientId = patientId;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("EmergencyContact [emergencyContactId=")
                .append(emergencyContactId)
                .append(", name=")
                .append(name)
                .append(", phone=")
                .append(phone)
                .append(", address=")
                .append(address)
                .append(", patientId=")
                .append(patientId);
        return builder.toString();
    }
}
