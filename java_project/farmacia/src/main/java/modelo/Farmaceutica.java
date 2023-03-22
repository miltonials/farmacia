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

    public Farmaceutica(int id, String nombre, String telefono, String correoElectronico) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
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
}
