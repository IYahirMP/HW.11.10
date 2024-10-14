package hospital;

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

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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
}
