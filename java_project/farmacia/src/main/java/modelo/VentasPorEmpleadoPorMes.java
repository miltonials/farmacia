/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Andy Porras
 */
public class VentasPorEmpleadoPorMes {
    private String mes;
    private String nombre;
    private int cantidad;
    private int total;

    public VentasPorEmpleadoPorMes(String mes, String nombre, int cantidad, int total) {
        this.mes = mes;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.total = total;
    }

    public String getMes() {
        return mes;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getTotal() {
        return total;
    }
    
}
