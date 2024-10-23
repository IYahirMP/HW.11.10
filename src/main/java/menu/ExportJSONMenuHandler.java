package menu;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import dao.factories.DAOFactory;
import models.*;
import models.xml.Hospital;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import views.ExportEntity;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;

import static java.lang.System.exit;

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

            ExportEntity exportView = new ExportEntity();
            exportView.display();
            HashMap<String, String> answersMap = exportView.getInputs();
            int option = Integer.parseInt(answersMap.get("menuOption"));

            String entity = switch(option){
                case 1 -> "patients";
                case 2 -> "emergencyContacts";
                case 3 -> "doctors";
                case 4 -> "consultations";
                case 5 -> "prescriptions";
                case 6 -> "medicines";
                case 7 -> "admissionRecords";
                case 8 -> "treatmentRecords";
                case 9 -> "invoices";
                case 10 -> "services";
                case 11 -> "invoiceHasServices";
                case 12 -> "prescriptionHasMedicines";
                case 13 -> "";
                case 14 -> "Exit";
                default -> "Restart";
            };

            String filedir = "target\\database\\json\\";
            String filepath = (entity.isEmpty()) ?  "hospital.json" : entity + ".json";

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            ObjectWriter jsonwriter;

            if (entity.equals("Exit")){
                exit(0);
            }

            if (entity.equals("Restart")){
                processMenuOption();
            }
            SimpleFilterProvider filters = new SimpleFilterProvider();
            if (!entity.isEmpty()) {
                filters.addFilter("HospitalFilter", SimpleBeanPropertyFilter.filterOutAllExcept(entity));
            }else{
                filters.addFilter("HospitalFilter", SimpleBeanPropertyFilter.serializeAll());
            }
            jsonwriter = mapper.writer(filters).withDefaultPrettyPrinter();

            String jsonString = jsonwriter.writeValueAsString(db);

            File output = new File(filedir, filepath);
            if (!output.getParentFile().exists()) {
                output.getParentFile().mkdirs();
                output.createNewFile();
            }

            FileWriter writer = new FileWriter(output);
            writer.write(jsonString);
            writer.close();
            System.out.println("Exported to " + filepath);
        }catch (Exception e){
            System.out.println("Some error occurred while exporting the database to XML");
            e.printStackTrace();
        }

        processMenuOption();
    }
}

