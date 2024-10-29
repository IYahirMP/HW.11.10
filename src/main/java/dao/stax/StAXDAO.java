package dao.stax;

import dao.factories.StAXDAOFactory;
import models.*;
import models.xml.Hospital;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.stream.*;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static java.lang.System.exit;

public abstract class StAXDAO {
    protected final static Logger logger = LogManager.getLogger(StAXDAO.class);

    public static void validateXML(String xsd, String url) throws SAXException, XMLStreamException, IOException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        // Parse the XSD file into a Schema object
        StreamSource schemaFile = new StreamSource(xsd);
        Schema schema = factory.newSchema(schemaFile);

        // Create an XMLInputFactory to parse the XML document
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        StreamSource xmlFile = new StreamSource(url);
        XMLStreamReader reader = inputFactory.createXMLStreamReader(xmlFile);

        // Create a Validator object from the Schema object
        Validator validator = schema.newValidator();

        // Validate the XML document using the Validator
        validator.validate(new StAXSource(reader));
    }

    protected void validateDatabase(){
        try{
            StAXDAO.validateXML("src/main/resources/xsd/Hospital.xsd", StAXDAOFactory.filepath);
        }catch(Exception e){
            logger.error("An error occurred while validating the XML database.", e);
            System.out.println("An error occurred while validating the XML database.");
            System.out.println("Shutting down...");
            exit(0);
        }
    }

    protected Hospital getDB(){
        validateDatabase();
        Hospital hospital = new Hospital();

        hospital.setAdmissionRecords(new StAXAdmissionRecordDAO().selectAll());
        hospital.setConsultations(new StAXConsultationDAO().selectAll());
        hospital.setDoctors(new StAXDoctorDAO().selectAll());
        hospital.setEmergencyContacts(new StAXEmergencyContactDAO().selectAll());
        hospital.setInvoices(new StAXInvoiceDAO().selectAll());
        hospital.setInvoiceHasServices(new StAXInvoiceHasServiceDAO().selectAll());
        hospital.setMedicines(new StAXMedicineDAO().selectAll());
        hospital.setPrescriptions(new StAXPrescriptionDAO().selectAll());
        hospital.setPrescriptionHasMedicines(new StAXPrescriptionHasMedicineDAO().selectAll());
        hospital.setPatients(new StAXPatientDAO().selectAll());
        hospital.setTreatmentRecords(new StAXTreatmentRecordDAO().selectAll());
        hospital.setServices(new StAXServiceDAO().selectAll());

        return hospital;
    }

    protected void writeDB(Hospital hospital){
        XMLOutputFactory outputFactory = XMLOutputFactory.newFactory();

        try(FileOutputStream outputFile = new FileOutputStream("target/database/xml/Hospital.xml")){
            XMLStreamWriter writer = outputFactory.createXMLStreamWriter(outputFile);
            //Beginning wrapper for database
            writer.writeStartDocument();
            writer.writeStartElement("hospital");

            //Database blocks start
            writePatients(writer, hospital);
            writeEmergencyContacts(writer, hospital);
            writeDoctors(writer, hospital);
            writeConsultations(writer, hospital);
            writeAdmissionRecords(writer, hospital);
            writeTreatmentRecords(writer, hospital);
            writeInvoices(writer, hospital);
            writeInvoiceHasServices(writer, hospital);
            writeServices(writer, hospital);
            writePrescriptions(writer, hospital);
            writePrescriptionHasMedicines(writer, hospital);
            writeMedicines(writer, hospital);

            //Ends hospital block
            writer.writeEndElement();
            writer.writeEndDocument();

        } catch(FileNotFoundException e){
            logger.error("Output file couldn't be found", e);
        } catch(IOException e){
            logger.error("An error occurred while writing XML to the file", e);
        } catch(XMLStreamException e){
            logger.error("An error occurred while creating XMLStreamWriter for output file.", e);
        }
    }

    private void writeMedicines(XMLStreamWriter writer, Hospital hospital) throws XMLStreamException {
        List<Medicine> medicines = hospital.getMedicines();
        if (medicines != null && !medicines.isEmpty()) {
            StAXMedicineDAO dao = new StAXMedicineDAO();
            writer.writeStartElement("Medicines");
            for (Medicine medicine : medicines) {
                dao.writeMedicine(writer, medicine);
            }
            writer.writeEndElement();
        }
    }


    private void writePrescriptionHasMedicines(XMLStreamWriter writer, Hospital hospital) throws XMLStreamException {
        List<PrescriptionHasMedicine> prescriptionHasMedicines = hospital.getPrescriptionHasMedicines();
        if (prescriptionHasMedicines != null && !prescriptionHasMedicines.isEmpty()) {
            writer.writeStartElement("PrescriptionHasMedicines");
            StAXPrescriptionHasMedicineDAO dao = new StAXPrescriptionHasMedicineDAO();
            for (PrescriptionHasMedicine phm : prescriptionHasMedicines) {
                dao.writePrescriptionHasMedicine(writer, phm);
            }
            writer.writeEndElement();
        }
    }


    private void writePrescriptions(XMLStreamWriter writer, Hospital hospital) throws XMLStreamException {
        List<Prescription> prescriptions = hospital.getPrescriptions();
        if (prescriptions != null && !prescriptions.isEmpty()) {
            writer.writeStartElement("Prescriptions");
            StAXPrescriptionDAO dao = new StAXPrescriptionDAO();
            for (Prescription prescription : prescriptions) {
                dao.writePrescription(writer, prescription);
            }
            writer.writeEndElement();
        }
    }


    private void writeServices(XMLStreamWriter writer, Hospital hospital) throws XMLStreamException {
        List<Service> services = hospital.getServices();
        if (services != null && !services.isEmpty()) {
            writer.writeStartElement("Services");
            StAXServiceDAO dao = new StAXServiceDAO();
            for (Service service : services) {
                dao.writeService(writer, service);
            }
            writer.writeEndElement();
        }
    }


    private void writeInvoiceHasServices(XMLStreamWriter writer, Hospital hospital) throws XMLStreamException {
        List<InvoiceHasService> invoiceHasServices = hospital.getInvoiceHasServices();
        if (invoiceHasServices != null && !invoiceHasServices.isEmpty()) {
            writer.writeStartElement("InvoiceHasServices");
            StAXInvoiceHasServiceDAO dao = new StAXInvoiceHasServiceDAO();
            for (InvoiceHasService ighs : invoiceHasServices) {
                dao.writeInvoiceHasService(writer, ighs);
            }
            writer.writeEndElement();
        }
    }


    private void writeInvoices(XMLStreamWriter writer, Hospital hospital) throws XMLStreamException {
        List<Invoice> invoices = hospital.getInvoices();
        if (invoices != null && !invoices.isEmpty()) {
            writer.writeStartElement("Invoice");
            StAXInvoiceDAO dao = new StAXInvoiceDAO();
            for (Invoice invoice : invoices) {
                dao.writeInvoice(writer, invoice);
            }
            writer.writeEndElement();
        }
    }


    private void writeTreatmentRecords(XMLStreamWriter writer, Hospital hospital) throws XMLStreamException {
        List<TreatmentRecord> treatmentRecords = hospital.getTreatmentRecords();
        if (treatmentRecords != null && !treatmentRecords.isEmpty()) {
            writer.writeStartElement("TreatmentRecord");
            StAXTreatmentRecordDAO dao = new StAXTreatmentRecordDAO();
            for (TreatmentRecord record : treatmentRecords) {
                dao.writeTreatmentRecord(writer, record);
            }
            writer.writeEndElement();
        }
    }


    private void writeAdmissionRecords(XMLStreamWriter writer, Hospital hospital) throws XMLStreamException {
        List<AdmissionRecord> admissionRecords = hospital.getAdmissionRecords();
        if (admissionRecords != null && !admissionRecords.isEmpty()) {
            writer.writeStartElement("AdmissionRecord");
            StAXAdmissionRecordDAO dao = new StAXAdmissionRecordDAO();
            for (AdmissionRecord record : admissionRecords) {
                dao.writeAdmissionRecord(writer, record);
            }
            writer.writeEndElement();
        }
    }


    private void writeConsultations(XMLStreamWriter writer, Hospital hospital) throws XMLStreamException {
        List<Consultation> consultations = hospital.getConsultations();
        if (consultations != null && !consultations.isEmpty()) {
            writer.writeStartElement("Consultation");
            StAXConsultationDAO dao = new StAXConsultationDAO();
            for (Consultation consultation : consultations) {
                dao.writeConsultation(writer, consultation);
            }
            writer.writeEndElement();
        }
    }


    private void writeDoctors(XMLStreamWriter writer, Hospital hospital) throws XMLStreamException {
        List<Doctor> doctors = hospital.getDoctors();
        writer.writeStartElement("Doctor");

        StAXDoctorDAO dao = new StAXDoctorDAO();
        for (Doctor doctor : doctors) {
            dao.writeDoctor(writer, doctor);
        }
        writer.writeEndElement();
    }

    private void writeEmergencyContacts(XMLStreamWriter writer, Hospital hospital) throws XMLStreamException {
        List<EmergencyContact> emergencyContacts = hospital.getEmergencyContacts();
        writer.writeStartElement("EmergencyContact");

        StAXEmergencyContactDAO dao = new StAXEmergencyContactDAO();
        for (EmergencyContact emergencyContact : emergencyContacts) {
            dao.writeEmergencyContact(writer, emergencyContact);
        }
        writer.writeEndElement();
    }

    private void writePatients(XMLStreamWriter writer, Hospital hospital) throws XMLStreamException {
        List<Patient> patients = hospital.getPatients();
        writer.writeStartElement("Patients");

        StAXPatientDAO dao = new StAXPatientDAO();
        for (Patient patient : patients) {
            dao.writePatient(writer, patient);
        }
        writer.writeEndElement();
    }
}
