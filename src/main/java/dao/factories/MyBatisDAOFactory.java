package dao.factories;

import dao.interfaces.*;
import dao.mybatis.*;
import dao.mybatis.proxy.MyBatisInvoiceHasServiceDAOProxy;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.SQLException;

public class MyBatisDAOFactory extends DAOFactory{
    public static SqlSessionFactory sessionFactory;

    public static SqlSession createConnection() throws SQLException{
            return sessionFactory.openSession();
    }

    /**
     * @return
     */
    @Override
    public ConsultationDAO getConsultationDAO() {
        return new MyBatisConsultationDAO();
    }

    /**
     * @return
     */
    @Override
    public PatientDAO getPatientDAO() {
        return new MyBatisPatientDAO();
    }

    /**
     * @return
     */
    @Override
    public DoctorDAO getDoctorDAO() {
        return new MyBatisDoctorDAO();
    }

    /**
     * @return
     */
    @Override
    public AdmissionRecordDAO getAdmissionRecordDAO() {
        return new MyBatisAdmissionRecordDAO();
    }

    /**
     * @return
     */
    @Override
    public EmergencyContactDAO getEmergencyContactDAO() {
        return new MyBatisEmergencyContactDAO();
    }

    /**
     * @return
     */
    @Override
    public InvoiceDAO getInvoiceDAO() {
        return new MyBatisInvoiceDAO();
    }

    /**
     * @return
     */
    @Override
    public InvoiceHasServiceDAO getInvoiceHasServiceDAO() {
        return new MyBatisInvoiceHasServiceDAOProxy();
    }

    /**
     * @return
     */
    @Override
    public MedicineDAO getMedicineDAO() {
        return new MyBatisMedicineDAO();
    }

    /**
     * @return
     */
    @Override
    public PrescriptionDAO getPrescriptionDAO() {
        return new MyBatisPrescriptionDAO();
    }

    /**
     * @return
     */
    @Override
    public PrescriptionHasMedicineDAO getPrescriptionHasMedicineDAO() {
        return new MyBatisPrescriptionHasMedicineDAO();
    }

    /**
     * @return
     */
    @Override
    public ServiceDAO getServiceDAO() {
        return new MyBatisServiceDAO();
    }

    /**
     * @return
     */
    @Override
    public TreatmentRecordDAO getTreatmentRecordDAO() {
        return new MyBatisTreatmentRecordDAO();
    }
}
