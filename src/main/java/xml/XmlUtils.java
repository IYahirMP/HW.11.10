package xml;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlUtils {
    public static void exportXML(String filepath, Object object) {
        try {
            // Get the class of the object passed (either Patient, Patients, or any other JAXB-annotated class)
            Class<?> clazz = object.getClass();

            // Create JAXB context and marshaller based on the class of the object
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // Set formatted output property
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Define file and create dirs
            File file = new File(filepath);
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            // Marshal the object to the specified file
            jaxbMarshaller.marshal((clazz.cast(object)), file);
        } catch (JAXBException e) {
            System.out.println("Error during JAXB marshalling.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }catch(Exception e){
            System.out.println("Error during JAXB serialization.");
        }
    }
}
