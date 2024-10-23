package models.xml;

import models.EmergencyContact;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "EmergencyContacts")
public class EmergencyContacts {

    private List<EmergencyContact> emergencyContacts;

    @XmlElement(name = "EmergencyContact")
    public List<EmergencyContact> getEmergencyContacts() {
        return emergencyContacts;
    }

    public EmergencyContacts setEmergencyContacts(List<EmergencyContact> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
        return this;
    }
}
