package tienda.pdf;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */




/**
 *
 * @author kevin
 */
import tienda.interfaces.Documento;

/**
 * Adaptador que permite que un PDFComponente (con su interfaz específica)
 * funcione como un objeto de tipo Documento.
 */
public class PDFAdaptado implements Documento {
    private final PDFComponente pdf;
    
    public PDFAdaptado() {
        this.pdf = new PDFComponente();
    }
    
    // NUEVO CONSTRUCTOR: Acepta una instancia ya creada
    public PDFAdaptado(PDFComponente pdf) {
        this.pdf = pdf;
    }

    // ... el resto de los métodos se mantienen igual ...
    @Override
    public void setContenido(String contenido) {
        pdf.pdfAsignarContenido(contenido);
    }

    @Override
    public void dibujar() {
        System.out.println("--- DIBUJANDO PDF (ADAPTADO) ---");
        System.out.println(pdf);
        System.out.println("--------------------------------");
    }

    @Override
    public void imprimir() {
        pdf.pdfEnviarImpresora();
    }
}
