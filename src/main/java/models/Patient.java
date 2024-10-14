package models;

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
        StringBuilder builder = new StringBuilder();
        builder.append("Patient [patientId=");
        builder.append(patientId);
        builder.append(", name=");
        builder.append(name);
        builder.append(", age=");
        builder.append(age);
        builder.append(", address=");
        builder.append(address);
        builder.append(", phone=");
        builder.append(phone);
        builder.append("]");
        return builder.toString();
    }
}
