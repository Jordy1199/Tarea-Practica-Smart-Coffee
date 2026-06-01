public class Cliente extends Persona {
    private String codigoCliente;
    private String tipoMembresia;

    public Cliente() {}

    public Cliente(String nombre, String correo, int edad, String codigoCliente, String tipoMembresia) {
        super(nombre, correo, edad);
        this.codigoCliente = codigoCliente;
        setTipoMembresia(tipoMembresia);
    }

    public String getCodigoCliente() { return codigoCliente; }
    public void setCodigoCliente(String codigoCliente) { this.codigoCliente = codigoCliente; }

    public String getTipoMembresia() { return tipoMembresia; }
    public void setTipoMembresia(String tipoMembresia) {
        if (!tipoMembresia.equals("Gold") && !tipoMembresia.equals("Silver") && !tipoMembresia.equals("Premium")) {
            throw new IllegalArgumentException("Membresía inválida. Debe ser Gold, Silver o Premium.");
        }
        this.tipoMembresia = tipoMembresia;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cliente)) return false;
        Cliente otro = (Cliente) obj;
        return this.codigoCliente.equals(otro.codigoCliente);
    }

    @Override
    public int hashCode() {
        return codigoCliente.hashCode();
    }

    @Override
    public String toString() {
        return super.toString() + " | Código: " + codigoCliente + " | Membresía: " + tipoMembresia;
    }
}