package controllers;

import dao.TreatmentRecordDAO;
import models.TreatmentRecord;
import views.generic.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class TreatmentRecordController {
    private final TreatmentRecordDAO treatmentRecordDAO;

    public TreatmentRecordController(TreatmentRecordDAO treatmentRecordDAO) {
        this.treatmentRecordDAO = treatmentRecordDAO;
    }

    public int requestId(){
        RequestId req = new RequestId("TreatmentRecord");
        HashMap<String, String> patientOptions = req.getInputs();
        int id = Integer.parseInt(patientOptions.get("id"));
        return id;
    }

    public void show(int id) {
        Optional<TreatmentRecord> record = treatmentRecordDAO.select(id);
        if(record.isPresent()) {
            //Views
            Show<TreatmentRecord> showTreatmentRecord = new Show<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record.get());
            showTreatmentRecord.setInputs(data);
            showTreatmentRecord.display();
        }
        else{
            System.out.println("TreatmentRecord not found");
        }
    }

    public void delete(int id) {
        int deleted = treatmentRecordDAO.delete(id);
        Deleted del = new Deleted();
        HashMap<String, Object> data = new HashMap<>();
        data.put("deletedRows", deleted);
        data.put("deletedId", id);
        data.put("deletedType", "TreatmentRecord");
        del.setInputs(data);
        del.display();
    }

    public void insert(TreatmentRecord record) {
        int affectedRows = treatmentRecordDAO.insert(record);
        if(affectedRows > 0) {
            Inserted<TreatmentRecord> insertedTreatmentRecord = new Inserted<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record);
            insertedTreatmentRecord.setInputs(data);
            insertedTreatmentRecord.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public void update(int id, TreatmentRecord consultation) {
        int affectedRows = treatmentRecordDAO.update(id, consultation);
        if(affectedRows > 0) {
            Updated<TreatmentRecord> updatedTreatmentRecord = new Updated<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", consultation);
            data.put("updatedRows", affectedRows);
            data.put("updatedId", id);
            updatedTreatmentRecord.setInputs(data);
            updatedTreatmentRecord.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public TreatmentRecord getData(){/*
        TreatmentRecordRequestData requestTreatmentRecordData = new TreatmentRecordRequestData();
        requestTreatmentRecordData.display();
        HashMap<String, String> pd = requestTreatmentRecordData.getInputs();

        TreatmentRecord consultation = new TreatmentRecord()
                .setPatientId(Integer.parseInt(pd.get("patientId")))
                .setTreatmentRecordId(Integer.parseInt(pd.get("consultationId")))
                .setAdmissionDate(Date.valueOf(pd.get("admissionDate")))
                .setDischargeDate(Date.valueOf(pd.get("dischargeDate")))
                .setRoomNumber(Integer.parseInt(pd.get("roomNumber")))
                .setBedNumber(Integer.parseInt(pd.get("bedNumber")));
        return consultation;*/
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void index(){
        List<TreatmentRecord> treatmentRecords = treatmentRecordDAO.selectAll();
        HashMap<String, Object> data = new HashMap<>();
        data.put("element", treatmentRecords);
        Index<TreatmentRecord> index = new Index<>();
        index.setInputs(data);

        index.display();

        /*System.out.println("Do you want to export it to XML?");
        Scanner sc = new Scanner(System.in);
        int answer = sc.nextInt();
        if(answer == 1) {
            TreatmentRecords patientList = new TreatmentRecords();
            patientList.setTreatmentRecords(patients);
            exportXML("TreatmentRecordList.xml", patientList);
        }else{
            System.out.println("Going back...");
        }*/
    }

    public List<TreatmentRecord> selectAll(){
        return treatmentRecordDAO.selectAll();
    }

}
