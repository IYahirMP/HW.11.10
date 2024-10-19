package xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

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

            // Marshal the object to the specified file
            jaxbMarshaller.marshal((clazz.cast(object)), new File(filepath));
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
