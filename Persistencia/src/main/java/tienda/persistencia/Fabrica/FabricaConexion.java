/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tienda.persistencia.Fabrica;

/**
 *
 * @author jimmy
 */

import java.sql.Connection;
import java.sql.SQLException;

public interface FabricaConexion {
    Connection getConnection() throws SQLException;
}
