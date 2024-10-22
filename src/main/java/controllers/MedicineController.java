package controllers;

import dao.MedicineDAO;
import models.Medicine;
import models.TreatmentRecord;
import views.generic.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MedicineController {
    private final MedicineDAO medicineDAO;

    public MedicineController(MedicineDAO medicineDAO) {
        this.medicineDAO = medicineDAO;
    }

    public int requestId(){
        RequestId req = new RequestId("Medicine");
        HashMap<String, String> patientOptions = req.getInputs();
        int id = Integer.parseInt(patientOptions.get("id"));
        return id;
    }

    public void show(int id) {
        Optional<Medicine> record = medicineDAO.select(id);
        if(record.isPresent()) {
            //Views
            Show<Medicine> showMedicine = new Show<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record.get());
            showMedicine.setInputs(data);
            showMedicine.display();
        }
        else{
            System.out.println("Medicine not found");
        }
    }

    public void delete(int id) {
        int deleted = medicineDAO.delete(id);
        Deleted del = new Deleted();
        HashMap<String, Object> data = new HashMap<>();
        data.put("deletedRows", deleted);
        data.put("deletedId", id);
        data.put("deletedType", "Medicine");
        del.setInputs(data);
        del.display();
    }

    public void insert(Medicine record) {
        int affectedRows = medicineDAO.insert(record);
        if(affectedRows > 0) {
            Inserted<Medicine> insertedMedicine = new Inserted<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record);
            insertedMedicine.setInputs(data);
            insertedMedicine.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public void update(int id, Medicine consultation) {
        int affectedRows = medicineDAO.update(id, consultation);
        if(affectedRows > 0) {
            Updated<Medicine> updatedMedicine = new Updated<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", consultation);
            data.put("updatedRows", affectedRows);
            data.put("updatedId", id);
            updatedMedicine.setInputs(data);
            updatedMedicine.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public Medicine getData(){/*
        MedicineRequestData requestMedicineData = new MedicineRequestData();
        requestMedicineData.display();
        HashMap<String, String> pd = requestMedicineData.getInputs();

        Medicine consultation = new Medicine()
                .setPatientId(Integer.parseInt(pd.get("patientId")))
                .setMedicineId(Integer.parseInt(pd.get("consultationId")))
                .setAdmissionDate(Date.valueOf(pd.get("admissionDate")))
                .setDischargeDate(Date.valueOf(pd.get("dischargeDate")))
                .setRoomNumber(Integer.parseInt(pd.get("roomNumber")))
                .setBedNumber(Integer.parseInt(pd.get("bedNumber")));
        return consultation;*/
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Medicine> index(){
        List<Medicine> medicines = medicineDAO.selectAll();
        HashMap<String, Object> data = new HashMap<>();
        data.put("element", medicines);
        Index<Medicine> index = new Index<>();
        index.setInputs(data);

        index.display();
        return medicines;

        /*System.out.println("Do you want to export it to XML?");
        Scanner sc = new Scanner(System.in);
        int answer = sc.nextInt();
        if(answer == 1) {
            Medicines patientList = new Medicines();
            patientList.setMedicines(patients);
            exportXML("MedicineList.xml", patientList);
        }else{
            System.out.println("Going back...");
        }*/
    }

    public List<Medicine> selectAll(){
        return medicineDAO.selectAll();
    }

}
