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
import java.util.List;

public class Main {
    /**public static void main(String[] args) {
        // Crear la implementación de la conexión a MySQL
        // Ajusta host, dbName, user y password a tu configuración

        // Obtener la conexión
        Connection connection = dbConnection.getConnection();
        if (connection == null) {
            System.err.println("No se pudo establecer conexión con la BD MySQL.");
            return;
        }

        // Crear el componente SistemaClientes
        SistemaClientes sistemaClientes = new SistemaClientes(connection);

        // Insertar un nuevo cliente
        sistemaClientes.agregarCliente(
                get,
                "Juan Pérez",
                "123456789",
                "juan.perez@example.com"
        );

        // Obtener y mostrar la lista de clientes
        List<String> lista = sistemaClientes.obtenerClientes();
        lista.forEach(System.out::println);

        // Cerrar la conexión al finalizar
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    * **/
}

