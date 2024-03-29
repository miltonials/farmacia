CREATE OR REPLACE PACKAGE farmacia_insertar AS
  FUNCTION insertar_tipo_producto (p_nombre IN VARCHAR2) RETURN NUMBER;
  FUNCTION insertar_producto (p_id_farmaceutica NUMBER, p_id_tipo_producto NUMBER, p_nombre VARCHAR2, p_descripcion VARCHAR2, p_precio NUMBER, p_cantidad_stock NUMBER) RETURN NUMBER;
  FUNCTION insertar_farmaceutica (p_nombre IN VARCHAR2, p_telefono IN VARCHAR2, p_correo IN VARCHAR2) RETURN NUMBER;
  FUNCTION insertar_venta (p_fecha_emision IN DATE, p_id_cliente IN NUMBER, p_id_empleado IN NUMBER, p_total_venta IN NUMBER) RETURN NUMBER;
  FUNCTION insertar_detalle_venta (p_id_venta IN NUMBER, p_id_producto IN NUMBER, p_cantidad IN NUMBER) RETURN NUMBER;
  FUNCTION insertar_cliente (p_nombre IN VARCHAR2, p_apellido IN VARCHAR2, p_telefono IN VARCHAR2, p_correo_electronico IN VARCHAR2, p_fecha_nacimiento IN DATE, p_genero IN VARCHAR2) RETURN NUMBER;
  FUNCTION insertar_empleado (p_cedula IN VARCHAR2, p_id_cargo IN NUMBER, p_nombre IN VARCHAR2, p_apellido IN VARCHAR2, p_salario IN NUMBER, p_fecha_contratacion IN DATE, p_clave IN VARCHAR2) RETURN NUMBER;
  FUNCTION insertar_cargo_empleado (p_nombre IN VARCHAR2) RETURN NUMBER;
END farmacia_insertar;
/

CREATE OR REPLACE PACKAGE BODY farmacia_insertar AS
  FUNCTION insertar_tipo_producto (p_nombre IN VARCHAR2) RETURN NUMBER
IS PRAGMA AUTONOMOUS_TRANSACTION;
    respuesta NUMBER;
    BEGIN
        BEGIN
            EXECUTE IMMEDIATE 'INSERT INTO tipo_producto (Nombre) VALUES (:1)'
                USING p_nombre;

            respuesta := 1;
            COMMIT;

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

  FUNCTION insertar_producto (p_id_farmaceutica NUMBER, p_id_tipo_producto NUMBER, p_nombre VARCHAR2, p_descripcion VARCHAR2, p_precio NUMBER, p_cantidad_stock NUMBER) RETURN NUMBER
IS PRAGMA AUTONOMOUS_TRANSACTION;
    respuesta NUMBER;
    BEGIN
        BEGIN           
            EXECUTE IMMEDIATE 'INSERT INTO producto (ID_Farmaceutica, ID_tipo_producto, Nombre, Descripcion, Precio, Cantidad_stock)
                                    VALUES (:1, :2, :3, :4, :5, :6)'
                USING p_id_farmaceutica, p_id_tipo_producto, p_nombre, p_descripcion, p_precio, p_cantidad_stock;

            respuesta := 1;
            COMMIT;

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

    FUNCTION insertar_farmaceutica (p_nombre IN VARCHAR2, p_telefono IN VARCHAR2, p_correo IN VARCHAR2) RETURN NUMBER
IS PRAGMA AUTONOMOUS_TRANSACTION;
    respuesta NUMBER;
    BEGIN
        BEGIN
            EXECUTE IMMEDIATE 'INSERT INTO farmaceutica (Nombre, Telefono, Correo_electronico)
                                    VALUES (:1, :2, :3)'
                USING p_nombre, p_telefono, p_correo;

            respuesta := 1;
            COMMIT;

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

    FUNCTION insertar_venta (p_fecha_emision IN DATE, p_id_cliente IN NUMBER, p_id_empleado IN NUMBER, p_total_venta IN NUMBER) RETURN NUMBER
