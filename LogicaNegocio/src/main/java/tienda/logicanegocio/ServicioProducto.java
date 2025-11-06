/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tienda.logicanegocio;
import java.util.List;
import tienda.dto.ProductoDTO;
/**
 *
 * @author jimmy
 */
public interface ServicioProducto {
    void agregarProducto(String nombre, String descripcion, double precio, int stock);
    void eliminarProducto(int id);
    List<ProductoDTO> listarProductos();
}
