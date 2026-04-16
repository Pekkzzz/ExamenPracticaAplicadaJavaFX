import org.junit.jupiter.api.Test;
import java.sql.Connection;
import co.edu.poli.examen2_diegogaitan.examensoto.SERVICIOS.conexionBD;

import static org.junit.jupiter.api.Assertions.*;

class ConexionBDTest {

    @Test
    void testConexion() throws Exception {
        Connection con = conexionBD.getConnection();

        assertNotNull(con, "La conexión no debería ser null");
    }
}