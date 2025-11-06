/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.fachada;

import tienda.gestordocumentos.FormatoNoSoportadoException;
import tienda.sistemaclientes.SistemaClientes;
import tienda.sistemaclientes.ClienteDuplicadoException;
import java.sql.Connection;
import tienda.sistemaclientes.MySQLDatabaseConnection;
import java.util.List;
import tienda.gestordocumentos.GestorDocumentos;

/**
 *
 * @author kevin
 */
public class ImplFachada implements IFachada {
        private final SistemaClientes sistemaClientes;
        private final GestorDocumentos gestorDocumentos;
    public ImplFachada() {
        MySQLDatabaseConnection dbConn = new MySQLDatabaseConnection();
        Connection connection = dbConn.getConnection();
        this.sistemaClientes = new SistemaClientes(connection);
        this.gestorDocumentos = new GestorDocumentos();
    }
    @Override
    public void enviarInformacion(String nombre, String telefono, String email) throws ClienteDuplicadoException {
        sistemaClientes.agregarCliente(nombre, telefono, email);
    }
    @Override
    public void eliminarCliente(int id) {
        sistemaClientes.eliminarCliente(id);
}
    @Override
    public List<String> obtenerListaClientes() {
    // Retornar la lista de clientes
        return sistemaClientes.obtenerClientes();
    }
    @Override
    public void generarDocumento(String formato,String contenido) throws FormatoNoSoportadoException {
        gestorDocumentos.crearDocumento(formato, contenido);
    }
    
}
   