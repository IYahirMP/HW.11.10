package controllers;

import com.mysql.cj.jdbc.MysqlDataSource;
import dao.factories.*;
import dao.interfaces.*;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import views.MySQLConfiguration;
import views.XMLFileConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import static java.lang.System.exit;

public class DataSourceController {

    public DataSourceController() {}

    public static DAOFactory getDataSourceFactory(int type){
        return switch(type){
            case 1 -> configureMySQLFactory();
            case 2 -> configureMyBatisFactory();
            case 3 -> configureStaxFactory();
            case 4 -> configureJAXBFactory();
            case 5 -> configureJacksonFactory();
            default -> null;
        };
    }

    private static MySQLDAOFactory configureMySQLFactory(){
        MySQLConfiguration config = new MySQLConfiguration();
        config.display();
        HashMap<String, String> options = config.getInputs();

        MySQLDAOFactory.url = options.get("url");
        MySQLDAOFactory.database = options.get("database");
        MySQLDAOFactory.user = options.get("username");
        MySQLDAOFactory.password = options.get("password");

        try(Connection con = MySQLDAOFactory.createConnection()){
            System.out.println("Connected to MySQL");
            System.out.println("Testing connection...");
        }catch(SQLException e){
            System.out.println("Connection could not be established");
            System.out.println("Shutting down...");
            exit(0);
        }
        return new MySQLDAOFactory();
    }

    private static MyBatisDAOFactory configureMyBatisFactory(){
        MySQLConfiguration config = new MySQLConfiguration();
        config.display();
        HashMap<String, String> options = config.getInputs();

        MysqlDataSource dataSource = new MysqlDataSource();
        String databaseName = (options.get("database"));
        String user = (options.get("username"));
        String password = (options.get("password"));
        String url = (options.get("url"));
        dataSource.setURL("jdbc:mysql://" + url + databaseName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(AdmissionRecordDAO.class);
        configuration.addMapper(ConsultationDAO.class);
        configuration.addMapper(DoctorDAO.class);
        configuration.addMapper(EmergencyContactDAO.class);
        configuration.addMapper(InvoiceDAO.class);
        configuration.addMapper(InvoiceHasServiceDAO.class);
        configuration.addMapper(MedicineDAO.class);
        configuration.addMapper(PatientDAO.class);
        configuration.addMapper(PrescriptionDAO.class);
        configuration.addMapper(PrescriptionHasMedicineDAO.class);
        configuration.addMapper(ServiceDAO.class);
        configuration.addMapper(TreatmentRecordDAO.class);

        MyBatisDAOFactory.sessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        return new MyBatisDAOFactory();
    }

    private static StAXDAOFactory configureStaxFactory(){
        StAXDAOFactory.filepath = getFileConfiguration();
        return new StAXDAOFactory();
    }

    private static JAXBDAOFactory configureJAXBFactory(){
        JAXBDAOFactory.filepath = getFileConfiguration();
        return new JAXBDAOFactory();
    }

    private static JacksonDAOFactory configureJacksonFactory(){
        JacksonDAOFactory.filepath = getFileConfiguration();
        return new JacksonDAOFactory();
    }

    private static String getFileConfiguration(){
        XMLFileConfiguration config = new XMLFileConfiguration();
        config.display();
        HashMap<String, String> options = config.getInputs();

        return options.get("uri");
    }
}
