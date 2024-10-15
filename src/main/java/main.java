import controllers.MainController;
import controllers.PatientController;
import dao.PatientDAO;
import dao.factories.MySQLDAOFactory;
import models.Patient;

import java.util.List;
import java.util.Optional;

public class main {

    private static MainController mainController = new MainController();

    public static void main(String[] args) {
        mainController.start();
    }
}