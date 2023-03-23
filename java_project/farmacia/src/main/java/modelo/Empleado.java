package modelo;

import java.util.Date;

/**
 *
 * @author milto
 */
public class Empleado {

    private int id;
    private String nombre;
    private String idCargo;
    private String Apellido;
    private double salario;
    private Date fechaContratacion;
    private String cedula;
    private String clave;

    public Empleado(int id, String nombre, String idCargo, String Apellido, double salario, Date fechaContratacion, String cedula) {
        this.id = id;
        this.nombre = nombre;
        this.idCargo = idCargo;
        this.Apellido = Apellido;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
        this.cedula = cedula;
    }
    
    public Empleado(String idCargo, String cedula, String nombre, String Apellido, double salario, Date fechaContratacion, String clave) {
        this.idCargo = idCargo;
        this.nombre = nombre;
        this.Apellido = Apellido;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
        this.cedula = cedula;
        this.clave = clave;
    }

    public Empleado(int id, String nombre, String idCargo, String Apellido, double salario, Date fechaContratacion, String cedula, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.idCargo = idCargo;
        this.Apellido = Apellido;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
        this.cedula = cedula;
        this.clave = clave;
    }
    
    

    public Empleado(String nombre, String clave) {
        this.nombre = nombre;
        this.clave = clave;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdCargo() {
        return idCargo;
    }

    public String getApellido() {
        return Apellido;
    }

    public double getSalario() {
        return salario;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public String getCedula() {
        return cedula;
    }

    public String getClave() {
        return clave;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdCargo(String idCargo) {
        this.idCargo = idCargo;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    
}
