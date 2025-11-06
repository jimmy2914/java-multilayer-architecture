/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.fabrica;
/**
 *
 * @author jimmy
 */
import tienda.textoplano.TextoPlanoComponente;
import tienda.interfaces.Documento;

public class FabricaTextoPlano extends Fabricadocumento {
    @Override
    public Documento creardocumento() {
        return new TextoPlanoComponente();
    }
}
