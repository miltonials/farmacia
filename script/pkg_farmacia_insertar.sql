CREATE OR REPLACE PACKAGE farmacia_insertar AS
  FUNCTION insertar_tipo_producto (p_nombre IN VARCHAR2) RETURN NUMBER;
  FUNCTION insertar_producto (p_id_farmaceutica NUMBER, p_id_tipo_producto NUMBER, p_nombre VARCHAR2, p_descripcion VARCHAR2, p_precio NUMBER, p_cantidad_stock NUMBER) RETURN NUMBER;
  FUNCTION insertar_farmaceutica (p_nombre IN VARCHAR2, p_telefono IN VARCHAR2, p_correo IN VARCHAR2) RETURN NUMBER;
  FUNCTION insertar_venta (p_fecha_emision IN DATE, p_id_cliente IN NUMBER, p_id_empleado IN NUMBER, p_total_venta IN NUMBER) RETURN NUMBER;
  FUNCTION insertar_detalle_venta (p_id_venta IN NUMBER, p_id_producto IN NUMBER, p_cantidad IN NUMBER, precio_unitario IN NUMBER) RETURN NUMBER;
  FUNCTION insertar_cliente (p_nombre IN VARCHAR2, p_apellido IN VARCHAR2, p_telefono IN VARCHAR2, p_correo_electronico IN VARCHAR2, p_fecha_nacimiento IN DATE, p_genero IN VARCHAR2) RETURN NUMBER;
  FUNCTION insertar_empleado (p_id_cargo IN NUMBER, p_nombre IN VARCHAR2, p_apellido IN VARCHAR2, p_salario IN NUMBER, p_fecha_contratacion IN DATE, p_clave IN VARCHAR2) RETURN NUMBER;
  FUNCTION insertar_cargo_empleado (p_nombre IN VARCHAR2) RETURN NUMBER;
END farmacia_insertar;
/

