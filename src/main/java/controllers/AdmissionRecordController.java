package controllers;

import dao.AdmissionRecordDAO;
import models.AdmissionRecord;
import models.TreatmentRecord;
import views.admission_record.AdmissionRecordRequestData;
import views.generic.*;
import views.patient.PatientRequestData;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class AdmissionRecordController {
    private final AdmissionRecordDAO admissionRecordDAO;

    public AdmissionRecordController(AdmissionRecordDAO admissionRecordDAO) {
        this.admissionRecordDAO = admissionRecordDAO;
    }

    public int requestId(){
        RequestId req = new RequestId("AdmissionRecord");
        HashMap<String, String> patientOptions = req.getInputs();
        int id = Integer.parseInt(patientOptions.get("id"));
        return id;
    }

    public void show(int id) {
        Optional<AdmissionRecord> record = admissionRecordDAO.select(id);
        if(record.isPresent()) {
            //Views
            Show<AdmissionRecord> showAdmissionRecord = new Show<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record.get());
            showAdmissionRecord.setInputs(data);
            showAdmissionRecord.display();

            /*System.out.println("Do you want to export it to XML?");
            Scanner sc = new Scanner(System.in);
            int answer = sc.nextInt();
            if(answer == 1) {
                String patientId = String.valueOf(patient.get().getAdmissionRecordId());
                exportXML("AdmissionRecord" + patientId + ".xml", patient.get());
            }else{
                System.out.println("Going back...");
            }*/
        }
        else{
            System.out.println("AdmissionRecord not found");
        }
    }

    public void delete(int id) {
        int deleted = admissionRecordDAO.delete(id);
        Deleted del = new Deleted();
        HashMap<String, Object> data = new HashMap<>();
        data.put("deletedRows", deleted);
        data.put("deletedId", id);
        data.put("deletedType", "AdmissionRecord");
        del.setInputs(data);
        del.display();
    }

    public void insert(AdmissionRecord record) {
        int affectedRows = admissionRecordDAO.insert(record);
        if(affectedRows > 0) {
            Inserted<AdmissionRecord> insertedAdmissionRecord = new Inserted<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record);
            insertedAdmissionRecord.setInputs(data);
            insertedAdmissionRecord.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public void update(int id, AdmissionRecord admissionRecord) {
        int affectedRows = admissionRecordDAO.update(id, admissionRecord);
        if(affectedRows > 0) {
            Updated<AdmissionRecord> updatedAdmissionRecord = new Updated<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", admissionRecord);
            data.put("updatedRows", affectedRows);
            data.put("updatedId", id);
            updatedAdmissionRecord.setInputs(data);
            updatedAdmissionRecord.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public AdmissionRecord getData(){
        AdmissionRecordRequestData requestAdmissionRecordData = new AdmissionRecordRequestData();
        requestAdmissionRecordData.display();
        HashMap<String, String> pd = requestAdmissionRecordData.getInputs();

        AdmissionRecord admissionRecord = new AdmissionRecord()
                .setPatientId(Integer.parseInt(pd.get("patientId")))
                .setConsultationId(Integer.parseInt(pd.get("consultationId")))
                .setAdmissionDate(Date.valueOf(pd.get("admissionDate")))
                .setDischargeDate(Date.valueOf(pd.get("dischargeDate")))
                .setRoomNumber(Integer.parseInt(pd.get("roomNumber")))
                .setBedNumber(Integer.parseInt(pd.get("bedNumber")));
        return admissionRecord;
    }

    public List<AdmissionRecord> index(){
        List<AdmissionRecord> admissionRecords = admissionRecordDAO.selectAll();
        HashMap<String, Object> data = new HashMap<>();
        data.put("element", admissionRecords);
        Index<AdmissionRecord> index = new Index<>();
        index.setInputs(data);

        index.display();
        return admissionRecords;

        /*System.out.println("Do you want to export it to XML?");
        Scanner sc = new Scanner(System.in);
        int answer = sc.nextInt();
        if(answer == 1) {
            AdmissionRecords patientList = new AdmissionRecords();
            patientList.setAdmissionRecords(patients);
            exportXML("AdmissionRecordList.xml", patientList);
        }else{
            System.out.println("Going back...");
        }*/
    }

    public List<AdmissionRecord> selectAll(){
        return admissionRecordDAO.selectAll();
    }

}
