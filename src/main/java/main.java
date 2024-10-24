import com.mysql.cj.jdbc.MysqlDataSource;
import controllers.MainController;
import dao.interfaces.PatientDAO;
import models.Patient;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.util.List;


public class main {

    private static MainController mainController = new MainController();

    public static void main(String[] args) throws Exception {
        mainController.start();
    }
}