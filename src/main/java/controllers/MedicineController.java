package controllers;

import dao.interfaces.MedicineDAO;
import models.Medicine;
import views.generic.*;
import views.medicine.MedicineRequestData;

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

    public void update(int id, Medicine medicine) {
        int affectedRows = medicineDAO.update(id, medicine);
        if(affectedRows > 0) {
            Updated<Medicine> updatedMedicine = new Updated<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", medicine);
            data.put("updatedRows", affectedRows);
            data.put("updatedId", id);
            updatedMedicine.setInputs(data);
            updatedMedicine.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public Medicine getData(){
        MedicineRequestData requestMedicineData = new MedicineRequestData();
        requestMedicineData.display();
        HashMap<String, String> pd = requestMedicineData.getInputs();

        Medicine medicine = new Medicine()
                .setCost(Double.parseDouble(pd.get("cost")))
                .setName(pd.get("name"))
                .setDoseSize(Integer.parseInt(pd.get("doseSize")));
        return medicine;
    }

    public List<Medicine> index(){
        List<Medicine> medicines = medicineDAO.selectAll();
        HashMap<String, Object> data = new HashMap<>();
        data.put("element", medicines);
        Index<Medicine> index = new Index<>();
        index.setInputs(data);

        index.display();
        return medicines;
        
    }

    public List<Medicine> selectAll(){
        return medicineDAO.selectAll();
    }

}
