package models.xml;

import models.AdmissionRecord;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "AdmissionRecords")
public class AdmissionRecords {

    private List<AdmissionRecord> consultations;

    @XmlElement(name = "AdmissionRecord")
    public List<AdmissionRecord> getAdmissionRecords() {
        return consultations;
    }

    public AdmissionRecords setAdmissionRecords(List<AdmissionRecord> consultations) {
        this.consultations = consultations;
        return this;
    }
}
