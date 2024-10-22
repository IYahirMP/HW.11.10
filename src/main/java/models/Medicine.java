package models;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Medicine {
    @XmlElement(required = true)
    private int medicineId;

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private double cost;

    @XmlElement(required = true)
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Medicine [medicineId=")
                .append(medicineId)
                .append(", name=")
                .append(name)
                .append(", cost=")
                .append(cost)
                .append(", doseSize=")
                .append(doseSize)
                .append("]");
        return builder.toString();
    }
}
