package interfaces;

import java.util.ArrayList;

/**
 * Interfaz que contiene los métodos para realizar las operaciones CRUD
 * (Create, Read, Update, Delete) en la base de datos.
 * @author Milton Barrera
 */
public interface CRUD {
    public boolean create(Object objeto);
    public boolean read(Object objecto);
    public boolean update(Object objeto);
    public boolean delete(Object objeto);
}
