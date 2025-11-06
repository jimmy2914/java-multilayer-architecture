package tienda.controlador;


import tienda.fachada.IFachada;
import tienda.sistemaclientes.ClienteDuplicadoException;
import tienda.gestordocumentos.FormatoNoSoportadoException;
import java.util.List;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Estudiante_MCA
 */
public class FachadaControlador {

    private final IFachada fachada;

    // Inyectas la fachada (o la creas internamente)
    public FachadaControlador(IFachada fachada) {
        this.fachada = fachada;
    }
    public void eliminarCliente(int id) {
        fachada.eliminarCliente(id);
    }

    // ------------------- CLIENTES -------------------
    public void registrarCliente(String nombre, String telefono, String email) throws ClienteDuplicadoException {
        fachada.enviarInformacion(nombre, telefono, email);
    }

    public List<String> obtenerClientes() {
        return fachada.obtenerListaClientes();
    }
    
    
    //----------------- Documentos --------------
    public void generarDoc(String formato,String Contenido) throws FormatoNoSoportadoException{
        fachada.generarDocumento(formato, Contenido);
    }

}
