import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cafeteria implements CrudPedido {
    private String nombreCafeteria;
    private HashSet<Cliente> clientes = new HashSet<>();
    private Map<String, Double> mapaPedidos = new HashMap<>();

    public Cafeteria(String nombreCafeteria) {
        this.nombreCafeteria = nombreCafeteria;
    }

    public String getNombreCafeteria() { return nombreCafeteria; }
    public void setNombreCafeteria(String nombreCafeteria) { this.nombreCafeteria = nombreCafeteria; }

    public void registrarCliente(Cliente cliente) {
        if (!clientes.add(cliente)) {
            System.out.println("El cliente ya existe.");
        } else {
            System.out.println("Cliente registrado correctamente.");
        }
    }

    public void mostrarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }

    public Cliente buscarClientePorCorreo(String correo) {
        for (Cliente c : clientes) {
            if (c.getCorreo().equals(correo)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void registrarPedido(String correo, double consumo) {
        if (consumo <= 0) {
            System.out.println("El consumo debe ser mayor a 0.");
            return;
        }
        Cliente cliente = buscarClientePorCorreo(correo);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        mapaPedidos.put(cliente.getCodigoCliente(), consumo);
        System.out.println("Pedido registrado correctamente.");
    }

    @Override
    public void actualizarPedido(String correo, double nuevoConsumo) {
        Cliente cliente = buscarClientePorCorreo(correo);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        if (!mapaPedidos.containsKey(cliente.getCodigoCliente())) {
            System.out.println("El pedido no existe.");
            return;
        }
        mapaPedidos.put(cliente.getCodigoCliente(), nuevoConsumo);
        System.out.println("Pedido actualizado correctamente.");
    }

    @Override
    public void eliminarPedido(String correo) {
        Cliente cliente = buscarClientePorCorreo(correo);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        if (!mapaPedidos.containsKey(cliente.getCodigoCliente())) {
            System.out.println("El pedido no existe.");
            return;
        }
        mapaPedidos.remove(cliente.getCodigoCliente());
        System.out.println("Pedido eliminado correctamente.");
    }

    @Override
    public void promedioConsumo() {
        if (mapaPedidos.isEmpty()) {
            System.out.println("No hay datos disponibles.");
            return;
        }
        double total = 0;
        for (double consumo : mapaPedidos.values()) {
            total += consumo;
        }
        System.out.println("Promedio de consumo: $" + (total / mapaPedidos.size()));
    }

    @Override
    public void mejorCliente() {
        if (mapaPedidos.isEmpty()) {
            System.out.println("No hay datos disponibles.");
            return;
        }
        String mejorCodigo = null;
        double maxConsumo = 0;
        for (Map.Entry<String, Double> entry : mapaPedidos.entrySet()) {
            if (entry.getValue() > maxConsumo) {
                maxConsumo = entry.getValue();
                mejorCodigo = entry.getKey();
            }
        }
        for (Cliente c : clientes) {
            if (c.getCodigoCliente().equals(mejorCodigo)) {
                System.out.println("Mejor cliente: " + c + " | Consumo: $" + maxConsumo);
                return;
            }
        }
    }

    public void mostrarPedidos() {
        if (mapaPedidos.isEmpty()) {
            System.out.println("No hay datos disponibles.");
            return;
        }
        for (Map.Entry<String, Double> entry : mapaPedidos.entrySet()) {
            System.out.println("Código: " + entry.getKey() + " | Consumo: $" + entry.getValue());
        }
    }
}