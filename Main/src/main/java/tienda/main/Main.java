/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tienda.main;

/**
 *
 * @author jimmy
 */
import java.sql.DriverManager;
import tienda.vistaconsola.VistaConsola;
import tienda.persistencia.ProductoDAO;
import tienda.vistaescritorio.VistaEscritorio;
import javax.swing.SwingUtilities;


public class Main {

    public static void main(String[] args) {
        // 1. Inicialización de la base de datos (se ejecuta en el hilo principal)
        System.out.println("Iniciando la base de datos...");
        try {
            // --- INICIO DE LA SOLUCIÓN ---
            // Forzar la carga del driver de H2 en la memoria.
            Class.forName("org.h2.Driver");
            // --- FIN DE LA SOLUCIÓN ---
            ProductoDAO productoDAO = new ProductoDAO();
            productoDAO.crearTabla();
            productoDAO.insertarProductosDefault();
            System.out.println("Base de datos lista.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Thread vistaEscritorioThread = new Thread(() -> {
            SwingUtilities.invokeLater(() -> new VistaEscritorio().setVisible(true));
        });

        Thread vistaConsolaThread = new Thread(() -> {
            VistaConsola vistaConsola = new VistaConsola();
            vistaConsola.mostrarMenu();
        });

        vistaEscritorioThread.start();
        vistaConsolaThread.start();

        System.out.println("Ambas interfaces han sido lanzadas y se están ejecutando simultáneamente.");
    }
}
