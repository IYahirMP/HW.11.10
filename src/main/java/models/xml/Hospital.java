package models.xml;

import models.*;
import javax.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "patients",
        "emergencyContacts",
        "doctors",
        "consultations",
        "admissionRecords",
        "treatmentRecords",
        "invoices",
        "invoiceHasServices",
        "services",
        "medicines",
        "prescriptionHasMedicines",
        "prescriptions"
})
public class Hospital {

    @XmlElement(required = true)
    private List<Patient> patients;
    @XmlElement(required = true)
    private List<AdmissionRecord> admissionRecords;
    @XmlElement(required = true)
    private List<Consultation> consultations;
    @XmlElement(required = true)
    private List<Doctor> doctors;
    @XmlElement(required = true)
    private List<EmergencyContact> emergencyContacts;
    @XmlElement(required = true)
    private List<Invoice> invoices;
    @XmlElement(required = true)
    private List<InvoiceHasService> invoiceHasServices;
    @XmlElement(required = true)
    private List<Medicine> medicines;
    @XmlElement(required = true)
    private List<Prescription> prescriptions;
    @XmlElement(required = true)
    private List<PrescriptionHasMedicine> prescriptionHasMedicines;
    @XmlElement(required = true)
    private List<Service> services;
    @XmlElement(required = true)
    private List<TreatmentRecord> treatmentRecords;

}
