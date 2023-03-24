CREATE OR REPLACE PACKAGE farmacia_eliminar AS
  PROCEDURE eliminar_tipo_producto (p_id_tipo_producto IN NUMBER);
  PROCEDURE eliminar_producto (p_id_producto IN NUMBER);
  PROCEDURE eliminar_farmaceutica (p_id_farmaceutica IN NUMBER);
  PROCEDURE eliminar_venta (p_id_venta IN NUMBER);
  PROCEDURE eliminar_detalle_venta (p_id_venta IN NUMBER, p_id_producto IN NUMBER);
  PROCEDURE eliminar_cliente (p_id_cliente IN NUMBER);
  PROCEDURE eliminar_empleado (p_id_empleado IN NUMBER);
  PROCEDURE eliminar_cargo_empleado (p_id_cargo IN NUMBER);
END farmacia_eliminar;
/

CREATE OR REPLACE PACKAGE BODY farmacia_eliminar AS
  PROCEDURE eliminar_tipo_producto (
      p_id_tipo_producto IN NUMBER
  ) AS
      BEGIN
          DELETE FROM tipo_producto
            WHERE id_tipo_producto = p_id_tipo_producto;
          DBMS_OUTPUT.Put_Line('Registro eliminado');
      END;

  PROCEDURE eliminar_producto (
      p_id_producto IN NUMBER
  ) AS
        registro_eliminar VARCHAR2(100);
      BEGIN
          DELETE FROM producto
            WHERE id_producto = p_id_producto;
          DBMS_OUTPUT.Put_Line('Registro eliminado');
      END;
  
  PROCEDURE eliminar_venta (
    p_id_venta IN NUMBER
  )
  IS
      BEGIN
          DELETE FROM venta
            WHERE id_venta = p_id_venta;
          DBMS_OUTPUT.Put_Line('Registro eliminado');
      END;

  PROCEDURE eliminar_empleado (
    p_id_empleado IN NUMBER
  )
  IS
      BEGIN
          DELETE FROM empleado
            WHERE id_empleado = p_id_empleado;
          DBMS_OUTPUT.Put_Line('Registro eliminado');
      END;

  PROCEDURE eliminar_farmaceutica (
    p_id_farmaceutica IN NUMBER
  )
  IS
      BEGIN
          DELETE FROM farmaceutica
            WHERE id_farmaceutica = p_id_farmaceutica;
          DBMS_OUTPUT.Put_Line('Registro eliminado');
      END;

  PROCEDURE eliminar_cliente (
    p_id_cliente IN NUMBER
  )
  IS
      BEGIN
          DELETE FROM cliente
            WHERE id_cliente = p_id_cliente;
          DBMS_OUTPUT.Put_Line('Registro eliminado');
      END;

  PROCEDURE eliminar_detalle_venta (
    p_id_venta IN NUMBER, p_id_producto IN NUMBER
  ) AS
    monto NUMBER;
      BEGIN
          SELECT precio_unitario * cantidad INTO monto
            FROM DETALLE_VENTA
                WHERE id_venta = p_id_venta AND
                      id_producto = p_id_producto;
          UPDATE VENTA
            SET total_venta = total_venta - monto
            WHERE id_venta =  p_id_venta;
            

          --restaurar el stock
          UPDATE producto
            SET cantidad_stock = cantidad_stock + (SELECT cantidad
                                  FROM detalle_venta
                                  WHERE id_venta = p_id_venta AND
                                        id_producto = p_id_producto)
            WHERE id_producto = p_id_producto;
            
          DELETE FROM detalle_venta
            WHERE id_venta = p_id_venta AND
                  id_producto = p_id_producto;
          COMMIT;
          DBMS_OUTPUT.Put_Line('Registro eliminado');
      END;

  PROCEDURE eliminar_cargo_empleado (
    p_id_cargo IN NUMBER
  ) AS
      BEGIN
          DELETE FROM cargo_empleado
            WHERE id_cargo = p_id_cargo;
          DBMS_OUTPUT.Put_Line('Registro eliminado');
      END;
END farmacia_eliminar;
/


