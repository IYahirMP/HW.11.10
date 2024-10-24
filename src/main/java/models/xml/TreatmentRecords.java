package models.xml;

import models.TreatmentRecord;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "TreatmentRecords")
public class TreatmentRecords {

    private List<TreatmentRecord> treatmentRecords;

    @XmlElement(name = "TreatmentRecord")
    public List<TreatmentRecord> getTreatmentRecords() {
        return treatmentRecords;
    }

    public TreatmentRecords setTreatmentRecords(List<TreatmentRecord> treatmentRecords) {
        this.treatmentRecords = treatmentRecords;
        return this;
    }
}
