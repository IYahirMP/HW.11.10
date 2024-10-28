package controllers;

import dao.interfaces.EmergencyContactDAO;
import models.EmergencyContact;
import views.emergency_contact.EmergencyContactRequestData;
import views.generic.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class EmergencyContactController {
    private final EmergencyContactDAO emergencyContactDAO;

    public EmergencyContactController(EmergencyContactDAO emergencyContactDAO) {
        this.emergencyContactDAO = emergencyContactDAO;
    }

    public int requestId(){
        RequestId req = new RequestId("EmergencyContact");
        HashMap<String, String> patientOptions = req.getInputs();
        int id = Integer.parseInt(patientOptions.get("id"));
        return id;
    }

    public void show(int id) {
        Optional<EmergencyContact> record = emergencyContactDAO.select(id);
        if(record.isPresent()) {
            //Views
            Show<EmergencyContact> showEmergencyContact = new Show<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record.get());
            showEmergencyContact.setInputs(data);
            showEmergencyContact.display();
        }
        else{
            System.out.println("EmergencyContact not found");
        }
    }

    public void delete(int id) {
        int deleted = emergencyContactDAO.delete(id);
        Deleted del = new Deleted();
        HashMap<String, Object> data = new HashMap<>();
        data.put("deletedRows", deleted);
        data.put("deletedId", id);
        data.put("deletedType", "EmergencyContact");
        del.setInputs(data);
        del.display();
    }

    public void insert(EmergencyContact record) {
        int affectedRows = emergencyContactDAO.insert(record);
        if(affectedRows > 0) {
            Inserted<EmergencyContact> insertedEmergencyContact = new Inserted<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", record);
            insertedEmergencyContact.setInputs(data);
            insertedEmergencyContact.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public void update(int id, EmergencyContact emergencyContact) {
        int affectedRows = emergencyContactDAO.update(id, emergencyContact);
        if(affectedRows > 0) {
            Updated<EmergencyContact> updatedEmergencyContact = new Updated<>();
            HashMap<String, Object> data = new HashMap<>();
            data.put("element", emergencyContact);
            data.put("updatedRows", affectedRows);
            data.put("updatedId", id);
            updatedEmergencyContact.setInputs(data);
            updatedEmergencyContact.display();
        }else{
            System.out.println("Insert failed");
        }
    }

    public EmergencyContact getData(){
        EmergencyContactRequestData requestEmergencyContactData = new EmergencyContactRequestData();
        requestEmergencyContactData.display();
        HashMap<String, String> pd = requestEmergencyContactData.getInputs();

        EmergencyContact emergencyContact = new EmergencyContact()
                .setPatientId(Integer.parseInt(pd.get("patientId")))
                .setAddress(pd.get("address"))
                .setPhone(pd.get("phone"))
                .setName(pd.get("name"));
        return emergencyContact;
    }

    public List<EmergencyContact> index(){
        List<EmergencyContact> emergencyContacts = emergencyContactDAO.selectAll();
        HashMap<String, Object> data = new HashMap<>();
        data.put("element", emergencyContacts);
        Index<EmergencyContact> index = new Index<>();
        index.setInputs(data);

        index.display();
        return emergencyContacts;

    }

    public List<EmergencyContact> selectAll(){
        return emergencyContactDAO.selectAll();
    }

}
