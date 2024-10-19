import controllers.MainController;
import models.Patient;
import models.xml.Patients;

import javax.xml.bind.*;
import java.io.File;
import java.util.List;


public class main {

    private static MainController mainController = new MainController();

    public static void main(String[] args) throws Exception {
        mainController.start();
        /*JAXBContext jaxbContext = JAXBContext.newInstance(Patients.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Patients patients = (Patients) jaxbUnmarshaller.unmarshal(new File("PatientList.xml"));

        //Want to delete one
        int id = 3;
        List<Patient> patientsNew = patients.getPatients().stream().filter((patient) -> patient.getPatientId() != id).toList();

        for(Patient patient : patientsNew) {
            System.out.println(patient);
        }*/
    }
}