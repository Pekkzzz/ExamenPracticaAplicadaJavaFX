package co.edu.poli.examen2_diegogaitan.examensoto.MODELO;

public class SeguroVida extends Seguro {
    private String beneficiario;

    public SeguroVida(String numero, String fechaExpedicion, boolean estado, Asegurado asegurado, String beneficiario) {
        super(numero, fechaExpedicion, estado, asegurado);
        this.beneficiario = beneficiario;
    }

    public String getBeneficiario() {
        return beneficiario;
    }
    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    @Override
    public String toString() {
        return "SeguroVida{" + super.toString() + ", beneficiario='" + beneficiario + "'}";
    }
}