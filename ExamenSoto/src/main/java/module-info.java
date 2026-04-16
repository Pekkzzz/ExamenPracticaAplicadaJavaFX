module co.edu.poli.examen2_diegogaitan.examensoto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.junit.jupiter.api;
    requires mysql.connector.j;
    requires io.github.cdimascio.dotenv.java;

// Abre los modelos para que JUnit y JavaFX puedan usar Reflexión
    opens co.edu.poli.examen2_diegogaitan.examensoto.MODELO;

    // Abre controladores y vistas para JavaFX
    opens co.edu.poli.examen2_diegogaitan.examensoto.CONTROLADOR to javafx.fxml;
    opens co.edu.poli.examen2_diegogaitan.examensoto.VISTA to javafx.fxml;
    exports co.edu.poli.examen2_diegogaitan.examensoto;
    exports co.edu.poli.examen2_diegogaitan.examensoto.MODELO;
    exports co.edu.poli.examen2_diegogaitan.examensoto.CONTROLADOR;
    exports co.edu.poli.examen2_diegogaitan.examensoto.VISTA;
}