package controllers;

import dao.PrescriptionHasMedicineDAO;
import models.PrescriptionHasMedicine;
import models.TreatmentRecord;
import views.generic.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class PrescriptionHasMedicineController {
    private final PrescriptionHasMedicineDAO prescriptionHasMedicineDAO;

    public PrescriptionHasMedicineController(PrescriptionHasMedicineDAO prescriptionHasMedicineDAO) {
        this.prescriptionHasMedicineDAO = prescriptionHasMedicineDAO;
    }

    public int requestId(){
        RequestId req = new RequestId("PrescriptionHasMedicine");
        HashMap<String, String> patientOptions = req.getInputs();
        int id = Integer.parseInt(patientOptions.get("id"));
        return id;
    }

    public void show(int id) {
        Optional<PrescriptionHasMedicine> record = prescriptionHasMedicineDAO.select(id);
        if(record.isPresent()) {
            //Views
            Show<PrescriptionHasMedicine> showPrescriptionHasMedicine = new Show<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record.get());
            showPrescriptionHasMedicine.setInputs(data);
            showPrescriptionHasMedicine.display();
        }
        else{
            System.out.println("PrescriptionHasMedicine not found");
        }
    }

    public void delete(int id) {
        int deleted = prescriptionHasMedicineDAO.delete(id);
        Deleted del = new Deleted();
        HashMap<String, Object> data = new HashMap<>();
        data.put("deletedRows", deleted);
        data.put("deletedId", id);
        data.put("deletedType", "PrescriptionHasMedicine");
        del.setInputs(data);
        del.display();
    }

    public void insert(PrescriptionHasMedicine record) {
        int affectedRows = prescriptionHasMedicineDAO.insert(record);
        if(affectedRows > 0) {
            Inserted<PrescriptionHasMedicine> insertedPrescriptionHasMedicine = new Inserted<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record);
            insertedPrescriptionHasMedicine.setInputs(data);
            insertedPrescriptionHasMedicine.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public void update(int id, PrescriptionHasMedicine consultation) {
        int affectedRows = prescriptionHasMedicineDAO.update(id, consultation);
        if(affectedRows > 0) {
            Updated<PrescriptionHasMedicine> updatedPrescriptionHasMedicine = new Updated<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", consultation);
            data.put("updatedRows", affectedRows);
            data.put("updatedId", id);
            updatedPrescriptionHasMedicine.setInputs(data);
            updatedPrescriptionHasMedicine.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public PrescriptionHasMedicine getData(){/*
        PrescriptionHasMedicineRequestData requestPrescriptionHasMedicineData = new PrescriptionHasMedicineRequestData();
        requestPrescriptionHasMedicineData.display();
        HashMap<String, String> pd = requestPrescriptionHasMedicineData.getInputs();

        PrescriptionHasMedicine consultation = new PrescriptionHasMedicine()
                .setPatientId(Integer.parseInt(pd.get("patientId")))
                .setPrescriptionHasMedicineId(Integer.parseInt(pd.get("consultationId")))
                .setAdmissionDate(Date.valueOf(pd.get("admissionDate")))
                .setDischargeDate(Date.valueOf(pd.get("dischargeDate")))
                .setRoomNumber(Integer.parseInt(pd.get("roomNumber")))
                .setBedNumber(Integer.parseInt(pd.get("bedNumber")));
        return consultation;*/
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void index(){
        List<PrescriptionHasMedicine> prescriptionHasMedicines = prescriptionHasMedicineDAO.selectAll();
        HashMap<String, Object> data = new HashMap<>();
        data.put("element", prescriptionHasMedicines);
        Index<PrescriptionHasMedicine> index = new Index<>();
        index.setInputs(data);

        index.display();

        /*System.out.println("Do you want to export it to XML?");
        Scanner sc = new Scanner(System.in);
        int answer = sc.nextInt();
        if(answer == 1) {
            PrescriptionHasMedicines patientList = new PrescriptionHasMedicines();
            patientList.setPrescriptionHasMedicines(patients);
            exportXML("PrescriptionHasMedicineList.xml", patientList);
        }else{
            System.out.println("Going back...");
        }*/
    }

    public List<PrescriptionHasMedicine> selectAll(){
        return prescriptionHasMedicineDAO.selectAll();
    }

}
