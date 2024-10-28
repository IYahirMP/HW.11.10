package models;

import models.xml.LocalDateTimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TreatmentRecord {
    @XmlElement(required = true)
    private int recordId;
    @XmlElement(required = true)
    private int admissionId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime time;
    @XmlElement(required = true)
    private String notes;

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

    @Override
    public String toString() {
        String sb = "TreatmentRecord[" +
                "recordId=" + recordId +
                ", admissionId=" + admissionId +
                ", notes=" + notes +
                ", time=" + time +
                "]";
        return sb;
    }
}
