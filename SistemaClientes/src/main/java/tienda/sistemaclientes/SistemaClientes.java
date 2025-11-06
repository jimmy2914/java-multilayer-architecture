/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tienda.sistemaclientes;

/**
 *
 * @author kevin
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SistemaClientes {

    private final Connection connection;

    // Inyección de la dependencia de conexión
    public SistemaClientes(Connection connection) {
        this.connection = connection;
    }

    public void agregarCliente(String nombre, String telefono, String email) throws ClienteDuplicadoException{
        String sql = "INSERT INTO clientes (nombre, telefono, email) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, telefono);
            ps.setString(3, email);
            ps.executeUpdate();
            System.out.println("✔ Cliente agregado con éxito: " + nombre);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // 1062 es el código de MySQL para "Duplicate entry"
                throw new ClienteDuplicadoException("El correo electrónico '" + email + "' ya está registrado.");
            } else {
                // --- CAMBIO: Se envuelve cualquier otro error de SQL en una RuntimeException ---
                // Esto asegura que el error no sea ignorado y llegue a la vista.
                throw new RuntimeException("Error inesperado en la base de datos: " + e.getMessage(), e);
            }
        }
    }

    // Obtener la lista de todos los clientes
    public List<String> obtenerClientes() {
        List<String> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");

                String info = String.format("ID: %d, Nombre: %s, Tel: %s, Email: %s",
                        id, nombre, telefono, email);
                clientes.add(info);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener la lista de clientes.");
            e.printStackTrace();
        }
        return clientes;
    }
    public void eliminarCliente(int id) {
    String sql = "DELETE FROM clientes WHERE id = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setInt(1, id);
        int affectedRows = pstmt.executeUpdate();
        if (affectedRows == 0) {
            // Opcional: Manejar el caso donde no se encuentra el cliente
            System.out.println("No se encontró ningún cliente con el ID: " + id);
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error al eliminar el cliente de la base de datos", e);
    }
}
}

