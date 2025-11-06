/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.persistencia.fabrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import tienda.persistencia.Fabrica.FabricaConexion;

public class FabricaConexionH2 implements FabricaConexion {
    private static final String URL = "jdbc:h2:~/productosdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    
    private static FabricaConexionH2 instancia;
    private Connection conexion;

    private FabricaConexionH2() {}

    public static synchronized FabricaConexionH2 getInstancia() {
        if (instancia == null) {
            instancia = new FabricaConexionH2();
        }
        return instancia;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return conexion;
    }
}
