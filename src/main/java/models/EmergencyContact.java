package models;

public class EmergencyContact {
    private int emergencyContactId;
    private String name;
    private String phone;
    private String address;
    private int patientId;  // Foreign key from Patient

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
}
