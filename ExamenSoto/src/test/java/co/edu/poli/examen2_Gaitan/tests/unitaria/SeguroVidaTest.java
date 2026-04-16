package co.edu.poli.examen2_Gaitan.tests.unitaria;

import co.edu.poli.examen2_diegogaitan.examensoto.MODELO.Asegurado;
import co.edu.poli.examen2_diegogaitan.examensoto.MODELO.SeguroVida;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class SeguroVidaTest {

    @Test
    @DisplayName("Debería validar la creación correcta y el cambio de estado de un Seguro de Vida")
    void testLogicaSeguroVida() {
        // 1. Preparación (Arrange)
        // Necesitamos un objeto Asegurado porque la clase Seguro lo requiere en su constructor
        Asegurado asegurado = new Asegurado(101, "Diego Gaitan");

        // Creamos la instancia de SeguroVida
        SeguroVida seguroVida = new SeguroVida(
                "POL-2026-001",
                "2026-04-16",
                true,
                asegurado,
                "Andrea Gaitan"
        );

        // 2. Ejecución y Verificación (Act & Assert)
        // Verificamos que los atributos específicos se asignaron correctamente
        assertEquals("Andrea Gaitan", seguroVida.getBeneficiario(), "El beneficiario debe coincidir");
        assertEquals("POL-2026-001", seguroVida.getNumero(), "El número de póliza debe coincidir");

        // Probamos el método cancelar() heredado de Seguro
        String mensajeCancelacion = seguroVida.cancelar();

        // Verificamos que el estado cambió a false
        assertFalse(seguroVida.isEstado(), "El estado del seguro debería ser false (inactivo) tras cancelar");

        // Verificamos que el mensaje de retorno es el esperado
        assertTrue(mensajeCancelacion.contains("CANCELADO"), "El mensaje de retorno debe confirmar la cancelación");

        // Probamos el método activar()
        seguroVida.activar();
        assertTrue(seguroVida.isEstado(), "El estado del seguro debería ser true (activo) tras activar");
    }
}