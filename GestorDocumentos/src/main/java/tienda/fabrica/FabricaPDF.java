/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.fabrica;


/**
 *
 * @author kevin
 */
import tienda.pdf.PDFAdaptado;
import tienda.interfaces.Documento;

public class FabricaPDF extends Fabricadocumento {
    @Override
    public Documento creardocumento() {
        return new PDFAdaptado();
    }
}
