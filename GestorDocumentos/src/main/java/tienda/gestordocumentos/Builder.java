/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.gestordocumentos;

/**
 *
 * @author kevin
 */

import tienda.html.HTMLBuilder;
import tienda.pdf.PDFBuilder;
import tienda.textoplano.TextoPlanoBuilder;
import tienda.interfaces.DocumentoBuilder;

public class Builder {
    public static DocumentoBuilder tipo(String tipo) {
        tipo = tipo.toUpperCase();
        switch (tipo) {
            case "HTML":
                return new HTMLBuilder();
            case "PDF":
                return new PDFBuilder();
            case "TEXTO":
                return new TextoPlanoBuilder();
            default:
                throw new IllegalArgumentException("Tipo de builder no soportado: " + tipo);
        }
    }
}
