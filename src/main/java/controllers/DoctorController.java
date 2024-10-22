package controllers;

import dao.DoctorDAO;
import models.Doctor;
import models.EmergencyContact;
import models.TreatmentRecord;
import views.generic.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class DoctorController {
    private final DoctorDAO doctorDAO;

    public DoctorController(DoctorDAO consultationDAO) {
        this.doctorDAO = consultationDAO;
    }

    public int requestId(){
        RequestId req = new RequestId("Doctor");
        HashMap<String, String> patientOptions = req.getInputs();
        int id = Integer.parseInt(patientOptions.get("id"));
        return id;
    }

    public void show(int id) {
        Optional<Doctor> record = doctorDAO.select(id);
        if(record.isPresent()) {
            //Views
            Show<Doctor> showDoctor = new Show<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record.get());
            showDoctor.setInputs(data);
            showDoctor.display();
        }
        else{
            System.out.println("Doctor not found");
        }
    }

    public void delete(int id) {
        int deleted = doctorDAO.delete(id);
        Deleted del = new Deleted();
        HashMap<String, Object> data = new HashMap<>();
        data.put("deletedRows", deleted);
        data.put("deletedId", id);
        data.put("deletedType", "Doctor");
        del.setInputs(data);
        del.display();
    }

    public void insert(Doctor record) {
        int affectedRows = doctorDAO.insert(record);
        if(affectedRows > 0) {
            Inserted<Doctor> insertedDoctor = new Inserted<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record);
            insertedDoctor.setInputs(data);
            insertedDoctor.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public void update(int id, Doctor consultation) {
        int affectedRows = doctorDAO.update(id, consultation);
        if(affectedRows > 0) {
            Updated<Doctor> updatedDoctor = new Updated<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", consultation);
            data.put("updatedRows", affectedRows);
            data.put("updatedId", id);
            updatedDoctor.setInputs(data);
            updatedDoctor.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public Doctor getData(){/*
        DoctorRequestData requestDoctorData = new DoctorRequestData();
        requestDoctorData.display();
        HashMap<String, String> pd = requestDoctorData.getInputs();

        Doctor consultation = new Doctor()
                .setPatientId(Integer.parseInt(pd.get("patientId")))
                .setDoctorId(Integer.parseInt(pd.get("consultationId")))
                .setAdmissionDate(Date.valueOf(pd.get("admissionDate")))
                .setDischargeDate(Date.valueOf(pd.get("dischargeDate")))
                .setRoomNumber(Integer.parseInt(pd.get("roomNumber")))
                .setBedNumber(Integer.parseInt(pd.get("bedNumber")));
        return consultation;*/
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Doctor> index(){
        List<Doctor> doctors = doctorDAO.selectAll();
        HashMap<String, Object> data = new HashMap<>();
        data.put("element", doctors);
        Index<Doctor> index = new Index<>();
        index.setInputs(data);

        index.display();
        return doctors;
        /*System.out.println("Do you want to export it to XML?");
        Scanner sc = new Scanner(System.in);
        int answer = sc.nextInt();
        if(answer == 1) {
            Doctors patientList = new Doctors();
            patientList.setDoctors(patients);
            exportXML("DoctorList.xml", patientList);
        }else{
            System.out.println("Going back...");
        }*/
    }

    public List<Doctor> selectAll(){
        return doctorDAO.selectAll();
    }

}
