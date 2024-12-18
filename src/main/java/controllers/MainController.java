package controllers;

import dao.factories.DAOFactory;
import views.DataSourceSelection;

import java.util.HashMap;

import static java.lang.System.exit;
import static menu.MenuHandler.displayEntitySelection;

public class MainController {
    //Factories
    private DAOFactory currentDataSourceFactory;

    //Home views
    private final DataSourceSelection menuView = new DataSourceSelection();
    public MainController() {}

    public void start(){
        //Display main menu for selecting data source
        boolean dbSelected = displayDatabaseSelection();

        if (!dbSelected) {
            start();
        }

        displayEntitySelection(currentDataSourceFactory);

    }

    public boolean displayDatabaseSelection(){
        menuView.display();
        HashMap<String,String> dataSourceOption = menuView.getInputs();
        int val = Integer.parseInt(dataSourceOption.get("menuOption"));

        switch(val){
            case 1,2,3,4,5 -> currentDataSourceFactory = DataSourceController.getDataSourceFactory(val);
            case 6 -> exit(0);
        }

        System.out.println("Current database has been set to option " + val + ".");
        return true;
    }

}