IS PRAGMA AUTONOMOUS_TRANSACTION;
    respuesta NUMBER;
    BEGIN
        BEGIN
            EXECUTE IMMEDIATE 'INSERT INTO venta (Fecha_emision, ID_cliente, ID_empleado, Total_venta)
                                    VALUES (:1, :2, :3, :4)'
                USING p_fecha_emision, p_id_cliente, p_id_empleado, p_total_venta;

            respuesta := 1;
            COMMIT;

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

    FUNCTION insertar_detalle_venta (p_id_venta IN NUMBER, p_id_producto IN NUMBER, p_cantidad IN NUMBER) RETURN NUMBER
IS PRAGMA AUTONOMOUS_TRANSACTION;
    respuesta NUMBER;
    vprecio_unitario NUMBER;
    BEGIN
        BEGIN
            SELECT precio INTO vprecio_unitario
                FROM producto
                WHERE id_producto = p_id_producto;
                
            EXECUTE IMMEDIATE 'INSERT INTO detalle_venta (ID_venta, ID_producto, Cantidad, Precio_unitario)
                                    VALUES (:1, :2, :3, :4)'
                USING p_id_venta, p_id_producto, p_cantidad, vprecio_unitario;
                
            UPDATE VENTA
                SET total_venta = total_venta + vprecio_unitario * p_cantidad
                WHERE id_venta = p_id_venta;
            
            UPDATE PRODUCTO
                SET cantidad_stock = cantidad_stock - p_cantidad
                WHERE id_producto = p_id_producto;
                
            respuesta := 1;
            COMMIT;

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

    FUNCTION insertar_cliente (p_nombre IN VARCHAR2, p_apellido IN VARCHAR2, p_telefono IN VARCHAR2, p_correo_electronico IN VARCHAR2, p_fecha_nacimiento IN DATE, p_genero IN VARCHAR2) RETURN NUMBER
IS PRAGMA AUTONOMOUS_TRANSACTION;
    respuesta NUMBER;
    BEGIN
        BEGIN
            EXECUTE IMMEDIATE 'INSERT INTO cliente (Nombre, Apellido, Telefono, Correo_electronico, Fecha_nacimiento, Genero)
                                    VALUES (:1, :2, :3, :4, :5, :6)'
                USING p_nombre, p_apellido, p_telefono, p_correo_electronico, p_fecha_nacimiento, p_genero;

            respuesta := 1;
            COMMIT;

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

    FUNCTION insertar_empleado (p_cedula IN VARCHAR2, p_id_cargo IN NUMBER, p_nombre IN VARCHAR2, p_apellido IN VARCHAR2, p_salario IN NUMBER, p_fecha_contratacion IN DATE, p_clave IN VARCHAR2) RETURN NUMBER
IS PRAGMA AUTONOMOUS_TRANSACTION;
    respuesta NUMBER;
    BEGIN
        BEGIN
            EXECUTE IMMEDIATE 'INSERT INTO empleado (ID_cargo, Cedula, Nombre, Apellido, Salario, Fecha_contratacion, Clave)
                                    VALUES (:1, :2, :3, :4, :5, :6, :7)'
                USING p_id_cargo, p_cedula, p_nombre, p_apellido, p_salario, p_fecha_contratacion, p_clave;

            respuesta := 1;
            COMMIT;

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

    FUNCTION insertar_cargo_empleado (p_nombre IN VARCHAR2) RETURN NUMBER
IS PRAGMA AUTONOMOUS_TRANSACTION;
    respuesta NUMBER;
    BEGIN
        BEGIN
            EXECUTE IMMEDIATE 'INSERT INTO cargo_empleado (Nombre)
                                    VALUES (:1)'
                USING p_nombre;

            respuesta := 1;
            COMMIT;

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




