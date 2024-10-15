package controllers;

import dao.factories.DAOFactory;
import dao.factories.MySQLDAOFactory;
import dao.factories.STAXDAOFactory;
import views.MySQLConfiguration;

import java.util.HashMap;

public class DataSourceController {

    private MySQLConfiguration mySQlConfiguration;

    public DataSourceController() {}

    public static DAOFactory getDataSourceFactory(int type){
        return switch(type){
            case 1 -> configureMySQLFactory();
            case 2 -> new STAXDAOFactory();
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

        return new MySQLDAOFactory();
    }
}
