/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;
import javax.xml.crypto.Data;

/**
 *
 * @author milto
 */
public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correoElectronico;
    private Date fechaNaciemiento;
    private String genero;
    
    public Cliente(String nombre, String apellido, String telefono, String correoElectronico, Date fechaNaciemiento, String genero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.fechaNaciemiento = fechaNaciemiento; 
        //= cambiarStringADate(fechaNaciemiento);
        this.genero = genero;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getCorreoElectronico() {
        return correoElectronico;
    }
    public Date getFechaNacimiento() {
        return fechaNaciemiento;
    }
    public String getGenero() {
        return genero;
    }
    public int getId() {
        return id;
    }
    public void setId(int pId) {
        this.id = pId;
    }
    
}
