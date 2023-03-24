package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author milto
 */
public class Venta {
    private int id;
    private Date fecha_emision;
    private Cliente cliente;
    private Empleado empleado;
    private double total_venta;

    public Venta(Date fecha_emision, Cliente cliente, Empleado empleado, double total_venta) {
        this.fecha_emision = fecha_emision;
        this.cliente = cliente;
        this.empleado = empleado;
        this.total_venta = total_venta;
    }

    public Venta(int id, Date fecha_emision, Cliente cliente, Empleado empleado, double total_venta) {
        this.id = id;
        this.fecha_emision = fecha_emision;
        this.cliente = cliente;
        this.empleado = empleado;
        this.total_venta = total_venta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(Date fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public double getTotal_venta() {
        return total_venta;
    }

    public void setTotal_venta(double total_venta) {
        this.total_venta = total_venta;
    }
    
    public String getFecha(){
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        return formatoFecha.format(this.fecha_emision);
    }
}
