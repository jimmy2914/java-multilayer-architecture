package tienda.textoplano;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author kevin
 */
import tienda.interfaces.Documento;

/**
 * Implementación concreta para un documento de texto plano.
 */
public class TextoPlanoComponente implements Documento {
    private String contenido = "";

    @Override
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public void dibujar() {
        System.out.println("--- DIBUJANDO TEXTO PLANO ---");
        System.out.println(contenido);
        System.out.println("-----------------------------");
    }

    @Override
    public void imprimir() {
        System.out.println("--- IMPRIMIENDO TEXTO PLANO ---");
        System.out.println("Contenido: " + contenido);
        System.out.println("-------------------------------");
    }
}
