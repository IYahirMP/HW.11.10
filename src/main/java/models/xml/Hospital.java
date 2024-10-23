package models.xml;

import models.*;
import javax.xml.bind.annotation.*;
import java.util.List;
import com.fasterxml.jackson.annotation.*;


@XmlRootElement(name = "hospital")
@XmlAccessorType(XmlAccessType.FIELD)

@JsonPropertyOrder(
        {
                "patients",
                "emergencyContacts",
                "doctors",
                "consultations",
                "admissionRecords",
                "treatmentRecords",
                "invoices",
                "invoiceHasServices",
                "services",
                "prescriptions",
                "prescriptionHasMedicines",
                "medicines"
        }
)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonFilter("HospitalFilter")
@JsonRootName(value = "Database")
public class Hospital {

    @XmlElementWrapper(name = "Patients")
    @XmlElement(name = "Patient")
    private List<Patient> patients;

    @XmlElementWrapper(name = "AdmissionRecord")
    @XmlElement(name = "AdmissionRecord")
    private List<AdmissionRecord> admissionRecords;

    @XmlElementWrapper(name = "TreatmentRecord")
    @XmlElement(name = "TreatmentRecord")
    private List<TreatmentRecord> treatmentRecords;

    @XmlElementWrapper(name = "Doctor")
    @XmlElement(name = "Doctor")
    private List<Doctor> doctors;

    @XmlElementWrapper(name = "Consultation")
    @XmlElement(name = "Consultation")
    private List<Consultation> consultations;

    @XmlElementWrapper(name = "InvoiceHasServices")
    @XmlElement(name = "InvoiceHasService")
    private List<InvoiceHasService> invoiceHasServices;

    @XmlElementWrapper(name = "Services")
    @XmlElement(name = "Service")
    private List<Service> services;

    @XmlElementWrapper(name = "Invoice")
    @XmlElement(name = "Invoice")
    private List<Invoice> invoices;

    @XmlElementWrapper(name = "EmergencyContact")
    @XmlElement(name = "EmergencyContact")
    private List<EmergencyContact> emergencyContacts;


    @XmlElementWrapper(name = "Prescriptions")
    @XmlElement(name = "Prescription")
    private List<Prescription> prescriptions;

    @XmlElementWrapper(name = "PrescriptionHasMedicines")
    @XmlElement(name = "PrescriptionHasMedicine")
    private List<PrescriptionHasMedicine> prescriptionHasMedicines;

    @XmlElementWrapper(name = "Medicines")
    @XmlElement(name = "Medicine")
    private List<Medicine> medicines;

    // Getters and Setters

    public List<Patient> getPatients() {
        return patients;
    }

    public Hospital setPatients(List<Patient> patients) {
        this.patients = patients;
        return this;
    }

    public List<EmergencyContact> getEmergencyContacts() {
        return emergencyContacts;
    }

    public Hospital setEmergencyContacts(List<EmergencyContact> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
        return this;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public Hospital setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
        return this;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public Hospital setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
        return this;
    }

    public List<AdmissionRecord> getAdmissionRecords() {
        return admissionRecords;
    }

    public Hospital setAdmissionRecords(List<AdmissionRecord> admissionRecords) {
        this.admissionRecords = admissionRecords;
        return this;
    }

    public List<TreatmentRecord> getTreatmentRecords() {
        return treatmentRecords;
    }

    public Hospital setTreatmentRecords(List<TreatmentRecord> treatmentRecords) {
        this.treatmentRecords = treatmentRecords;
        return this;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public Hospital setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
        return this;
    }

    public List<InvoiceHasService> getInvoiceHasServices() {
        return invoiceHasServices;
    }

    public Hospital setInvoiceHasServices(List<InvoiceHasService> invoiceHasServices) {
        this.invoiceHasServices = invoiceHasServices;
        return this;
    }

    public List<Service> getServices() {
        return services;
    }

    public Hospital setServices(List<Service> services) {
        this.services = services;
        return this;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public Hospital setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
        return this;
    }

    public List<PrescriptionHasMedicine> getPrescriptionHasMedicines() {
        return prescriptionHasMedicines;
    }

    public Hospital setPrescriptionHasMedicines(List<PrescriptionHasMedicine> prescriptionHasMedicines) {
        this.prescriptionHasMedicines = prescriptionHasMedicines;
        return this;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public Hospital setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
        return this;
    }
}