/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tienda.persistencia;

/**
 *
 * @author jimmy
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import tienda.entidades.Producto;


public class ProductoDAO {
    private static final String URL = "jdbc:h2:~/productosdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    // Método para crear la tabla si no existe
    public void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS Productos ("
                   + "id_producto INT AUTO_INCREMENT PRIMARY KEY, "
                   + "nombre VARCHAR(100) NOT NULL, "
                   + "descripcion TEXT, "
                   + "precio DOUBLE, "
                   + "stock INT)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
            System.out.println("Tabla Productos creada o ya existe.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void insertarProductosDefault() {
        String checkSql = "SELECT COUNT(*) FROM Productos";
        String insertSql = "INSERT INTO Productos (nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             ResultSet rs = checkStmt.executeQuery()) {

            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("✔ La base de datos ya contiene productos. No se insertarán duplicados.");
                return;
            }

            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                String[][] productos = {
                    {"Arroz", "Arroz blanco de grano largo", "5000", "100"},
                    {"Frijoles", "Frijoles rojos en bolsa", "4500", "50"},
                    {"Aceite", "Aceite vegetal 1L", "8000", "80"},
                    {"Leche", "Leche entera en caja 1L", "3500", "200"},
                    {"Panela", "Panela en bloque 500g", "3000", "120"},
                    {"Azúcar", "Azúcar refinada 1kg", "4000", "60"},
                    {"Sal", "Sal marina 500g", "2000", "70"},
                    {"Harina", "Harina de trigo 1kg", "4500", "90"}
                };

                for (String[] producto : productos) {
                    insertStmt.setString(1, producto[0]);
                    insertStmt.setString(2, producto[1]);
                    insertStmt.setDouble(3, Double.parseDouble(producto[2]));
                    insertStmt.setInt(4, Integer.parseInt(producto[3]));
                    insertStmt.executeUpdate();
                }

                System.out.println("✔ Productos por defecto insertados correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Método para insertar un producto
    public void guardarProducto(Producto producto) {
        String sql = "INSERT INTO Productos (nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setInt(4, producto.getStock());

            stmt.executeUpdate();
            System.out.println("Producto guardado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener todos los productos
    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM Productos";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto.Builder()
                        .setId(rs.getInt("id_producto"))
                        .setNombre(rs.getString("nombre"))
                        .setDescripcion(rs.getString("descripcion"))
                        .setPrecio(rs.getDouble("precio"))
                        .setStock(rs.getInt("stock"))
                        .build();
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos; 
    }
    public void eliminarProducto(int id) {
    String sql = "DELETE FROM productos WHERE id_Producto = ?";
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        System.out.println("Producto eliminado correctamente.");
    } catch (SQLException e) {
        // En una aplicación real, aquí se manejaría el error de forma más robusta
        e.printStackTrace();
    }
}
}
