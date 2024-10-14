package hospital;

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

    public void setEmergencyContactId(int emergencyContactId) {
        this.emergencyContactId = emergencyContactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
}
