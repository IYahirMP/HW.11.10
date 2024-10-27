package menu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dao.factories.DAOFactory;
import models.*;
import models.xml.Hospital;
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
        logger.info("Retrieving information from the database.");
        try {
            logger.info("Retrieving information from AdmissionRecords");
            List<AdmissionRecord> admissionRecords = admissionRecordController.selectAll();

            logger.info("Retrieving information from Consultations");
            List<Consultation> consultations = consultationController.selectAll();

            logger.info("Retrieving information from Doctors");
            List<Doctor> doctors = doctorController.selectAll();

            logger.info("Retrieving information from EmergencyContact");
            List<EmergencyContact> emergencyContacts = emergencyContactController.selectAll();

            logger.info("Retrieving information from Invoice");
            List<Invoice> invoices = invoiceController.selectAll();

            logger.info("Retrieving information from InvoiceHasService");
            List<InvoiceHasService> invoiceHasServices = invoiceHasServiceController.selectAll();

            logger.info("Retrieving information from Medicine");
            List<Medicine> medicines = medicineController.selectAll();

            logger.info("Retrieving information from Patient");
            List<Patient> patients = patientController.selectAll();

            logger.info("Retrieving information from Prescription");
            List<Prescription> prescriptions = prescriptionController.selectAll();

            logger.info("Retrieving information from PrescriptionHasMedicine");
            List<PrescriptionHasMedicine> prescriptionHasMedicines = prescriptionHasMedicineController.selectAll();

            logger.info("Retrieving information from Service");
            List<Service> services = serviceController.selectAll();

            logger.info("Retrieving information from TreatmentRecord");
            List<TreatmentRecord> treatmentRecords = treatmentRecordController.selectAll();

            logger.trace("Creating new Hospital object with retrieved data.");
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

            logger.trace("Creating ExportEntity view object");
            ExportEntity exportView = new ExportEntity();
            logger.trace("Display ExportEntity view");
            exportView.display();

            logger.info("Retrieving the table to export from user input");
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

            logger.trace("Creating ObjectMapper");
            ObjectMapper mapper = new ObjectMapper();
            logger.trace("Registering Mapper modules");
            mapper.registerModule(new JavaTimeModule());

            ObjectWriter jsonwriter;

            if (entity.equals("Exit")){
                logger.trace("Shutting down the program");
                exit(0);
            }

            if (entity.equals("Restart")){
                logger.trace("Restarting JSONMenuHandler menu");
                processMenuOption();
            }

            SimpleFilterProvider filters = new SimpleFilterProvider();
            if (!entity.isEmpty()) {
                logger.trace("Adding a filter for selected entity.");
                filters.addFilter("HospitalFilter", SimpleBeanPropertyFilter.filterOutAllExcept(entity));
            }else{
                logger.trace("Adding a filter to serialize all entities.");
                filters.addFilter("HospitalFilter", SimpleBeanPropertyFilter.serializeAll());
            }
            logger.trace("Configuring pretty printer");
            jsonwriter = mapper.writer(filters).withDefaultPrettyPrinter();

            logger.trace("Parsing database object into a JSON string.");
            String jsonString = jsonwriter.writeValueAsString(db);

            logger.trace("Creating a new file object with name {}", filepath);
            File output = new File(filedir, filepath);
            if (!output.getParentFile().exists()) {
                logger.info("Creating output directory {}", output.getParentFile().getAbsolutePath());
                output.getParentFile().mkdirs();
                logger.info("Creating new file in device storage");
                output.createNewFile();
            }

            FileWriter writer = new FileWriter(output);
            logger.trace("Writing to file {}", filepath);
            writer.write(jsonString);
            logger.trace("Closing output stream");
            writer.close();
            System.out.println("Exported to " + filepath);
        }catch (Exception e){
            System.out.println("Some error occurred while exporting the database to XML");
            e.printStackTrace();
        }

        processMenuOption();
    }
}

