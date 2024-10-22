package models;

public class Medicine {
    private int medicineId;
    private String name;
    private double cost;
    private int doseSize;

    // Getters and Setters
    public int getMedicineId() {
        return medicineId;
    }

    public Medicine setMedicineId(int medicineId) {
        this.medicineId = medicineId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Medicine setName(String name) {
        this.name = name;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public Medicine setCost(double cost) {
        this.cost = cost;
        return this;
    }

    public int getDoseSize() {
        return doseSize;
    }

    public Medicine setDoseSize(int doseSize) {
        this.doseSize = doseSize;
        return this;
    }
}
