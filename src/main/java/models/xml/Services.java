package models.xml;

import models.Service;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Services")
public class Services {

    private List<Service> services;

    @XmlElement(name = "Service")
    public List<Service> getServices() {
        return services;
    }

    public Services setServices(List<Service> services) {
        this.services = services;
        return this;
    }
}
