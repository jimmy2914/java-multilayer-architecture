/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tienda.fachada;

import tienda.gestordocumentos.FormatoNoSoportadoException;
import java.util.List;
import tienda.sistemaclientes.ClienteDuplicadoException;
/**
 *
 * @author kevin
 */
public interface IFachada {

    /**
     *
     * @param nombre
     * @param telefono
     * @param correo
     * @throws tienda.sistemaclientes.ClienteDuplicadoException
     */
    void enviarInformacion(String nombre, String telefono, String correo) throws ClienteDuplicadoException;
    void eliminarCliente(int id);
    List<String> obtenerListaClientes();
    void generarDocumento(String formato, String contenido)throws FormatoNoSoportadoException;
}
