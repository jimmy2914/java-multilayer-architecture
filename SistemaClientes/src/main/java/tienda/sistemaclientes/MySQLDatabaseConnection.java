/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.sistemaclientes;

/**
 *
 * @author kevin
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabaseConnection implements DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/tienda";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private Connection connection;

    // Método estático para obtener la conexión a la base de datos
        // Constructor que inicializa la conexión
    public MySQLDatabaseConnection() {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
        }
    }

    // Sobrescribiendo el método de la interfaz (no es estático)
    @Override
    public Connection getConnection() {
    if (connection == null) {
        System.out.println("La conexión es nula, no se pudo conectar a la base de datos.");
    } else {
        System.out.println("\nConexion establecida...\n");
    }
        return this.connection;
    }
}

