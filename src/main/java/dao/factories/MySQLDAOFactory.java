package dao.factories;

import dao.*;
import dao.mysql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDAOFactory extends DAOFactory{
    public static String url = "";
    public static String database = "";
    public static String user = "";
    public static String password = "";

    public static Connection createConnection() throws SQLException{
            return DriverManager.getConnection("jdbc:mysql://" + url + database, user, password);
    }

    /**
     * @return
     */
    @Override
    public ConsultationDAO getConsultationDAO() {
        return new MySQLConsultationDAO();
    }

    /**
     * @return
     */
    @Override
    public PatientDAO getPatientDAO() {
        return new MySQLPatientDAO();
    }

    /**
     * @return
     */
    @Override
    public DoctorDAO getDoctorDAO() {
        return new MySQLDoctorDAO();
    }

    /**
     * @return
     */
    @Override
    public AdmissionRecordDAO getAdmissionRecordDAO() {
        return new MySQLAdmissionRecordDAO();
    }

    /**
     * @return
     */
    @Override
    public EmergencyContactDAO getEmergencyContactDAO() {
        return new MySQLEmergencyContactDAO();
    }

    /**
     * @return
     */
    @Override
    public InvoiceDAO getInvoiceDAO() {
        return new MySQLInvoiceDAO();
    }

    /**
     * @return
     */
    @Override
    public InvoiceHasServiceDAO getInvoiceHasServiceDAO() {
        return new MySQLInvoiceHasServiceDAO();
    }

    /**
     * @return
     */
    @Override
    public MedicineDAO getMedicineDAO() {
        return new MySQLMedicineDAO();
    }

    /**
     * @return
     */
    @Override
    public PrescriptionDAO getPrescriptionDAO() {
        return new MySQLPrescriptionDAO();
    }

    /**
     * @return
     */
    @Override
    public PrescriptionHasMedicineDAO getPrescriptionHasMedicineDAO() {
        return new MySQLPrescriptionHasMedicineDAO();
    }

    /**
     * @return
     */
    @Override
    public ServiceDAO getServiceDAO() {
        return new MySQLServiceDAO();
    }

    /**
     * @return
     */
    @Override
    public TreatmentRecordDAO getTreatmentRecordDAO() {
        return new MySQLTreatmentRecordDAO();
    }
}
