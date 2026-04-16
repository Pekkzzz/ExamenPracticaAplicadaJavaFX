package co.edu.poli.examen2_diegogaitan.examensoto.SERVICIOS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class conexionBD {



    private static final Dotenv dotenv = Dotenv.load();

    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {


            // 🔍 PRUEBA TEMPORAL
            System.out.println("URL: " + URL);
            System.out.println("USER: " + USER);
            System.out.println("PASSWORD: " + PASSWORD);

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;


    }
}

