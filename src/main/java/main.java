import dao.PatientDAO;
import dao.factories.MySQLDAOFactory;
import models.Patient;

import java.util.List;
import java.util.Optional;

public class main {
    public static void main(String[] args) {
        MySQLDAOFactory mySQLDAOFactory = new MySQLDAOFactory();
        PatientDAO patientDAO = mySQLDAOFactory.getPatientDAO();
        Optional<Patient> patient = patientDAO.select(1);

        if (patient.isPresent()) {
            System.out.println(patient);
        }

        List<Patient> patients = patientDAO.selectAll();
        for (Patient p : patients) {
            System.out.println(p);
        }

        patientDAO.delete(5);

        Patient patient2 = new Patient()
                .setName("Ivan")
                .setAge(22)
                .setAddress("SomeAddress")
                .setPhone("1231231231");

        patientDAO.update(4, patient2);
    }
}