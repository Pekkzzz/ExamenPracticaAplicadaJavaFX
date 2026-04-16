module co.edu.poli.examen2_diegogaitan.examensoto {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires org.junit.jupiter.api;
    requires mysql.connector.j;
    requires io.github.cdimascio.dotenv.java;

    opens co.edu.poli.examen2_diegogaitan.examensoto.MODELO;
    opens co.edu.poli.examen2_diegogaitan.examensoto.CONTROLADOR to javafx.fxml;

    // ✅ Ahora javafx.graphics puede instanciar tu clase Application
    opens co.edu.poli.examen2_diegogaitan.examensoto.VISTA to javafx.fxml, javafx.graphics;

    exports co.edu.poli.examen2_diegogaitan.examensoto;
    exports co.edu.poli.examen2_diegogaitan.examensoto.MODELO;
    exports co.edu.poli.examen2_diegogaitan.examensoto.CONTROLADOR;
    exports co.edu.poli.examen2_diegogaitan.examensoto.SERVICIOS;
}