CREATE OR REPLACE PACKAGE BODY farmacia_insertar AS
  FUNCTION insertar_tipo_producto (p_nombre IN VARCHAR2) RETURN NUMBER AS
    respuesta NUMBER;
    BEGIN
        BEGIN
            INSERT INTO tipo_producto (nombre)
              VALUES (p_nombre);

            respuesta := 1;
            --COMMIT;

            DBMS_OUTPUT.Put_Line('Se ha insertado ' || p_nombre);
            RETURN respuesta;
        
        EXCEPTION
            --retorna el código de error
            WHEN DUP_VAL_ON_INDEX THEN
                DBMS_OUTPUT.Put_Line('El tipo de producto ' || p_nombre || ' ya existe');
                RETURN -1;
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE('Error al insertar' || SQLCODE);
                RETURN SQLCODE;
        END;
    END;

  FUNCTION insertar_producto (p_id_farmaceutica NUMBER, p_id_tipo_producto NUMBER, p_nombre VARCHAR2, p_descripcion VARCHAR2, p_precio NUMBER, p_cantidad_stock NUMBER) RETURN NUMBER AS
    respuesta NUMBER;
    BEGIN
        BEGIN
            INSERT INTO producto (ID_Farmaceutica, ID_tipo_producto, Nombre, Descripcion, Precio, Cantidad_stock)
              VALUES (p_id_farmaceutica, p_id_tipo_producto, p_nombre, p_descripcion, p_precio, p_cantidad_stock);

            respuesta := 1;
            --COMMIT;

            DBMS_OUTPUT.Put_Line('Se ha insertado ' || p_nombre);
            
            RETURN respuesta;
        
        EXCEPTION
            --retorna el código de error
            WHEN DUP_VAL_ON_INDEX THEN
                DBMS_OUTPUT.Put_Line('El producto ' || p_nombre || ' ya existe');
                RETURN -1;
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE('Error al insertar' || SQLCODE);
                RETURN SQLCODE;
        END;
    END;

    FUNCTION insertar_farmaceutica (p_nombre IN VARCHAR2, p_telefono IN VARCHAR2, p_correo IN VARCHAR2) RETURN NUMBER AS
    respuesta NUMBER;
    BEGIN
        BEGIN
            INSERT INTO farmaceutica (nombre, telefono, correo_electronico)
              VALUES (p_nombre, p_telefono, p_correo);

            respuesta := 1;
            --COMMIT;

            DBMS_OUTPUT.Put_Line('Se ha insertado ' || p_nombre);
            
            RETURN respuesta;
        
        EXCEPTION
            --retorna el código de error
            WHEN DUP_VAL_ON_INDEX THEN
                DBMS_OUTPUT.Put_Line('Ya existe una farmaceutica con al menos uno de los datos ingresados.');
                DBMS_OUTPUT.Put_Line(p_nombre || '---' || p_telefono || '---' || p_correo);
                RETURN -1;
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE('Error al insertar' || SQLCODE);
                RETURN SQLCODE;
        END;
    END;

    FUNCTION insertar_venta (p_fecha_emision IN DATE, p_id_cliente IN NUMBER, p_id_empleado IN NUMBER, p_total_venta IN NUMBER) RETURN NUMBER AS
    respuesta NUMBER;
    BEGIN
        BEGIN
            INSERT INTO venta (fecha_emision, id_cliente, id_empleado, total_venta)
              VALUES (p_fecha_emision, p_id_cliente, p_id_empleado, p_total_venta);

            respuesta := 1;
            --COMMIT;

            DBMS_OUTPUT.Put_Line('Se ha insertado la venta');
            
            RETURN respuesta;
        
        EXCEPTION
            --retorna el código de error
            WHEN DUP_VAL_ON_INDEX THEN
                DBMS_OUTPUT.Put_Line('La venta no se ha podido insertar');
                RETURN -1;
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE('Error al insertar' || SQLCODE);
                RETURN SQLCODE;
        END;
    END;

    FUNCTION insertar_detalle_venta (p_id_venta IN NUMBER, p_id_producto IN NUMBER, p_cantidad IN NUMBER, precio_unitario IN NUMBER) RETURN NUMBER AS
    respuesta NUMBER;
    BEGIN
        BEGIN
            INSERT INTO detalle_venta (id_venta, id_producto, cantidad, precio_unitario)
              VALUES (p_id_venta, p_id_producto, p_cantidad, precio_unitario);

            respuesta := 1;
            --COMMIT;

            DBMS_OUTPUT.Put_Line('Se ha insertado el detalle de la venta');
            
            RETURN respuesta;
        
        EXCEPTION
            --retorna el código de error
            WHEN DUP_VAL_ON_INDEX THEN
                DBMS_OUTPUT.Put_Line('El detalle de la venta no se ha podido insertar');
                RETURN -1;
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE('Error al insertar' || SQLCODE);
                RETURN SQLCODE;
        END;
    END;

    FUNCTION insertar_cliente (p_nombre IN VARCHAR2, p_apellido IN VARCHAR2, p_telefono IN VARCHAR2, p_correo_electronico IN VARCHAR2, p_fecha_nacimiento IN DATE, p_genero IN VARCHAR2) RETURN NUMBER AS
    respuesta NUMBER;
    BEGIN
        BEGIN
            INSERT INTO cliente (nombre, apellido, telefono, correo_electronico, fecha_nacimiento, genero)
              VALUES (p_nombre, p_apellido, p_telefono, p_correo_electronico, p_fecha_nacimiento, p_genero);

            respuesta := 1;
            --COMMIT;

            DBMS_OUTPUT.Put_Line('Se ha insertado el cliente');
            
            RETURN respuesta;
        
        EXCEPTION
            --retorna el código de error
            WHEN DUP_VAL_ON_INDEX THEN
                DBMS_OUTPUT.Put_Line('El cliente no se ha podido insertar');
                RETURN -1;
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE('Error al insertar' || SQLCODE);
                RETURN SQLCODE;
        END;
    END;

    FUNCTION insertar_empleado (p_id_cargo IN NUMBER, p_nombre IN VARCHAR2, p_apellido IN VARCHAR2, p_salario IN NUMBER, p_fecha_contratacion IN DATE, p_clave IN VARCHAR2) RETURN NUMBER AS
    respuesta NUMBER;
    BEGIN
        BEGIN
            INSERT INTO empleado (id_cargo, nombre, apellido, salario, fecha_contratacion, clave)
              VALUES (p_id_cargo, p_nombre, p_apellido, p_salario, p_fecha_contratacion, p_clave);

            respuesta := 1;
            --COMMIT;

            DBMS_OUTPUT.Put_Line('Se ha insertado el empleado');
            
            RETURN respuesta;
        
        EXCEPTION
            --retorna el código de error
            WHEN DUP_VAL_ON_INDEX THEN
                DBMS_OUTPUT.Put_Line('El empleado no se ha podido insertar');
                RETURN -1;
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE('Error al insertar' || SQLCODE);
                RETURN SQLCODE;
        END;
    END;

    FUNCTION insertar_cargo_empleado (p_nombre IN VARCHAR2) RETURN NUMBER AS
    respuesta NUMBER;
    BEGIN
        BEGIN
            INSERT INTO cargo_empleado (nombre)
              VALUES (p_nombre);

            respuesta := 1;
            --COMMIT;

            DBMS_OUTPUT.Put_Line('Se ha insertado el cargo');
            
            RETURN respuesta;
        
        EXCEPTION
            --retorna el código de error
            WHEN DUP_VAL_ON_INDEX THEN
                DBMS_OUTPUT.Put_Line('El cargo no se ha podido insertar');
                RETURN -1;
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE('Error al insertar' || SQLCODE);
                RETURN SQLCODE;
        END;
    END;
END farmacia_insertar;
/





