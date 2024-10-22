package models;

import java.time.LocalDateTime;

public class TreatmentRecord {
    private int recordId;
    private int admissionId;
    private String notes;
    private LocalDateTime time;

    // Getters and Setters
    public int getRecordId() {
        return recordId;
    }

    public TreatmentRecord setRecordId(int recordId) {
        this.recordId = recordId;
        return this;
    }

    public int getAdmissionId() {
        return admissionId;
    }

    public TreatmentRecord setAdmissionId(int admissionId) {
        this.admissionId = admissionId;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public TreatmentRecord setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public TreatmentRecord setTime(LocalDateTime time) {
        this.time = time;
        return this;
    }
}
