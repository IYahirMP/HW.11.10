package menu;

import dao.factories.DAOFactory;
import models.*;
import models.xml.Hospital;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class ExportJSONMenuHandler extends MenuHandler {
    public ExportJSONMenuHandler(DAOFactory factory) {
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

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(db);

            File output = new File("hospital.json");
            FileWriter writer = new FileWriter(output);
            writer.write(jsonString);
            writer.close();
        }catch (Exception e){
            System.out.println("Some error occurred while exporting the database to XML");
            e.printStackTrace();
        }
    }
}

