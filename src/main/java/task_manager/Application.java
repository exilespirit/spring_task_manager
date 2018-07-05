package task_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import task_manager.model.Task;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.*;

@EnableScheduling
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=ShallNotPass1");
            Statement statement = con.createStatement();
            int myResult = statement.executeUpdate("CREATE DATABASE IF NOT EXISTS TaskManager");
            System.out.println(myResult);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        SpringApplication.run(Application.class, args);
    }
}