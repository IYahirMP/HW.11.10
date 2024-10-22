package models;

public class Doctor {
    private int doctorId;
    private String name;
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
}
