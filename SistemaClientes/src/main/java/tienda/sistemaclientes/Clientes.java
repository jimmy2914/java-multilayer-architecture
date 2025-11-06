/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienda.sistemaclientes;

/**
 *
 * @author kevin
 */
public class Clientes{
    private double id;
    private String nombre;
    private double telefono;
    private String correo;
    public void cargarInformacion(){
        System.out.println("Información enviada al sistema de clientes");
    }
    public double getId() {
        return id;
    }
    public String getNombre() {
            return nombre;
        }

    public String getCorreo() {
        return correo;
    }
    
    public double getTelefono() {
        return telefono;
    }

    
    public void setId(double id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
            this.nombre = nombre;
        }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(double telefono) {
        this.telefono = telefono;
    }
    
    
    
            
    
}
