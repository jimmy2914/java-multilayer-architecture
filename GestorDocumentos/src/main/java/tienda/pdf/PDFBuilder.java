/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.pdf;

/**
 *
 * @author kevin
 */
import tienda.interfaces.Documento;
import tienda.interfaces.DocumentoBuilder;

/**
 * Builder simple que construye un PDFComponenteAdaptado.
 */
public class PDFBuilder implements DocumentoBuilder {
    private final PDFComponente pdfComponente;

    public PDFBuilder() {
        this.pdfComponente = new PDFComponente();
    }

    @Override
    public DocumentoBuilder setContenido(String contenido) {
        pdfComponente.pdfAsignarContenido(contenido);
        return this;
    }

    @Override
    public Documento construir() {
        // Devuelve el componente original envuelto en el adaptador
        return new PDFAdaptado(this.pdfComponente);
    }
}
