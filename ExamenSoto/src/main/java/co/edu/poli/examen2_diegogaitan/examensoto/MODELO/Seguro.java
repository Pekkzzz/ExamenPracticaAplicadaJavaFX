package co.edu.poli.examen2_diegogaitan.examensoto.MODELO;

public abstract class Seguro {
    private String numero;
    private String fechaExpedicion;
    private boolean estado;
    private Asegurado asegurado;

    public Seguro(String numero, String fechaExpedicion, boolean estado, Asegurado asegurado) {
        this.numero = numero;
        this.fechaExpedicion = fechaExpedicion;
        this.estado = estado;
        this.asegurado = asegurado;
    }

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFechaExpedicion() {
        return fechaExpedicion;
    }
    public void setFechaExpedicion(String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Asegurado getAsegurado() {
        return asegurado;
    }
    public void setAsegurado(Asegurado asegurado) {
        this.asegurado = asegurado;
    }

    public String cancelar() {
        this.estado = false;
        return "Seguro " + numero + " CANCELADO.";
    }

    public String activar() {
        this.estado = true;
        return "Seguro " + numero + " ACTIVADO.";
    }

    @Override
    public String toString() {
        return "numero=" + numero + ", fechaExpedicion=" + fechaExpedicion
                + ", estado=" + estado + ", asegurado=" + asegurado;
    }
}