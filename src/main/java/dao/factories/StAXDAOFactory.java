package dao.factories;

import dao.interfaces.*;
import dao.stax.*;

public class StAXDAOFactory extends DAOFactory{

    public static String filepath = "";

    /**
     * @return
     */
    @Override
    public ConsultationDAO getConsultationDAO() {
        return new StAXConsultationDAO();
    }

    /**
     * @return
     */
    @Override
    public PatientDAO getPatientDAO() {
        return new StAXPatientDAO();
    }

    /**
     * @return
     */
    @Override
    public DoctorDAO getDoctorDAO() {
        return new StAXDoctorDAO();
    }

    /**
     * @return
     */
    @Override
    public AdmissionRecordDAO getAdmissionRecordDAO() {
        return new StAXAdmissionRecordDAO();
    }

    /**
     * @return
     */
    @Override
    public EmergencyContactDAO getEmergencyContactDAO() {
        return new StAXEmergencyContactDAO();
    }

    /**
     * @return
     */
    @Override
    public InvoiceDAO getInvoiceDAO() {
        return new StAXInvoiceDAO();
    }

    /**
     * @return
     */
    @Override
    public InvoiceHasServiceDAO getInvoiceHasServiceDAO() {
        return new StAXInvoiceHasServiceDAO();
    }

    /**
     * @return
     */
    @Override
    public MedicineDAO getMedicineDAO() {
        return new StAXMedicineDAO();
    }

    /**
     * @return
     */
    @Override
    public PrescriptionDAO getPrescriptionDAO() {
        return new StAXPrescriptionDAO();
    }

    /**
     * @return
     */
    @Override
    public PrescriptionHasMedicineDAO getPrescriptionHasMedicineDAO() {
        return new StAXPrescriptionHasMedicineDAO();
    }

    /**
     * @return
     */
    @Override
    public ServiceDAO getServiceDAO() {
        return new StAXServiceDAO();
    }

    /**
     * @return
     */
    @Override
    public TreatmentRecordDAO getTreatmentRecordDAO() {
        return new StAXTreatmentRecordDAO();
    }
}
