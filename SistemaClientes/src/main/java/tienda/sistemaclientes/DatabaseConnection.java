/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tienda.sistemaclientes;

/**
 *
 * @author kevin
 */
import java.sql.Connection;

public interface DatabaseConnection {
    Connection getConnection();
}
