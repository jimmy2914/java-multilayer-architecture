/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tienda.interfaces;

/**
 *
 * @author kevin
 */
public interface DocumentoBuilder {
    DocumentoBuilder setContenido(String contenido);
    Documento construir();
}
