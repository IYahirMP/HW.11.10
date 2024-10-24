package dao.stax;

import dao.interfaces.AdmissionRecordDAO;
import dao.factories.StAXDAOFactory;
import models.AdmissionRecord;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StAXAdmissionRecordDAO implements AdmissionRecordDAO {
    private final String tableName = "AdmissionRecord";
    private final String xsdRoot = "src/main/resources/xsd/";
    private final String xsdFile = "AdmissionRecord.xsd";


    @Override
    public int insert(AdmissionRecord obj) {
        throw new UnsupportedOperationException("STAXAdmissionRecordDAO does not support insert operations.");
    }

    @Override
    public int update(int id, AdmissionRecord obj) {
        throw new UnsupportedOperationException("STAXAdmissionRecordDAO does not support update operations.");
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("STAXAdmissionRecordDAO does not support delete operations.");
    }

    @Override
    public Optional<AdmissionRecord> select(int id) {
        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        AdmissionRecord admissionRecord = parseAdmissionRecord(reader);
                        if (admissionRecord != null && admissionRecord.getAdmissionId() == id) {
                            return Optional.of(admissionRecord);
                        }
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<AdmissionRecord> selectAll() {

        try (FileInputStream fis = new FileInputStream(StAXDAOFactory.filepath)) {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fis);
            List<AdmissionRecord> admissionRecords = new ArrayList<>();

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    if (tableName.equals(elementName)) {
                        AdmissionRecord admissionRecord = parseAdmissionRecord(reader);
                        if (admissionRecord != null) {
                            admissionRecords.add(admissionRecord);
                        }
                    }
                }
            }

            return admissionRecords;
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

        return List.of();
    }

    public void validate(){

    }

    private AdmissionRecord parseAdmissionRecord(XMLStreamReader reader) throws XMLStreamException {
        AdmissionRecord admissionRecord = new AdmissionRecord();

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if ("admissionId".equals(elementName)) {
                        admissionRecord.setAdmissionId(Integer.parseInt(reader.getElementText()));
                    } else if ("patientId".equals(elementName)) {
                        admissionRecord.setPatientId(Integer.parseInt(reader.getElementText()));
                    } else if ("consultationId".equals(elementName)) {
                        admissionRecord.setConsultationId(Integer.parseInt(reader.getElementText()));
                    } else if ("admissionDate".equals(elementName)) {
                        admissionRecord.setAdmissionDate(LocalDate.parse(reader.getElementText()));
                    } else if ("dischargeDate".equals(elementName)) {
                        admissionRecord.setDischargeDate(LocalDate.parse(reader.getElementText()));
                    } else if ("roomNumber".equals(elementName)) {
                        admissionRecord.setRoomNumber(Integer.parseInt(reader.getElementText()));
                    } else if ("bedNumber".equals(elementName)) {
                        admissionRecord.setBedNumber(Integer.parseInt(reader.getElementText()));
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    String endElementName = reader.getLocalName();
                    if (tableName.equals(endElementName)) {
                        return admissionRecord;
                    }
                    break;
            }
        }

        return null; // Return null if the element is not found
    }
}
