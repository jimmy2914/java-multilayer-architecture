/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.html;

/**
 *
 * @author kevin
 */
import tienda.interfaces.Documento;


public class HTMLComponente implements Documento {
    private String contenido = "";

    @Override
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public void dibujar() {
        System.out.println("--- DIBUJANDO HTML ---");
        System.out.println(contenido);
        System.out.println("\n----------------------");
    }

    @Override
    public void imprimir() {
        System.out.println("--- IMPRIMIENDO HTML ---");
        System.out.println("Contenido: " + contenido);
        System.out.println("\n------------------------");
    }
}
