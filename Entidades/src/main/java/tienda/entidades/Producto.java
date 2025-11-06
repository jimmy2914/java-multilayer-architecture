/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tienda.entidades;

/**
 *
 * @author jimmy
 */
public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;

    // Constructor privado: solo puede ser usado por el Builder
    private Producto(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.descripcion = builder.descripcion;
        this.precio = builder.precio;
        this.stock = builder.stock;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    // Clase interna Builder
    public static class Builder {
        private int id;
        private String nombre;
        private String descripcion;
        private double precio;
        private int stock;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setDescripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public Builder setPrecio(double precio) {
            this.precio = precio;
            return this;
        }

        public Builder setStock(int stock) {
            this.stock = stock;
            return this;
        }

        public Producto build() {
            return new Producto(this);
        }
    }
}