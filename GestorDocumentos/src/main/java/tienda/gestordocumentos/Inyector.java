/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.gestordocumentos;

/**
 *
 * @author kevin
 */


import java.util.HashMap;
import java.util.Map;
import tienda.fabrica.Fabricadocumento;
import tienda.fabrica.FabricaHTML;
import tienda.fabrica.FabricaPDF;
import tienda.fabrica.FabricaTextoPlano;

public class Inyector {
    private static final Map<String, Fabricadocumento> fabricas = new HashMap<>();

    public static Fabricadocumento getFabrica(String tipo) {
        tipo = tipo.toUpperCase();
        if (!fabricas.containsKey(tipo)) {
            switch (tipo) {
                case "HTML":
                    fabricas.put("HTML", new FabricaHTML());
                    break;
                case "PDF":
                    fabricas.put("PDF", new FabricaPDF());
                    break;
                case "TEXTO":
                     fabricas.put("TEXTO", new FabricaTextoPlano());
                     break;
                default:
                    throw new IllegalArgumentException("Tipo de documento no soportado: " + tipo);
            }
        }
        return fabricas.get(tipo);
    }
}