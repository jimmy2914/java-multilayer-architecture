/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tienda.pdf;

/**
 *
 * @author jimmy
 */
public class PDFComponente {
    private String contenido = "";

    public void pdfAsignarContenido(String contenido) {
        this.contenido = contenido;
    }

    public void pdfPrepararVisualizacion() {
        System.out.println("... Preparando visualización del PDF ...");
    }

    public void pdfRefrescar() {
        System.out.println("... Refrescando visualización del PDF ...");
    }

    public void pdfFinalizarVisualizacion() {
        System.out.println("... Finalizando visualización del PDF ...");
    }

    public void pdfEnviarImpresora() {
        System.out.println("--- ENVIANDO A IMPRESORA PDF ---");
        System.out.println("Contenido: " + contenido);
        System.out.println("--------------------------------");
    }
}
