/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tienda.vistaconsola;

/**
 *
 * @author jimmy
 */

import tienda.controlador.ProductoControlador;
import tienda.controlador.FachadaControlador;
import tienda.sistemaclientes.ClienteDuplicadoException;
import tienda.gestordocumentos.FormatoNoSoportadoException;
import tienda.fachada.*;
import tienda.dto.ProductoDTO;
import java.util.List;
import java.util.Scanner;

public class VistaConsola {
    private final ProductoControlador productoControlador;
    private final FachadaControlador fachadaControlador; // <--- CAMBIO 1: Se añade el controlador de la fachada
    private final Scanner scanner;

    public VistaConsola() {
        this.productoControlador = new ProductoControlador();
        this.scanner = new Scanner(System.in);
        
        // --- CAMBIO 2: Se inicializa la fachada y su controlador ---
        IFachada fachada = new ImplFachada();
        this.fachadaControlador = new FachadaControlador(fachada);
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("\n=== MENu TIENDA ===");
            System.out.println("--- Productos ---");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Eliminar Productos");
            System.out.println("3. Listar Productos");
            System.out.println("--- Clientes ---");
            System.out.println("4. Agregar Cliente");
            System.out.println("5. Eliminar Cliente");
            System.out.println("6. Listar Clientes");
            System.out.println("7. Generar Documento");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opcion: ");
            
            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Intente de nuevo.");
                continue;
            }
            
            switch (opcion) { // <--- CAMBIO 3: Se amplía el menú
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    eliminarProducto();
                    break;
                case 3:
                    listarProductos();
                    break;
                case 4:
                    agregarCliente();
                    break;
                case 5:
                    eliminarClientes();
                    break;
                case 6:
                    listarClientes();
                    break;
                case 7:
                    generarDocumento();
                    break;
                case 8:
                    System.out.println("Saliendo de la tienda...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    // --- MÉTODOS PARA PRODUCTOS (Sin cambios) ---
    private void agregarProducto() {
        System.out.print("\nNombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());
        System.out.print("Stock: ");
        int stock = Integer.parseInt(scanner.nextLine());
        productoControlador.agregarProducto(nombre, descripcion, precio, stock);
        System.out.println("Producto agregado con éxito.");
    }

    
    private void eliminarProducto() {
        System.out.print("Ingrese el nombre del producto a eliminar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            productoControlador.eliminarProducto(id);
            System.out.println(">> Producto '" + id + "' eliminado correctamente.");
        } catch (Exception e) {
            System.err.println("!! Ocurrió un error al eliminar el producto: " + e.getMessage());
        }
    }
    
    private void listarProductos() {
        List<ProductoDTO> productos = productoControlador.obtenerProductos();
        if (productos.isEmpty()) {
            System.out.println("\nNo hay productos registrados.");
        } else {
            System.out.println("\nLista de productos:");
            for (ProductoDTO p : productos) {
                System.out.println("➡ " + p);
            }
        }
    }

    private void agregarCliente() {
        System.out.print("\nNombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        try {
            fachadaControlador.registrarCliente(nombre, telefono, email);
        } catch (ClienteDuplicadoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void eliminarClientes() {
        System.out.print("Ingrese el ID del cliente a eliminar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            fachadaControlador.eliminarCliente(id);
            System.out.println(">> Cliente con ID " + id + " eliminado correctamente.");
        } catch (NumberFormatException e) {
            System.err.println("!! Error: El ID debe ser un número.");
        } catch (Exception e) {
            System.err.println("!! Ocurrió un error al eliminar el cliente: " + e.getMessage());
        }
    }

    private void listarClientes() {
        List<String> clientes = fachadaControlador.obtenerClientes();
        if (clientes.isEmpty()) {
            System.out.println("\nNo hay clientes registrados.");
        } else {
            System.out.println("\n--------------------Lista de clientes-----------------\n");
            for (String cliente : clientes) {
                System.out.println("➡ " + cliente);
            }
            System.out.println("\n------------------------------------------------------\n");
        }
    }
    
    private void generarDocumento() {
        try {
            System.out.print("Ingrese el formato del documento (HTML, PDF, TEXTO): ");
            String formato = scanner.nextLine();
            System.out.print("Ingrese el contenido del documento: ");
            String contenido = scanner.nextLine();
            fachadaControlador.generarDoc(formato, contenido);
            System.out.println("Documento generado exitosamente.");
        } catch (FormatoNoSoportadoException e) {
            System.err.println("Error al generar el documento: " + e.getMessage());
        }
    }
    
}

