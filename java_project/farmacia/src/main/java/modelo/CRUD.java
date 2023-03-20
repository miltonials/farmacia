package modelo;

/**
 *
 * @author milto
 */
public interface CRUD {
    public boolean create(Object objeto);
    public boolean read(Object objecto);
    public boolean update(Object objeto);
    public boolean delete(Object objeto);
}
