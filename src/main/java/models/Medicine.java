package models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
        String builder = "Medicine [medicineId=" +
                medicineId +
                ", name=" +
                name +
                ", cost=" +
                cost +
                ", doseSize=" +
                doseSize +
                "]";
        return builder;
    }
}
