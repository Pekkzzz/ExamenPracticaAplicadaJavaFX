package co.edu.poli.examen2_diegogaitan.examensoto.MODELO;

public class SeguroVehiculo extends Seguro {
    private String marca;

    public SeguroVehiculo(String numero, String fechaExpedicion, boolean estado, Asegurado asegurado, String marca) {
        super(numero, fechaExpedicion, estado, asegurado);
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "SeguroVehiculo{" + super.toString() + ", marca='" + marca + "'}";
    }
}