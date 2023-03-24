package modelo;

/**
 *
 * @author milto
 */
public class Farmaceutica {
    private int id;
    private String nombre;
    private String telefono;
    private String correoElectronico;

    public Farmaceutica(int id,String nombre, String telefono, String correoElectronico) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    public Farmaceutica(String nombre, String telefono, String correo_electronico) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correoElectronico = correo_electronico;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setId(int pID) {
       this.id = pID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    
}
