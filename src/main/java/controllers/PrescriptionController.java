package controllers;

import dao.interfaces.PrescriptionDAO;
import models.Prescription;
import views.generic.*;
import views.prescription.PrescriptionRequestData;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class PrescriptionController {
    private final PrescriptionDAO prescriptionDAO;

    public PrescriptionController(PrescriptionDAO prescriptionDAO) {
        this.prescriptionDAO = prescriptionDAO;
    }

    public int requestId(){
        RequestId req = new RequestId("Prescription");
        HashMap<String, String> patientOptions = req.getInputs();
        int id = Integer.parseInt(patientOptions.get("id"));
        return id;
    }

    public void show(int id) {
        Optional<Prescription> record = prescriptionDAO.select(id);
        if(record.isPresent()) {
            //Views
            Show<Prescription> showPrescription = new Show<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record.get());
            showPrescription.setInputs(data);
            showPrescription.display();
        }
        else{
            System.out.println("Prescription not found");
        }
    }

    public void delete(int id) {
        int deleted = prescriptionDAO.delete(id);
        Deleted del = new Deleted();
        HashMap<String, Object> data = new HashMap<>();
        data.put("deletedRows", deleted);
        data.put("deletedId", id);
        data.put("deletedType", "Prescription");
        del.setInputs(data);
        del.display();
    }

    public void insert(Prescription record) {
        int affectedRows = prescriptionDAO.insert(record);
        if(affectedRows > 0) {
            Inserted<Prescription> insertedPrescription = new Inserted<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record);
            insertedPrescription.setInputs(data);
            insertedPrescription.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public void update(int id, Prescription prescription) {
        int affectedRows = prescriptionDAO.update(id, prescription);
        if(affectedRows > 0) {
            Updated<Prescription> updatedPrescription = new Updated<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", prescription);
            data.put("updatedRows", affectedRows);
            data.put("updatedId", id);
            updatedPrescription.setInputs(data);
            updatedPrescription.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public Prescription getData(){
        PrescriptionRequestData requestPrescriptionData = new PrescriptionRequestData();
        requestPrescriptionData.display();
        HashMap<String, String> pd = requestPrescriptionData.getInputs();

        Prescription prescription = new Prescription()
                .setPatientId(Integer.parseInt(pd.get("patientId")))
                .setDiagnose(pd.get("diagnose"));
        return prescription;
    }

    public List<Prescription> index(){
        List<Prescription> prescriptions = prescriptionDAO.selectAll();
        HashMap<String, Object> data = new HashMap<>();
        data.put("element", prescriptions);
        Index<Prescription> index = new Index<>();
        index.setInputs(data);

        index.display();
        return prescriptions;

    }

    public List<Prescription> selectAll(){
        return prescriptionDAO.selectAll();
    }

}
