package hospital;

public class Medicine {
    private int medicineId;
    private String name;
    private double cost;
    private int doseSize;

    // Getters and Setters
    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getDoseSize() {
        return doseSize;
    }

    public void setDoseSize(int doseSize) {
        this.doseSize = doseSize;
    }
}
