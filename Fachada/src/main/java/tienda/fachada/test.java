package tienda.fachada;

import tienda.gestordocumentos.FormatoNoSoportadoException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kevin
 */
public class test {
    public static void main(String[] args) throws FormatoNoSoportadoException {
        IFachada fachada = new ImplFachada();
        System.out.println("\n[Paso 4: Generando documentos...]");
        System.out.println("-> Generando documento HTML:");
        fachada.generarDocumento("HTML", "Titulo Principal del Reporte");

        System.out.println("\n-> Generando documento de Texto Plano:");
        fachada.generarDocumento("Txt", "Este es el contenido del documento de texto.");

        System.out.println("\n\n## PRUEBA FINALIZADA ##");
    }
}
