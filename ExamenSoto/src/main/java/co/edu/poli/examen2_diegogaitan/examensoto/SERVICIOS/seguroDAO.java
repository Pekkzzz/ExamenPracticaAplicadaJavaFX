package co.edu.poli.examen2_diegogaitan.examensoto.SERVICIOS;

import co.edu.poli.examen2_diegogaitan.examensoto.MODELO.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class seguroDAO {

    public List<Asegurado> obtenerAsegurados() {
        List<Asegurado> lista = new ArrayList<>();
        String sql = "SELECT * FROM asegurado";
        try (Connection con = conexionBD.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Asegurado(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean guardarSeguroVida(SeguroVida sv) {
        String sqlSeguro = "INSERT INTO seguro (numero, fecha_expedicion, estado, tipo, asegurado_id) VALUES (?,?,?,?,?)";
        String sqlVida   = "INSERT INTO seguro_vida (seguro_id, beneficiario) VALUES (?,?)";
        try (Connection con = conexionBD.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps1 = con.prepareStatement(sqlSeguro, Statement.RETURN_GENERATED_KEYS)) {
                ps1.setString(1, sv.getNumero());
                ps1.setString(2, sv.getFechaExpedicion());
                ps1.setBoolean(3, sv.isEstado());
                ps1.setString(4, "VIDA");
                ps1.setInt(5, sv.getAsegurado().getId());
                ps1.executeUpdate();
                ResultSet keys = ps1.getGeneratedKeys();
                if (keys.next()) {
                    int seguroId = keys.getInt(1);
                    try (PreparedStatement ps2 = con.prepareStatement(sqlVida)) {
                        ps2.setInt(1, seguroId);
                        ps2.setString(2, sv.getBeneficiario());
                        ps2.executeUpdate();
                    }
                }
            }
            con.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean guardarSeguroVehiculo(SeguroVehiculo sveh) {
        String sqlSeguro   = "INSERT INTO seguro (numero, fecha_expedicion, estado, tipo, asegurado_id) VALUES (?,?,?,?,?)";
        String sqlVehiculo = "INSERT INTO seguro_vehiculo (seguro_id, marca) VALUES (?,?)";
        try (Connection con = conexionBD.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps1 = con.prepareStatement(sqlSeguro, Statement.RETURN_GENERATED_KEYS)) {
                ps1.setString(1, sveh.getNumero());
                ps1.setString(2, sveh.getFechaExpedicion());
                ps1.setBoolean(3, sveh.isEstado());
                ps1.setString(4, "VEHICULO");
                ps1.setInt(5, sveh.getAsegurado().getId());
                ps1.executeUpdate();
                ResultSet keys = ps1.getGeneratedKeys();
                if (keys.next()) {
                    int seguroId = keys.getInt(1);
                    try (PreparedStatement ps2 = con.prepareStatement(sqlVehiculo)) {
                        ps2.setInt(1, seguroId);
                        ps2.setString(2, sveh.getMarca());
                        ps2.executeUpdate();
                    }
                }
            }
            con.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String consultarPorNumero(String numero) {
        String sql = "SELECT s.*, a.nombre, sv.beneficiario, sveh.marca " +
                "FROM seguro s " +
                "JOIN asegurado a ON s.asegurado_id = a.id " +
                "LEFT JOIN seguro_vida sv ON s.id = sv.seguro_id " +
                "LEFT JOIN seguro_vehiculo sveh ON s.id = sveh.seguro_id " +
                "WHERE s.numero = ?";
        try (Connection con = conexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, numero);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String tipo = rs.getString("tipo");
                String resultado = "Número: " + rs.getString("numero") +
                        "\nFecha: " + rs.getString("fecha_expedicion") +
                        "\nEstado: " + (rs.getBoolean("estado") ? "Activo" : "Inactivo") +
                        "\nAsegurado: " + rs.getString("nombre") +
                        "\nTipo: " + tipo;
                if ("VIDA".equals(tipo)) {
                    resultado += "\nBeneficiario: " + rs.getString("beneficiario");
                } else {
                    resultado += "\nMarca: " + rs.getString("marca");
                }
                return resultado;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No encontrado.";
    }

    public boolean cambiarEstado(String numero, boolean nuevoEstado) {
        String sql = "UPDATE seguro SET estado = ? WHERE numero = ?";
        try (Connection con = conexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, nuevoEstado);
            ps.setString(2, numero);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}