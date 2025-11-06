/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.html;

/**
 *
 * @author kevin
 */
import tienda.interfaces.Documento;
import tienda.interfaces.DocumentoBuilder;

/**
 * Builder concreto para crear instancias de HTMLComponente.
 */
public class HTMLBuilder implements DocumentoBuilder {
    private final HTMLComponente documento = new HTMLComponente();

    @Override
    public DocumentoBuilder setContenido(String contenido) {
        documento.setContenido(contenido);
        return this;
    }

    @Override
    public Documento construir() {
        return documento;
    }
}