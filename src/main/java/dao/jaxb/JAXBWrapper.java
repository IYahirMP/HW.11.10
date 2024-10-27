package dao.jaxb;

import dao.factories.JAXBDAOFactory;
import models.xml.Hospital;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

import static dao.factories.JAXBDAOFactory.schemaRoot;
import static java.lang.System.exit;

public class JAXBWrapper {
    protected static Logger logger = LogManager.getLogger(JAXBWrapper.class);

    /**
     * Reads the database file provided. It will search for the corresponding schema
     * in schemaRoot directory and validate it when unmarshalling.
     *
     * @return Database object of type Hospital
     *
     */
    public static Hospital getDB(){
        logger.traceEntry();
        try {
            logger.debug("Creating JAXBContext");
            JAXBContext jaxbcontext = JAXBContext.newInstance(Hospital.class);
            logger.debug("Creating unmarshaller");
            Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();

            logger.debug("Creating File object for XML source");
            File file = new File(JAXBDAOFactory.filepath);
            logger.debug("Creating FIle object for XML Schema");
            File xsd = new File(schemaRoot + file.getName().replace(".xml", ".xsd"));

            logger.debug("Creating Schema object.");
            Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(xsd);

            logger.debug("Setting schema to unmarshaller");
            unmarshaller.setSchema(schema);

            return logger.traceExit((Hospital) unmarshaller.unmarshal(file));
        } catch (JAXBException e) {
            JAXBWrapper.logger.info("Could not unmarshall correctly.", e);
            exit(0);
        } catch(SAXException e) {
            JAXBWrapper.logger.error("Parser couldn't process files correctly.", e);
            exit(0);
        } catch(Exception e){
            JAXBWrapper.logger.error("Something went wrong while getting the Hospital file", e);
            exit(0);
        }

        return null;
    }

    public static int writeDB(Hospital hospital){
        try {
            logger.debug("Creating JAXBContext");
            JAXBContext jaxbcontext = JAXBContext.newInstance(Hospital.class);
            logger.debug("Creating marshaller");
            Marshaller marshaller = jaxbcontext.createMarshaller();
            logger.trace("Setting up formatted output");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            logger.debug("Creating File object for XML output");
            File file = new File(JAXBDAOFactory.filepath);
            logger.debug("Attempting to marshall XML object to file");
            marshaller.marshal(hospital,file);
            return logger.traceExit(1);
        } catch (JAXBException e) {
            JAXBWrapper.logger.error("Could not marshall hospital correctly.", e);
            exit(0);
        }

        return logger.traceExit(0);
    }
}
