public interface CrudPedido {
    void registrarPedido(String correo, double consumo);
    void actualizarPedido(String correo, double nuevoConsumo);
    void eliminarPedido(String correo);
    void promedioConsumo();
    void mejorCliente();
}