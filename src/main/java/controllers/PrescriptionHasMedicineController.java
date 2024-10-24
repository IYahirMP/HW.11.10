package controllers;

import dao.interfaces.PrescriptionHasMedicineDAO;
import models.PrescriptionHasMedicine;
import views.generic.*;
import views.prescription_has_medicine.PrescriptionHasMedicineRequestData;

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

    public void showByPrescription(int prescriptionId) {
        List<PrescriptionHasMedicine> record = prescriptionHasMedicineDAO.selectByPrescription(prescriptionId);
        if(!record.isEmpty()) {
            Index<PrescriptionHasMedicine> showPrescriptionHasMedicine = new Index<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record);
            showPrescriptionHasMedicine.setInputs(data);
            showPrescriptionHasMedicine.display();
        }
        else{
            System.out.println("PrescriptionHasMedicine not found");
        }
    }

    public void showByMedicine(int medicineId) {
        List<PrescriptionHasMedicine> record = prescriptionHasMedicineDAO.selectByMedicine(medicineId);
        if(!record.isEmpty()) {
            Index<PrescriptionHasMedicine> showPrescriptionHasMedicine = new Index<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record);
            showPrescriptionHasMedicine.setInputs(data);
            showPrescriptionHasMedicine.display();
        }
        else{
            System.out.println("PrescriptionHasMedicine not found");
        }
    }

    public void show(int prescriptionId, int medicineId) {
        Optional<PrescriptionHasMedicine> record = prescriptionHasMedicineDAO.select(prescriptionId, medicineId);
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

    public void delete(int prescriptionId, int medicineId) {
        int deleted = prescriptionHasMedicineDAO.delete(prescriptionId, medicineId);
        Deleted del = new Deleted();
        HashMap<String, Object> data = new HashMap<>();
        data.put("deletedRows", deleted);
        data.put("deletedId1", prescriptionId);
        data.put("deletedId2", medicineId);
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

    public void update(PrescriptionHasMedicine prescriptionHasMedicine) {
        int affectedRows = prescriptionHasMedicineDAO.update(prescriptionHasMedicine);
        if(affectedRows > 0) {
            Updated<PrescriptionHasMedicine> updatedPrescriptionHasMedicine = new Updated<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", prescriptionHasMedicine);
            data.put("updatedRows", affectedRows);
            data.put("updatedId1", prescriptionHasMedicine.getPrescriptionId());
            data.put("updatedId2", prescriptionHasMedicine.getMedicineId());
            updatedPrescriptionHasMedicine.setInputs(data);
            updatedPrescriptionHasMedicine.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public PrescriptionHasMedicine getData(){
        PrescriptionHasMedicineRequestData requestPrescriptionHasMedicineData = new PrescriptionHasMedicineRequestData();
        requestPrescriptionHasMedicineData.display();
        HashMap<String, String> pd = requestPrescriptionHasMedicineData.getInputs();

        PrescriptionHasMedicine prescriptionHasMedicine = new PrescriptionHasMedicine()
                .setMedicineId(Integer.parseInt(pd.get("medicineId")))
                .setPrescriptionId(Integer.parseInt(pd.get("prescriptionId")))
                .setPrescribedDays(Integer.parseInt(pd.get("prescribedDays")))
                .setPrescribedDose(pd.get("prescribedDose"))
                .setPrescribedTiming(pd.get("prescribedTiming"));

        return prescriptionHasMedicine;
    }

    public List<PrescriptionHasMedicine> index(){
        List<PrescriptionHasMedicine> prescriptionHasMedicines = prescriptionHasMedicineDAO.selectAll();
        HashMap<String, Object> data = new HashMap<>();
        data.put("element", prescriptionHasMedicines);
        Index<PrescriptionHasMedicine> index = new Index<>();
        index.setInputs(data);

        index.display();
        return prescriptionHasMedicines;
    }

    public List<PrescriptionHasMedicine> selectAll(){
        return prescriptionHasMedicineDAO.selectAll();
    }

}
