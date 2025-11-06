/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tienda.controlador;

import java.util.List;
import tienda.dto.ProductoDTO;
import tienda.logicanegocio.ServicioProducto;
import tienda.logicanegocio.LogicaNegocio;

/**
 *
 * @author jimmy
 */
public class ProductoControlador {
    private final ServicioProducto productoService;

    public ProductoControlador() {
        this.productoService = new LogicaNegocio(); // Se inyecta la lógica de negocio
    }

    // Método para agregar un producto desde la interfaz sin usar la entidad Producto
    public void agregarProducto(String nombre, String descripcion, double precio, int stock) {
        try {
            productoService.agregarProducto(nombre, descripcion, precio, stock);
            System.out.println("✔ Producto agregado con éxito desde el controlador.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error al agregar producto: " + e.getMessage());
        }
    }
    public void eliminarProducto(int id) {
        productoService.eliminarProducto(id);
    }

    // Método para obtener todos los productos como DTOs
    public List<ProductoDTO> obtenerProductos() {
        return productoService.listarProductos();
    }
}