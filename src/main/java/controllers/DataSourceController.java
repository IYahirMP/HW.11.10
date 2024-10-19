package controllers;

import dao.factories.DAOFactory;
import dao.factories.MySQLDAOFactory;
import dao.factories.STAXDAOFactory;
import views.MySQLConfiguration;
import views.StaxConfiguration;

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

    private static MySQLDAOFactory configureStaxFactory(){
        StaxConfiguration config = new StaxConfiguration();
        config.display();
        HashMap<String, String> options = config.getInputs();

        STAXDAOFactory.filepath = options.get("uri");

        return new MySQLDAOFactory();
    }
}
