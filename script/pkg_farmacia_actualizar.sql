CREATE OR REPLACE PACKAGE farmacia_modificar AS 
  FUNCTION modificar_tipo_producto (p_id_tipo_producto IN NUMBER, p_nombre IN VARCHAR2) RETURN NUMBER;
  FUNCTION modificar_producto (p_id_producto IN NUMBER, p_id_farmaceutica NUMBER, p_id_tipo_producto NUMBER, p_nombre VARCHAR2, p_descripcion VARCHAR2, p_precio NUMBER, p_cantidad_stock NUMBER) RETURN NUMBER;
  FUNCTION modificar_farmaceutica (p_id_farmaceutica IN NUMBER, p_nombre IN VARCHAR2, p_telefono IN VARCHAR2, p_correo IN VARCHAR2) RETURN NUMBER;
  FUNCTION modificar_venta (p_id_venta IN NUMBER, p_id_cliente IN NUMBER, p_id_empleado IN NUMBER, p_fecha_venta IN DATE) RETURN NUMBER;
  FUNCTION modificar_detalle_venta (p_id_venta IN NUMBER, p_id_producto IN NUMBER, p_cantidad IN NUMBER) RETURN NUMBER; 
  FUNCTION modificar_cliente (p_id_cliente IN NUMBER, p_nombre IN VARCHAR2, p_apellido IN VARCHAR2, p_telefono IN VARCHAR2, p_correo IN VARCHAR2, p_fecha_nacimiento IN DATE, p_genero IN VARCHAR2) RETURN NUMBER;
  FUNCTION modificar_empleado (p_id_empleado IN NUMBER, p_cedula IN VARCHAR2, p_id_cargo IN NUMBER, p_nombre IN VARCHAR2, p_apellido IN VARCHAR2, p_fecha_contratacion IN DATE, p_salario IN NUMBER) RETURN NUMBER;
  FUNCTION modificar_cargo_empleado (p_id_cargo IN NUMBER, p_nombre IN VARCHAR2) RETURN NUMBER;
END farmacia_modificar;
/

CREATE OR REPLACE PACKAGE BODY farmacia_modificar AS
  FUNCTION modificar_tipo_producto (p_id_tipo_producto IN NUMBER, p_nombre IN VARCHAR2) RETURN NUMBER
IS PRAGMA AUTONOMOUS_TRANSACTION;
    respuesta NUMBER;
    BEGIN
        BEGIN
            EXECUTE IMMEDIATE 'UPDATE tipo_producto SET nombre = :1
                                WHERE id_tipo_producto = :2'
            USING p_nombre, p_id_tipo_producto;

            respuesta := 1;
            COMMIT;

            DBMS_OUTPUT.Put_Line('Se ha modificado el tipo de producto ' || p_nombre);
            RETURN respuesta;
        
        EXCEPTION
            --retorna el código de error
            WHEN DUP_VAL_ON_INDEX THEN
                DBMS_OUTPUT.Put_Line('El tipo de producto ' || p_nombre || ' ya existe');
                RETURN -1;
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE('Error al modificar' || SQLCODE);
                RETURN SQLCODE;
        END;
    END;

  FUNCTION modificar_producto (p_id_producto IN NUMBER, p_id_farmaceutica NUMBER, p_id_tipo_producto NUMBER, p_nombre VARCHAR2, p_descripcion VARCHAR2, p_precio NUMBER, p_cantidad_stock NUMBER) RETURN NUMBER
IS PRAGMA AUTONOMOUS_TRANSACTION;
    respuesta NUMBER;
    BEGIN
        BEGIN
            UPDATE producto
              SET id_farmaceutica = p_id_farmaceutica,
                  id_tipo_producto = p_id_tipo_producto,
                  nombre = p_nombre,
                  descripcion = p_descripcion,
                  precio = p_precio,
                  cantidad_stock = p_cantidad_stock
              WHERE id_producto = p_id_producto;

            respuesta := 1;
            COMMIT;

            DBMS_OUTPUT.Put_Line('Se ha modificado el producto ' || p_nombre);
            RETURN respuesta;
        
        EXCEPTION
            --retorna el código de error
            WHEN DUP_VAL_ON_INDEX THEN
                DBMS_OUTPUT.Put_Line('El producto ' || p_nombre || ' ya existe');
                RETURN -1;
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE('Error al modificar' || SQLCODE);
                RETURN SQLCODE;
        END;
    END;

    FUNCTION modificar_farmaceutica (p_id_farmaceutica IN NUMBER, p_nombre IN VARCHAR2, p_telefono IN VARCHAR2, p_correo IN VARCHAR2) RETURN NUMBER
IS PRAGMA AUTONOMOUS_TRANSACTION;
    respuesta NUMBER;
    BEGIN
        BEGIN
            UPDATE farmaceutica
              SET nombre = p_nombre,
                  telefono = p_telefono,
                  correo_electronico = p_correo
              WHERE id_farmaceutica = p_id_farmaceutica;

            respuesta := 1;
            COMMIT;

            DBMS_OUTPUT.Put_Line('Se ha modificado la farmaceutica ' || p_nombre);
            RETURN respuesta;
        
        EXCEPTION
            --retorna el código de error
            WHEN DUP_VAL_ON_INDEX THEN
                DBMS_OUTPUT.Put_Line('La farmaceutica ' || p_nombre || ' ya existe');
                RETURN -1;
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE('Error al modificar' || SQLCODE);
                RETURN SQLCODE;
        END;
    END;

    FUNCTION modificar_venta (p_id_venta IN NUMBER, p_id_cliente IN NUMBER, p_id_empleado IN NUMBER, p_fecha_venta IN DATE) RETURN NUMBER
