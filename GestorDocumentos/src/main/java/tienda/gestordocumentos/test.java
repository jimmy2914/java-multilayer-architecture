/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tienda.gestordocumentos;

/**
 *
 * @author kevin
 */


import tienda.fabrica.Fabricadocumento;
import tienda.interfaces.Documento;

public class test {
    public static void main(String[] args) {
        
        System.out.println("=================================================");
        System.out.println("### DEMOSTRACIÓN DEL PATRÓN FACTORY ###");
        System.out.println("=================================================");

        // --- Crear un documento PDF usando la fábrica ---
        Fabricadocumento fabricaPDF = Inyector.getFabrica("PDF");
        Documento documentoPDF = fabricaPDF.creardocumento();
        
        System.out.println("\n--- Creando PDF con Fábrica ---");
        documentoPDF.setContenido("Contenido del PDF desde la fábrica.");
        documentoPDF.dibujar();
        documentoPDF.imprimir();

        System.out.println("\n-------------------------------------------------");
        System.out.println("### DEMOSTRACIÓN DEL PATRÓN SINGLETON (PARA FÁBRICAS) ###");
        System.out.println("-------------------------------------------------");
        
        // --- Comprobando que el Inyector devuelve la misma instancia de la fábrica ---
        Fabricadocumento otraFabricaPDF = Inyector.getFabrica("PDF");
        System.out.println("Se solicita la fábrica de PDF por segunda vez.");
        System.out.println("¿Las 2 variables de fábrica apuntan a la misma instancia?: " + (fabricaPDF == otraFabricaPDF));
        
        System.out.println("\n=================================================");
        System.out.println("### DEMOSTRACIÓN DEL PATRÓN BUILDER ###");
        System.out.println("=================================================");
        
        // --- Crear documentos usando el Builder Fluido ---
        System.out.println("\n--- Creando HTML con Builder ---");
        Documento miHtml = Builder.tipo("HTML")
                .setContenido("<h1>Documento HTML creado con Builder</h1>")
                .construir();
        miHtml.dibujar();

        System.out.println("\n--- Creando PDF con Builder ---");
        Documento miPDF = Builder.tipo("PDF")
                .setContenido("Documento PDF creado con el nuevo PDFBuilderAdapter.")
                .construir();
        miPDF.dibujar();

        System.out.println("\n--- Creando Texto Plano con Builder ---");
        Documento miTexto = Builder.tipo("TEXTO")
                .setContenido("Documento de texto plano desde el builder.")
                .construir();
        miTexto.dibujar();
    }
}
