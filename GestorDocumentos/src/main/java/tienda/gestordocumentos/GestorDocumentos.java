/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.gestordocumentos;

import tienda.interfaces.Documento;

/**
 *
 * @author kevin
 */
public class GestorDocumentos {
    public void crearDocumento(String formato, String contenido) throws FormatoNoSoportadoException{
        Documento miDoc = null;
        switch(formato.toUpperCase()){
            case "HTML","PDF": miDoc = Builder.tipo(formato)
                           .setContenido(contenido)
                           .construir();
                            break;
            case "TEXTOPLANO","TXT","TEXTO":miDoc = Builder.tipo("TEXTO")
                           .setContenido(contenido )
                           .construir();
                            break;
            default:
                throw new FormatoNoSoportadoException("El formato '" + formato + "' no es soportado. \nUse Pdf, html o textoplano.....");
        }
         miDoc.dibujar();
    }
    
}
