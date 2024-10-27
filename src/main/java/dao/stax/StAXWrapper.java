package dao.stax;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;

public class StAXWrapper {
    protected final static Logger logger = LogManager.getLogger(StAXWrapper.class);

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
}
