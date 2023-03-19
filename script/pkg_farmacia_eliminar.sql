CREATE OR REPLACE PACKAGE farmacia_eliminar AS
  PROCEDURE eliminar_tipo_producto (p_id_tipo_producto IN NUMBER);
  PROCEDURE eliminar_producto (p_id_producto IN NUMBER);
  PROCEDURE eliminar_farmaceutica (p_id_farmaceutica IN NUMBER);
  PROCEDURE eliminar_venta (p_id_venta IN NUMBER);
  PROCEDURE eliminar_detalle_venta (p_id_detalle_venta IN NUMBER);
  PROCEDURE eliminar_cliente (p_id_cliente IN NUMBER);
  PROCEDURE eliminar_empleado (p_id_empleado IN NUMBER);
  PROCEDURE eliminar_cargo_empleado (p_id_cargo IN NUMBER);
END farmacia_eliminar;
/

CREATE OR REPLACE PACKAGE BODY farmacia_eliminar AS
  PROCEDURE eliminar_tipo_producto (
      p_id_tipo_producto IN NUMBER
  ) AS
        registro_eliminar VARCHAR2(100);
      BEGIN
          SELECT nombre INTO registro_eliminar
          FROM tipo_producto
            WHERE id_tipo_producto = p_id_tipo_producto;

          DELETE FROM tipo_producto
            WHERE id_tipo_producto = p_id_tipo_producto;
          DBMS_OUTPUT.Put_Line('Se ha eliminado ' || registro_eliminar);
      END;

  PROCEDURE eliminar_producto (
      p_id_producto IN NUMBER
  ) AS
        registro_eliminar VARCHAR2(100);
      BEGIN
          SELECT nombre INTO registro_eliminar
          FROM producto
            WHERE id_producto = id_producto;
          
          DELETE FROM producto
            WHERE id_producto = p_id_producto;
          DBMS_OUTPUT.Put_Line('Se ha eliminado ' || registro_eliminar);
      END;
  
  PROCEDURE eliminar_venta (
    p_id_venta IN NUMBER
  )
  IS
      registro_eliminar VARCHAR2(100);
      BEGIN
          DELETE FROM venta
            WHERE id_venta = p_id_venta;
          DBMS_OUTPUT.Put_Line('Se ha eliminado ' || p_id_venta);
      END;

  PROCEDURE eliminar_empleado (
    p_id_empleado IN NUMBER
  )
  IS
        registro_eliminar VARCHAR2(100);
      BEGIN
          SELECT nombre INTO registro_eliminar
          FROM EMPLEADO 
            WHERE id_empleado = p_id_empleado;

          DELETE FROM empleado
            WHERE id_empleado = p_id_empleado;
          DBMS_OUTPUT.Put_Line('Se ha eliminado ' || registro_eliminar);
      END;

  PROCEDURE eliminar_farmaceutica (
    p_id_farmaceutica IN NUMBER
  )
  IS
        registro_eliminar VARCHAR2(100);
      BEGIN
          SELECT nombre INTO registro_eliminar
          FROM farmaceutica
            WHERE id_farmaceutica = p_id_farmaceutica;
          
          DELETE FROM farmaceutica
            WHERE id_farmaceutica = p_id_farmaceutica;
          DBMS_OUTPUT.Put_Line('Se ha eliminado ' || registro_eliminar);
      END;

  PROCEDURE eliminar_cliente (
    p_id_cliente IN NUMBER
  )
  IS
        registro_eliminar VARCHAR2(100);
      BEGIN
          SELECT nombre INTO registro_eliminar
          FROM cliente
            WHERE id_cliente = p_id_cliente;
          
          DELETE FROM cliente
            WHERE id_cliente = p_id_cliente;
          DBMS_OUTPUT.Put_Line('Se ha eliminado ' || registro_eliminar);
      END;

  PROCEDURE eliminar_detalle_venta (
    p_id_detalle_venta IN NUMBER
  ) AS
      BEGIN
          DELETE FROM detalle_venta
            WHERE id_venta = p_id_detalle_venta;
          DBMS_OUTPUT.Put_Line('Se ha eliminado ' || p_id_detalle_venta);
      END;

  PROCEDURE eliminar_cargo_empleado (
    p_id_cargo IN NUMBER
  ) AS
      registro_eliminar VARCHAR2(100);
      BEGIN
          SELECT nombre INTO registro_eliminar
          FROM cargo_empleado
            WHERE id_cargo = p_id_cargo;
          
          DELETE FROM cargo_empleado
            WHERE id_cargo = p_id_cargo;
          DBMS_OUTPUT.Put_Line('Se ha eliminado ' || registro_eliminar);
      END;
END farmacia_eliminar;
/


