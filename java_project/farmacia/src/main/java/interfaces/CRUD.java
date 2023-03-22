package interfaces;

import java.util.ArrayList;

/**
 * Interfaz que contiene los métodos para realizar las operaciones CRUD
 * (Create, Read, Update, Delete) en la base de datos.
 * @author Milton Barrera
 */
public interface CRUD {
    public int create(Object objeto);
    public int read(Object objecto);
    public int update(Object objeto);
    public int delete(Object objeto);
}
