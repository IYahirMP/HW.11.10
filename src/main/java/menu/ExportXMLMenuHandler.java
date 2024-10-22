package menu;

import dao.factories.DAOFactory;
import models.*;
import models.xml.Hospital;
import views.service.ServiceOperations;

import java.util.HashMap;
import java.util.List;

public class ExportXMLMenuHandler extends MenuHandler {
    public ExportXMLMenuHandler(DAOFactory factory) {
        super(factory);
    }

    @Override
    public void processMenuOption() {
        System.out.println("Retrieving information...");
        try {
            List<AdmissionRecord> admissionRecords = admissionRecordController.selectAll();
            List<Consultation> consultations = consultationController.selectAll();
            List<Doctor> doctors = doctorController.selectAll();
            List<EmergencyContact> emergencyContacts = emergencyContactController.selectAll();
            List<Invoice> invoices = invoiceController.selectAll();
            List<InvoiceHasService> invoiceHasServices = invoiceHasServiceController.selectAll();
            List<Medicine> medicines = medicineController.selectAll();
            List<Patient> patients = patientController.selectAll();
            List<Prescription> prescriptions = prescriptionController.selectAll();
            List<PrescriptionHasMedicine> prescriptionHasMedicines = prescriptionHasMedicineController.selectAll();
            List<Service> services = serviceController.selectAll();
            List<TreatmentRecord> treatmentRecords = treatmentRecordController.selectAll();

            Hospital db = new Hospital()
                    .setAdmissionRecords(admissionRecords)
                    .setConsultations(consultations)
                    .setDoctors(doctors)
                    .setEmergencyContacts(emergencyContacts)
                    .setInvoices(invoices)
                    .setInvoiceHasServices(invoiceHasServices)
                    .setMedicines(medicines)
                    .setPatients(patients)
                    .setPrescriptions(prescriptions)
                    .setPrescriptionHasMedicines(prescriptionHasMedicines)
                    .setServices(services)
                    .setTreatmentRecords(treatmentRecords);

            xml.XmlUtils.exportXML("hospital", db);
        }catch (Exception e){
            System.out.println("Some error occurred while exporting the database to XML");
            e.printStackTrace();
        }
    }
}

