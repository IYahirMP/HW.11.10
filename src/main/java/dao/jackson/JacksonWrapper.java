package dao.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dao.factories.JacksonDAOFactory;
import models.xml.Hospital;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class JacksonWrapper {
    protected final static Logger logger = LogManager.getLogger(JacksonWrapper.class);

    public static Hospital getDB(){
        try {
            File file = new File(JacksonDAOFactory.filepath);
            FileInputStream fis = new FileInputStream(file);
            String jsonDB = new String(fis.readAllBytes());

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
            return objectMapper.readValue(jsonDB, Hospital.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static int writeDB(Hospital db){
        String filedir = "target\\database\\json\\hospital.json";

        try{
            logger.trace("Creating ObjectMapper");
            ObjectMapper mapper = new ObjectMapper();
            logger.trace("Registering Mapper modules");
            mapper.registerModule(new JavaTimeModule());
            SimpleFilterProvider filters = new SimpleFilterProvider();
            logger.trace("Adding a filter to serialize all entities.");
            filters.addFilter("HospitalFilter", SimpleBeanPropertyFilter.serializeAll());

            mapper.setFilterProvider(filters);

            ObjectWriter objectWriter = mapper.writerWithDefaultPrettyPrinter();
            String newdb = objectWriter.writeValueAsString(db);

            File output = new File(filedir);
            if (!output.getParentFile().exists()) {
                logger.info("Creating output directory {}", output.getParentFile().getAbsolutePath());
                output.getParentFile().mkdirs();
                logger.info("Creating new file in device storage");
                output.createNewFile();
            }

            FileWriter writer = new FileWriter(output);
            logger.trace("Writing to file {}", output.getName());
            writer.write(newdb);
            logger.trace("Closing output stream");
            writer.close();
            System.out.println("Exported to " + output.getAbsolutePath());

            return 1;
        }catch(JsonProcessingException e){
            logger.error("Error while writing database to string newdb", e);
        }catch(IOException e){
            logger.error("Error while writing database to file Hospital.json", e);
        }

        return 0;
    }
}
