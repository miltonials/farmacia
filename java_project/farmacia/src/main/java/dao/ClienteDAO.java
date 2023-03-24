/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import interfaces.CRUD;
import static java.sql.DriverManager.println;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import modelo.Cliente;
import modelo.Conexion;

/**
 *
 * @author milto
 */
public class ClienteDAO implements CRUD {
private Conexion conexion = new Conexion();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    @Override
    public int create(Object cliente) {
        Cliente miCliente = (Cliente) cliente;
        //FUNCTION insertar_producto (p_id_farmaceutica NUMBER, p_id_tipo_producto NUMBER, p_nombre VARCHAR2, p_descripcion VARCHAR2, p_precio NUMBER, p_cantidad_stock NUMBER) RETURN NUMBER;
        String sql = "SELECT farmacia_insertar.insertar_cliente(?, ?, ?, ?, ?, ?) FROM DUAL";
        int respuesta = 0;
        try {
            conexion.conectar();
            // Nombre, Apellido, Telefono, Correo_electronico, Fecha_nacimiento, Genero
            println("fecha: " + miCliente.getFechaNacimiento());
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setString(1, miCliente.getNombre());
            preparedStatement.setString(2, miCliente.getApellido());
            preparedStatement.setString(3, miCliente.getTelefono());
            preparedStatement.setString(4, miCliente.getCorreoElectronico());
            preparedStatement.setDate(5, new java.sql.Date(miCliente.getFechaNacimiento().getTime()));
            preparedStatement.setString(6, miCliente.getGenero());
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                respuesta = resultSet.getInt(1);
                sql = "SELECT id_cliente FROM PRODUCTO WHERE nombre = ? AND apellido = ? AND telefono = ? AND correo_electronico = ? AND fecha_nacimiento = ? AND genero = ?";
                preparedStatement = conexion.prepararSql(sql);
                preparedStatement.setString(1, miCliente.getNombre());
                preparedStatement.setString(2, miCliente.getApellido());
                preparedStatement.setString(3, miCliente.getTelefono());
                preparedStatement.setString(4, miCliente.getCorreoElectronico());
                preparedStatement.setDate(5, new  java.sql.Date(miCliente.getFechaNacimiento().getTime()));
                preparedStatement.setString(6, miCliente.getGenero());
                resultSet = preparedStatement.executeQuery();
                
                if (resultSet.next()) {
                    miCliente.setId(resultSet.getInt(1));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al crear producto: " + e.getMessage());
        }
        return respuesta;
    }

    @Override
    public Object read(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(Object cliente) {
        int respuesta = 0;
        Cliente miCliente = (Cliente) cliente;
        String sql = "SELECT farmacia_modificar.modificar_cliente(?, ?, ?, ?, ?, ?, ?) from dual";
        //p_id_producto IN NUMBER, p_id_farmaceutica NUMBER, p_id_tipo_producto NUMBER, p_nombre VARCHAR2, p_descripcion VARCHAR2, p_precio NUMBER, p_cantidad_stock NUMBER

        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setInt(1, miCliente.getId());
            preparedStatement.setString(2, miCliente.getNombre());
            preparedStatement.setString(3, miCliente.getApellido());
            preparedStatement.setString(4, miCliente.getTelefono());
            preparedStatement.setString(5, miCliente.getCorreoElectronico());
            preparedStatement.setDate(6, new java.sql.Date(miCliente.getFechaNacimiento().getTime()));
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                respuesta = resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }

        return respuesta;    }

    @Override
    public int delete(Object cliente) {
        int respuesta = 0;
        Cliente miCliente = (Cliente) cliente;
        String sql = "CALL farmacia_eliminar.eliminar_cliente(?)";

        try {
            conexion.conectar();
            preparedStatement = conexion.prepararSql(sql);
            preparedStatement.setInt(1, miCliente.getId());
            preparedStatement.executeQuery();
            
        } catch (Exception e) {
            System.out.println("Error al eliminar el cliente: " + e.getMessage());
        }

        return respuesta;    }
}
