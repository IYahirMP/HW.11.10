package menu;

import controllers.*;
import dao.factories.DAOFactory;
import dao.interfaces.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import views.EntitySelection;

import java.util.HashMap;

import static java.lang.System.exit;

// Abstract Menu Handler Class
public abstract class MenuHandler {
    protected static final Logger logger = LogManager.getLogger(MenuHandler.class);

    protected DAOFactory currentDataSourceFactory;
    protected AdmissionRecordController admissionRecordController;
    protected ConsultationController consultationController;
    protected DoctorController doctorController;
    protected EmergencyContactController emergencyContactController;
    protected InvoiceController invoiceController;
    protected InvoiceHasServiceController invoiceHasServiceController;
    protected MedicineController medicineController;
    protected PatientController patientController;
    protected PrescriptionController prescriptionController;
    protected PrescriptionHasMedicineController prescriptionHasMedicineController;
    protected ServiceController serviceController;
    protected TreatmentRecordController treatmentRecordController;

    public MenuHandler(DAOFactory factory) {
        // Initialize DAO and Controllers here
        currentDataSourceFactory = factory;
        AdmissionRecordDAO admissionRecordDAO = currentDataSourceFactory.getAdmissionRecordDAO();
        ConsultationDAO consultationDAO = currentDataSourceFactory.getConsultationDAO();
        DoctorDAO doctorDAO = currentDataSourceFactory.getDoctorDAO();
        EmergencyContactDAO emergencyContactDAO = currentDataSourceFactory.getEmergencyContactDAO();
        InvoiceDAO invoiceDAO = currentDataSourceFactory.getInvoiceDAO();
        InvoiceHasServiceDAO invoiceHasServiceDAO = currentDataSourceFactory.getInvoiceHasServiceDAO();
        MedicineDAO medicineDAO = currentDataSourceFactory.getMedicineDAO();
        PatientDAO patientDAO = currentDataSourceFactory.getPatientDAO();
        PrescriptionDAO prescriptionDAO = currentDataSourceFactory.getPrescriptionDAO();
        PrescriptionHasMedicineDAO prescriptionHasMedicineDAO = currentDataSourceFactory.getPrescriptionHasMedicineDAO();
        ServiceDAO serviceDAO = currentDataSourceFactory.getServiceDAO();
        TreatmentRecordDAO treatmentRecordDAO = currentDataSourceFactory.getTreatmentRecordDAO();

        admissionRecordController = new AdmissionRecordController(admissionRecordDAO);
        consultationController = new ConsultationController(consultationDAO);
        doctorController = new DoctorController(doctorDAO);
        emergencyContactController = new EmergencyContactController(emergencyContactDAO);
        invoiceController = new InvoiceController(invoiceDAO);
        invoiceHasServiceController = new InvoiceHasServiceController(invoiceHasServiceDAO);
        medicineController = new MedicineController(medicineDAO);
        patientController = new PatientController(patientDAO);
        prescriptionController = new PrescriptionController(prescriptionDAO);
        prescriptionHasMedicineController = new PrescriptionHasMedicineController(prescriptionHasMedicineDAO);
        serviceController = new ServiceController(serviceDAO);
        treatmentRecordController = new TreatmentRecordController(treatmentRecordDAO);
    }

    public static void displayEntitySelection(DAOFactory daoFactory){
        EntitySelection entitySelection = new EntitySelection();
        entitySelection.display();
        HashMap<String,String> entityOption = entitySelection.getInputs();
        int value = Integer.parseInt(entityOption.get("menuOption"));
        switch (value){
            case 1 -> new PatientMenuHandler(daoFactory).processMenuOption();
            case 2 -> new EmergencyContactMenuHandler(daoFactory).processMenuOption();
            case 3 -> new DoctorMenuHandler(daoFactory).processMenuOption();
            case 4 -> new ConsultationMenuHandler(daoFactory).processMenuOption();
            case 5 -> new PrescriptionMenuHandler(daoFactory).processMenuOption();
            case 6 -> new MedicineMenuHandler(daoFactory).processMenuOption();
            case 7 -> new AdmissionRecordMenuHandler(daoFactory).processMenuOption();
            case 8 -> new TreatmentRecordMenuHandler(daoFactory).processMenuOption();
            case 9 -> new InvoiceMenuHandler(daoFactory).processMenuOption();
            case 10 -> new ServiceMenuHandler(daoFactory).processMenuOption();
            case 11 -> new ExportXMLMenuHandler(daoFactory).processMenuOption();
            case 12 -> new ExportJSONMenuHandler(daoFactory).processMenuOption();
            case 13 -> new InvoiceHasServiceMenuHandler(daoFactory).processMenuOption();
            case 14 -> new PrescriptionHasMedicineMenuHandler(daoFactory).processMenuOption();
            default -> exit(0);
        }
    }

    public abstract void processMenuOption();

}