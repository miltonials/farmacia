/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Andy Porras
 */
public class MontoTotalVendidoPorMes {
    private String mes;
    private int total;

    public MontoTotalVendidoPorMes(String mes, int total) {
        this.mes = mes;
        this.total = total;
    }

    public String getMes() {
        return mes;
    }

    public int getTotal() {
        return total;
    }
    
}
