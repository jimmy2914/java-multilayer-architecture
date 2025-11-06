/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tienda.logicanegocio;

import java.util.List;
import tienda.entidades.Producto;
import tienda.persistencia.ProductoDAO;
import java.util.stream.Collectors;
import tienda.dto.ProductoDTO;

/**
 *
 * @author jimmy
 */

public class LogicaNegocio implements ServicioProducto{

    private final ProductoDAO productoDAO;

    public LogicaNegocio() {
        this.productoDAO = new ProductoDAO(); // Inyección de dependencia directa
    }
    

     public void agregarProducto(String nombre, String descripcion, double precio, int stock) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }

         Producto producto = new Producto.Builder()
                .setNombre(nombre)
                .setDescripcion(descripcion)
                .setPrecio(precio)
                .setStock(stock)
                .build();
        productoDAO.guardarProducto(producto);
    }

    public List<ProductoDTO> listarProductos() {
        return productoDAO.obtenerProductos()
                .stream()
                .map(p -> new ProductoDTO(
                        p.getId(),  // Usamos los getters del producto
                        p.getNombre(),
                        p.getDescripcion(),
                        p.getPrecio(),
                        p.getStock()))
                .collect(Collectors.toList());
    }
    public void eliminarProducto(int id) {
    productoDAO.eliminarProducto(id);
}
    
}
