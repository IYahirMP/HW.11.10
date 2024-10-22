package controllers;

import dao.factories.DAOFactory;
import dao.factories.JAXBDAOFactory;
import dao.factories.MySQLDAOFactory;
import dao.factories.StAXDAOFactory;
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
            case 2 -> configureStaxFactory();
            case 3 -> configureJAXBFactory();
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

    private static StAXDAOFactory configureStaxFactory(){
        StAXDAOFactory.filepath = getXMLFileConfiguration();
        return new StAXDAOFactory();
    }

    private static JAXBDAOFactory configureJAXBFactory(){
        JAXBDAOFactory.filepath = getXMLFileConfiguration();
        return new JAXBDAOFactory();
    }

    private static String getXMLFileConfiguration(){
        XMLFileConfiguration config = new XMLFileConfiguration();
        config.display();
        HashMap<String, String> options = config.getInputs();

        return options.get("uri");
    }
}
