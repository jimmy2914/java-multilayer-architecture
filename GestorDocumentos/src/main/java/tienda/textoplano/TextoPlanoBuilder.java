package tienda.textoplano;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kevin
 */
import tienda.interfaces.Documento;
import tienda.interfaces.DocumentoBuilder;

/**
 * Builder concreto para crear instancias de TextoPlanoComponente.
 */
public class TextoPlanoBuilder implements DocumentoBuilder {
    private final TextoPlanoComponente documento = new TextoPlanoComponente();

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