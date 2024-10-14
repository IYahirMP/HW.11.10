package dao.factories;

import dao.*;
import dao.mysql.MySQLPatientDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDAOFactory extends DAOFactory{
    public static final String URL = "jdbc:mysql://localhost:3306/hospital";
    public static final String USER = "root";
    public static final String PASS = "Halocer123";

    public static Connection createConnection(){
        try{
            return DriverManager.getConnection(URL, USER, PASS);
        }catch( SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return
     */
    @Override
    public ConsultationDAO getConsultationDAO() {
        return null;
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
        return null;
    }

    /**
     * @return
     */
    @Override
    public AdmissionRecordDAO getAdmissionRecordDAO() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public EmergencyContactDAO getEmergencyContactDAO() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public InvoiceDAO getInvoiceDAO() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public InvoiceHasServiceDAO getInvoiceHasServiceDAO() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public MedicineDAO getMedicineDAO() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public PrescriptionDAO getPrescriptionDAO() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public PrescriptionHasMedicineDAO getPrescriptionHasMedicineDAO() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public ServiceDAO getServiceDAO() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public TreatmentRecordDAO getTreatmentRecordDAO() {
        return null;
    }
}
