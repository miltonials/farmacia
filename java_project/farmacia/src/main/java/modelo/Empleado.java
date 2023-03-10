/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Kevin Salazar
 */
public class Empleado {
    int id;
    String nombre;
    String pssw;
    
    public Empleado(){
        
    }
    
    public Empleado(int id, String nombre, String contra) {
        this.id = id;
        this.nombre = nombre;
        this.pssw = contra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPssw() {
        return pssw;
    }

    public void setPssw(String contra) {
        this.pssw = contra;
    }
    
    
}
