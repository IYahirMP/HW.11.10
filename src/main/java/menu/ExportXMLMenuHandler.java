package menu;

import dao.factories.DAOFactory;
import models.*;
import models.xml.*;
import views.ExportEntity;

import java.util.HashMap;
import java.util.List;

import static java.lang.System.exit;

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

            ExportEntity exportView = new ExportEntity();
            exportView.display();
            HashMap<String, String> answersMap = exportView.getInputs();
            int option = Integer.parseInt(answersMap.get("menuOption"));

            Object entity = switch(option){
                case 1 -> new Patients().setPatients(patients);
                case 2 -> new EmergencyContacts().setEmergencyContacts(emergencyContacts);
                case 3 -> new Doctors().setDoctors(doctors);
                case 4 -> new Consultations().setConsultations(consultations);
                case 5 -> new Prescriptions().setPrescriptions(prescriptions);
                case 6 -> new Medicines().setMedicines(medicines);
                case 7 -> new AdmissionRecords().setAdmissionRecords(admissionRecords);
                case 8 -> new TreatmentRecords().setTreatmentRecords(treatmentRecords);
                case 9 -> new Invoices().setInvoices(invoices);
                case 10 -> new Services().setServices(services);
                case 11 -> new InvoiceHasServices().setInvoiceHasServices(invoiceHasServices);
                case 12 -> new PrescriptionHasMedicines().setPrescriptionHasMedicines(prescriptionHasMedicines);
                case 13 -> db;
                case 14 -> "Exit";
                default -> "Restart";
            };

            if ((entity.toString()).equals("Exit")){
                exit(0);
            } else if ((entity.toString()).equals("Restart")){
                processMenuOption();
            }

            xml.XmlUtils.exportXML("target/database/xml/" + entity.getClass().getSimpleName() + ".xml", entity);
        }catch (Exception e){
            System.out.println("Some error occurred while exporting the database to XML");
            e.printStackTrace();
        }

        processMenuOption();
    }
}

