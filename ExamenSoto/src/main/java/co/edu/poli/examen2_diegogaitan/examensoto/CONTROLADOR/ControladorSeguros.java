package co.edu.poli.examen2_diegogaitan.examensoto.CONTROLADOR;

import co.edu.poli.examen2_diegogaitan.examensoto.MODELO.Asegurado;
import co.edu.poli.examen2_diegogaitan.examensoto.MODELO.SeguroVida;
import co.edu.poli.examen2_diegogaitan.examensoto.MODELO.SeguroVehiculo;
import co.edu.poli.examen2_diegogaitan.examensoto.SERVICIOS.seguroDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ControladorSeguros {

    private seguroDAO dao = new seguroDAO();

    @FXML private TextField txtNumero;
    @FXML private DatePicker dateExpedicion;
    @FXML private ComboBox<Asegurado> cmbAsegurado;
    @FXML private RadioButton rbVida;
    @FXML private TextField txtDatoEspecifico;
    @FXML private CheckBox chkEstado;
    @FXML private TextField txtBusqueda;
    @FXML private TextArea txtAreaDetalles;

    @FXML
    public void initialize() {
        // Carga los asegurados directamente desde la BD
        cmbAsegurado.setItems(FXCollections.observableArrayList(dao.obtenerAsegurados()));
        cmbAsegurado.setCellFactory(cb -> new ListCell<>() {
            @Override
            protected void updateItem(Asegurado a, boolean empty) {
                super.updateItem(a, empty);
                setText(empty || a == null ? null : a.getNombre());
            }
        });
        cmbAsegurado.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Asegurado a, boolean empty) {
                super.updateItem(a, empty);
                setText(empty || a == null ? null : a.getNombre());
            }
        });
    }

    @FXML
    private void handleGuardar() {
        String numero = txtNumero.getText();
        String fecha = dateExpedicion.getValue() != null ? dateExpedicion.getValue().toString() : "";
        boolean estado = chkEstado.isSelected();
        Asegurado asegurado = cmbAsegurado.getValue();
        String datoEspecifico = txtDatoEspecifico.getText();

        if (numero.isEmpty() || fecha.isEmpty() || asegurado == null || datoEspecifico.isEmpty()) {
            mostrarAlerta("Error", "Por favor complete todos los campos.");
            return;
        }

        boolean guardado;
        if (rbVida.isSelected()) {
            guardado = dao.guardarSeguroVida(new SeguroVida(numero, fecha, estado, asegurado, datoEspecifico));
        } else {
            guardado = dao.guardarSeguroVehiculo(new SeguroVehiculo(numero, fecha, estado, asegurado, datoEspecifico));
        }

        mostrarAlerta(guardado ? "Éxito" : "Error", guardado ? "Seguro guardado." : "Error al guardar.");
        if (guardado) limpiarFormulario();
    }

    @FXML
    private void handleConsultar() {
        txtAreaDetalles.setText(dao.consultarPorNumero(txtBusqueda.getText().trim()));
    }

    @FXML
    private void handleActivar() {
        boolean ok = dao.cambiarEstado(txtBusqueda.getText().trim(), true);
        txtAreaDetalles.setText(ok ? "Seguro activado." : "No encontrado.");
    }

    @FXML
    private void handleCancelar() {
        boolean ok = dao.cambiarEstado(txtBusqueda.getText().trim(), false);
        txtAreaDetalles.setText(ok ? "Seguro cancelado." : "No encontrado.");
    }

    private void limpiarFormulario() {
        txtNumero.clear();
        dateExpedicion.setValue(null);
        txtDatoEspecifico.clear();
        chkEstado.setSelected(true);
        cmbAsegurado.setValue(null);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}