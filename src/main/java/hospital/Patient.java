package hospital;

public class Patient {
    private int patientId;
    private String name;
    private int age;
    private String address;
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

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
