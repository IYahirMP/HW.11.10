package controllers;

import dao.interfaces.PatientDAO;
import models.Patient;
import views.generic.*;
import views.patient.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class PatientController {
    private final PatientDAO patientDAO;

    public PatientController(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    public int requestPatientId(){
        RequestId req = new RequestId("Patient");
        HashMap<String, String> patientOptions = req.getInputs();
        int id = Integer.parseInt(patientOptions.get("id"));
        return id;
    }

    public void show(int id) {
        Optional<Patient> patient = patientDAO.select(id);
        if(patient.isPresent()) {
            //Views
            Show<Patient> showPatient = new Show<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", patient.get());
            showPatient.setInputs(data);
            showPatient.display();

            /*System.out.println("Do you want to export it to XML?");
            Scanner sc = new Scanner(System.in);
            int answer = sc.nextInt();
            if(answer == 1) {
                String patientId = String.valueOf(patient.get().getPatientId());
                exportXML("Patient" + patientId + ".xml", patient.get());
            }else{
                System.out.println("Going back...");
            }*/
        }
        else{
            System.out.println("Patient not found");
        }
    }

    public void delete(int id) {
        int deleted = patientDAO.delete(id);
        Deleted del = new Deleted();
        HashMap<String, Object> data = new HashMap<>();
        data.put("deletedRows", deleted);
        data.put("deletedId", id);
        data.put("deletedType", "Patient");
        del.setInputs(data);
        del.display();
    }

    public void insert(Patient patient) {
        int affectedRows = patientDAO.insert(patient);
        if(affectedRows > 0) {
            Inserted<Patient> insertedPatient = new Inserted<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", patient);
            insertedPatient.setInputs(data);
            insertedPatient.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public void update(int id, Patient patient) {
        int affectedRows = patientDAO.update(id, patient);
        if(affectedRows > 0) {
            Updated<Patient> updatedPatient = new Updated<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", patient);
            data.put("updatedRows", affectedRows);
            data.put("updatedId", id);
            updatedPatient.setInputs(data);
            updatedPatient.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public Patient getPatientData(){
        PatientRequestData requestPatientData = new PatientRequestData();
        requestPatientData.display();
        HashMap<String, String> pd = requestPatientData.getInputs();

        Patient patient = new Patient()
                .setAddress(pd.get("address"))
                .setPhone(pd.get("phone"))
                .setName(pd.get("name"))
                .setAge(Integer.parseInt(pd.get("age")));
        return patient;
    }

    public List<Patient> index(){
        List<Patient> patients = patientDAO.selectAll();
        HashMap<String, Object> data = new HashMap<>();
        data.put("element", patients);
        Index<Patient> index = new Index<>();
        index.setInputs(data);

        index.display();
        return patients;

        /*System.out.println("Do you want to export it to XML?");
        Scanner sc = new Scanner(System.in);
        int answer = sc.nextInt();
        if(answer == 1) {
            Patients patientList = new Patients();
            patientList.setPatients(patients);
            exportXML("PatientList.xml", patientList);
        }else{
            System.out.println("Going back...");
        }*/
    }

    public List<Patient> selectAll(){
        return patientDAO.selectAll();
    }

}