IS PRAGMA AUTONOMOUS_TRANSACTION;
    respuesta NUMBER;
    BEGIN
        BEGIN
            UPDATE venta
              SET id_cliente = p_id_cliente,
                  id_empleado = p_id_empleado,
                  fecha_emision = p_fecha_venta
              WHERE id_venta = p_id_venta;

            respuesta := 1;
            COMMIT;

            DBMS_OUTPUT.Put_Line('Se ha modificado la venta ' || p_id_venta);
            RETURN respuesta;
        
        EXCEPTION
            --retorna el código de error
            WHEN DUP_VAL_ON_INDEX THEN
                DBMS_OUTPUT.Put_Line('La venta ' || p_id_venta || ' ya existe');
                RETURN -1;
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE('Error al modificar' || SQLCODE);
                RETURN SQLCODE;
        END;
    END;

    FUNCTION modificar_detalle_venta (p_id_venta IN NUMBER, p_id_producto IN NUMBER, p_cantidad IN NUMBER) RETURN NUMBER
IS PRAGMA AUTONOMOUS_TRANSACTION;
    respuesta NUMBER;
    BEGIN
        BEGIN
            UPDATE detalle_venta
              SET cantidad = p_cantidad
              WHERE id_venta = p_id_venta and id_producto  = p_id_producto;

            respuesta := 1;
            COMMIT;

            DBMS_OUTPUT.Put_Line('Se ha modificado el detalle de venta ');
            RETURN respuesta;
        
        EXCEPTION
            --retorna el código de error
            WHEN DUP_VAL_ON_INDEX THEN
                DBMS_OUTPUT.Put_Line('El detalle de venta ya existe');
                RETURN -1;
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE('Error al modificar' || SQLCODE);
                RETURN SQLCODE;
        END;
    END;

    FUNCTION modificar_cliente (p_id_cliente IN NUMBER, p_nombre IN VARCHAR2, p_apellido IN VARCHAR2, p_telefono IN VARCHAR2, p_correo IN VARCHAR2, p_fecha_nacimiento IN DATE, p_genero IN VARCHAR2) RETURN NUMBER
IS PRAGMA AUTONOMOUS_TRANSACTION;
    respuesta NUMBER;
    BEGIN
        BEGIN
            UPDATE cliente
              SET nombre = p_nombre,
                  apellido = p_apellido,
                  telefono = p_telefono,
                  correo_electronico = p_correo,
                  fecha_nacimiento = p_fecha_nacimiento,
                  genero = p_genero
              WHERE id_cliente = p_id_cliente;

            respuesta := 1;
            COMMIT;

            DBMS_OUTPUT.Put_Line('Se ha modificado el cliente ' || p_nombre);
            RETURN respuesta;
        
        EXCEPTION
            --retorna el código de error
            WHEN DUP_VAL_ON_INDEX THEN
                DBMS_OUTPUT.Put_Line('El cliente ' || p_nombre || ' ya existe');
                RETURN -1;
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE('Error al modificar' || SQLCODE);
                RETURN SQLCODE;
        END;
    END;

    FUNCTION modificar_empleado (p_id_empleado IN NUMBER, p_cedula IN VARCHAR2, p_id_cargo IN NUMBER, p_nombre IN VARCHAR2, p_apellido IN VARCHAR2, p_fecha_contratacion IN DATE, p_salario IN NUMBER) RETURN NUMBER
IS PRAGMA AUTONOMOUS_TRANSACTION;
    respuesta NUMBER;
    BEGIN
        BEGIN
            UPDATE empleado
              SET nombre = p_nombre,
                  cedula = p_cedula,
                  apellido = p_apellido,
                  fecha_contratacion = p_fecha_contratacion,
                  salario = p_salario,
                  id_cargo = p_id_cargo
              WHERE id_empleado = p_id_empleado;

            respuesta := 1;
            COMMIT;

            DBMS_OUTPUT.Put_Line('Se ha modificado el empleado ' || p_nombre);
            RETURN respuesta;
        
        EXCEPTION
            --retorna el código de error
            WHEN DUP_VAL_ON_INDEX THEN
                DBMS_OUTPUT.Put_Line('El empleado ' || p_nombre || ' ya existe');
                RETURN -1;
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE('Error al modificar' || SQLCODE);
                RETURN SQLCODE;
        END;
    END;

    FUNCTION modificar_cargo_empleado (p_id_cargo IN NUMBER, p_nombre IN VARCHAR2) RETURN NUMBER
IS PRAGMA AUTONOMOUS_TRANSACTION;
    respuesta NUMBER;
    BEGIN
        BEGIN
            UPDATE cargo_empleado
              SET nombre = p_nombre
              WHERE id_cargo = p_id_cargo;

            respuesta := 1;
            COMMIT;

            DBMS_OUTPUT.Put_Line('Se ha modificado el cargo ' || p_nombre);

            RETURN respuesta;
        
        EXCEPTION
            --retorna el código de error
            WHEN DUP_VAL_ON_INDEX THEN
                DBMS_OUTPUT.Put_Line('El cargo ' || p_nombre || ' ya existe');
                RETURN -1;
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE('Error al modificar' || SQLCODE);
                RETURN SQLCODE;
        END;
    END;
END farmacia_modificar;
/

