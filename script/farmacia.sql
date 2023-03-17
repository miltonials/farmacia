/*
DROP TABLE DETALLE_VENTA;
DROP TABLE VENTA;
DROP TABLE EMPLEADO;
DROP TABLE CARGO_EMPLEADO;
DROP TABLE CLIENTE;
DROP TABLE PRODUCTO;
DROP TABLE TIPO_PRODUCTO;
DROP TABLE PROVEEDOR;
*/

CREATE TABLE Proveedor (
    ID_Proveedor NUMBER GENERATED ALWAYS AS IDENTITY,
    Nombre VARCHAR2(50) CONSTRAINT NN_Nombre_Proveedor NOT NULL,
    Telefono VARCHAR2(20) CONSTRAINT NN_Telefono_Proveedor NOT NULL,
    Correo_electronico VARCHAR2(100) CONSTRAINT NN_CorreoElectronico_Proveedor NOT NULL,
    
    CONSTRAINT PK_Proveedor PRIMARY KEY (ID_Proveedor)
);

CREATE TABLE Tipo_producto (
    ID_tipo_producto NUMBER GENERATED ALWAYS AS IDENTITY,
    nombre VARCHAR2(100)
        CONSTRAINT UNI_Nombre_TipoProducto UNIQUE
        CONSTRAINT NN_Nombre_TipoProducto NOT NULL,
    
    CONSTRAINT PK_tipo_producto PRIMARY KEY (ID_tipo_producto)
);

-- Creaci�n de la tabla Producto
CREATE TABLE Producto (
    ID_Producto NUMBER GENERATED ALWAYS AS IDENTITY,
    ID_Proveedor NUMBER CONSTRAINT NN_IDProveedor_Producto NOT NULL,
    ID_tipo_producto NUMBER CONSTRAINT NN_IDTipoProducto_Producto NOT NULL,
    Nombre VARCHAR2(50) CONSTRAINT NN_Nombre_Producto NOT NULL,
    Descripcion VARCHAR2(500) CONSTRAINT NN_Descripcion_Producto NOT NULL,
    Precio NUMBER(10,2) CONSTRAINT NN_Precio_Producto NOT NULL,
    Cantidad_stock NUMBER CONSTRAINT NN_CantidadStock_Producto NOT NULL,
    Fecha_caducidad DATE CONSTRAINT NN_FechaCaducidad_Producto NOT NULL,
    
    CONSTRAINT PK_Producto PRIMARY KEY (ID_Producto),
    CONSTRAINT FK_Producto_Proveedor FOREIGN KEY (ID_Proveedor)
        REFERENCES proveedor (ID_Proveedor)
        ON DELETE CASCADE,
        
    CONSTRAINT FK_Producto_tipoProducto FOREIGN KEY (ID_tipo_producto)
        REFERENCES tipo_producto (ID_tipo_producto)
        ON DELETE CASCADE
);

-- Creaci�n de la tabla Cliente
CREATE TABLE Cliente (
    ID_Cliente NUMBER GENERATED ALWAYS AS IDENTITY,
    Nombre VARCHAR2(50) CONSTRAINT NN_Nombre_Cliente NOT NULL,
    Apellido VARCHAR2(50) CONSTRAINT NN_Apellido_Cliente NOT NULL,
    Telefono VARCHAR2(20),
    Correo_electronico VARCHAR2(100),
    Fecha_nacimiento DATE,
    Genero VARCHAR2(10),
    
    CONSTRAINT PK_Cliente PRIMARY KEY (ID_Cliente)
);

CREATE TABLE Cargo_empleado (
    ID_Cargo NUMBER GENERATED ALWAYS AS IDENTITY,
    Nombre VARCHAR2(50)
        CONSTRAINT UNI_Nombre_Cargo UNIQUE
        CONSTRAINT NN_Nombre_Cargo NOT NULL,
    
    CONSTRAINT PK_Cargo_empleado PRIMARY KEY (ID_Cargo)
);

-- Creaci�n de la tabla Empleado
CREATE TABLE Empleado (
    ID_Empleado NUMBER GENERATED ALWAYS AS IDENTITY,
    ID_Cargo NUMBER CONSTRAINT NN_IDCargo_Empleado NOT NULL,
    Nombre VARCHAR2(50) CONSTRAINT NN_Nombre_Empleado NOT NULL,
    Apellido VARCHAR2(50) CONSTRAINT NN_Apellido_Empleado NOT NULL,
    Salario NUMBER(10,2) CONSTRAINT NN_Salario_Empleado NOT NULL,
    Fecha_contratacion DATE CONSTRAINT NN_FechaContratacion_Empleado NOT NULL,
    Clave VARCHAR2(64) CONSTRAINT NN_Clave_Empleado NOT NULL,
    
    CONSTRAINT PK_Empleado PRIMARY KEY (ID_Empleado),
    CONSTRAINT FK_Cargo_CargoEmpleado FOREIGN KEY (ID_Cargo)
        REFERENCES Cargo_empleado(ID_Cargo)
        ON DELETE CASCADE
);

-- Creaci�n de la tabla Venta
CREATE TABLE Venta (
    ID_venta NUMBER GENERATED ALWAYS AS IDENTITY,
    Fecha_emision DATE CONSTRAINT NN_FechaEmision_Venta NOT NULL,
    ID_Cliente NUMBER CONSTRAINT NN_IDCliente_Venta NOT NULL,
    ID_Empleado NUMBER CONSTRAINT NN_IDEmpleado_Venta NOT NULL,
    Total_venta NUMBER(10,2) CONSTRAINT NN_TotalVenta_Venta NOT NULL,
    
    CONSTRAINT PK_Venta PRIMARY KEY (ID_venta),
    CONSTRAINT FK_Venta_Cliente FOREIGN KEY (ID_Cliente)
        REFERENCES cliente (ID_Cliente)
        ON DELETE CASCADE,
        
    CONSTRAINT FK_Venta_Empleado FOREIGN KEY (ID_Empleado)
        REFERENCES empleado (ID_Empleado)
        ON DELETE CASCADE
);

-- Creaci�n de la tabla Detalle_Factura
CREATE TABLE Detalle_venta (
    ID_venta NUMBER CONSTRAINT NN_IDVenta_DetalleVenta NOT NULL,
    ID_Producto NUMBER CONSTRAINT NN_IDProducto_DetalleVenta NOT NULL,
    Cantidad NUMBER CONSTRAINT NN_Cantidad_DetalleVenta NOT NULL,
    Precio_unitario NUMBER(10,2) CONSTRAINT NN_PrecioUnitario_DetalleVenta NOT NULL,
    
    CONSTRAINT PK_DetalleVenta PRIMARY KEY (ID_Venta, ID_Producto),
    
    CONSTRAINT FK_DetalleVenta_Venta FOREIGN KEY (ID_Venta)
        REFERENCES Venta (ID_Venta)
        ON DELETE CASCADE,
    
    CONSTRAINT FK_DetalleVenta_Producto FOREIGN KEY (ID_Producto)
        REFERENCES producto (ID_Producto)
        ON DELETE CASCADE
);


--Procedimientos-Kevin
CREATE OR REPLACE PROCEDURE CrearTipoProducto (
    nombre_tipo_producto IN VARCHAR2
)
IS
    BEGIN
        INSERT INTO tipo_producto (nombre)
        VALUES (nombre_tipo_producto);
    END;

CREATE OR REPLACE PROCEDURE ListarTipoProducto
IS
    nombre_tipo_producto tipo_producto.nombre%TYPE;
    CURSOR c_tipo_producto IS
        SELECT nombre FROM tipo_producto;
    BEGIN
        OPEN c_tipo_producto;
        LOOP
            FETCH c_tipo_producto INTO nombre_tipo_producto;
            EXIT WHEN c_tipo_producto%NOTFOUND;
            DBMS_OUTPUT.PUT_LINE(nombre_tipo_producto);
        END LOOP;
        CLOSE c_tipo_producto;
    END;


CREATE OR REPLACE PROCEDURE ModificarTipoProducto (
    id_tipo_producto IN NUMBER,
    nombre_tipo_producto IN VARCHAR2
)
IS
    BEGIN
        UPDATE tipo_producto
        SET nombre = nombre_tipo_producto
        WHERE id_tipo_producto = id_tipo_producto;
    END;
    
CREATE OR REPLACE PROCEDURE EliminarTipoProducto (
    id_tipo_producto IN NUMBER
)
IS
    BEGIN
        DELETE FROM tipo_producto
        WHERE id_tipo_producto = id_tipo_producto;
    END;
-- ********************************************************
-- ********************************************************
CREATE OR REPLACE PROCEDURE CrearVenta (
    fecha_emision IN DATE,
    id_cliente IN NUMBER,
    id_empleado IN NUMBER,
    total_venta IN NUMBER
)
IS
    BEGIN
        INSERT INTO venta (fecha_emision, id_cliente, id_empleado, total_venta)
        VALUES (fecha_emision, id_cliente, id_empleado, total_venta);
    END;
    
CREATE OR REPLACE PROCEDURE ListarVentas
IS
    id_venta venta.id_venta%TYPE;
    fecha_emision venta.fecha_emision%TYPE;
    id_cliente venta.id_cliente%TYPE;
    id_empleado venta.id_empleado%TYPE;
    total_venta venta.total_venta%TYPE;
    CURSOR c_venta IS
        SELECT * FROM venta;
    BEGIN
        OPEN c_venta;
        LOOP
            FETCH c_venta INTO id_venta, fecha_emision, id_cliente, id_empleado, total_venta;
            EXIT WHEN c_venta%NOTFOUND;
            DBMS_OUTPUT.PUT_LINE(id_venta || ' ' || fecha_emision || ' ' || id_cliente || ' ' || id_empleado || ' ' || total_venta);
        END LOOP;
        CLOSE c_venta;
    END;
    
CREATE OR REPLACE PROCEDURE ModificarVenta (
    id_venta IN NUMBER,
    fecha_emision IN DATE,
    id_cliente IN NUMBER,
    id_empleado IN NUMBER,
    total_venta IN NUMBER
)
IS
    BEGIN
        UPDATE venta
        SET fecha_emision = fecha_emision,
            id_cliente = id_cliente,
            id_empleado = id_empleado,
            total_venta = total_venta
        WHERE id_venta = id_venta;
    END;

CREATE OR REPLACE PROCEDURE EliminarVenta (
    id_venta IN NUMBER
)
IS
    BEGIN
        DELETE FROM venta
        WHERE id_venta = id_venta;
    END;
-- ********************************************************
-- ********************************************************
CREATE OR REPLACE PROCEDURE CrearEmpleado (
    id_cargo IN NUMBER,
    nombre IN VARCHAR2,
    apellido IN VARCHAR2,
    salario IN NUMBER,
    fecha_contratacion IN DATE,
    clave IN VARCHAR2
)
IS
    BEGIN
        INSERT INTO empleado (id_cargo, nombre, apellido, salario, fecha_contratacion, clave)
        VALUES (id_cargo, nombre, apellido, salario, fecha_contratacion, clave);
    END;

CREATE OR REPLACE PROCEDURE ListarEmpleado
IS
    id_empleado empleado.id_empleado%TYPE;
    id_cargo empleado.id_cargo%TYPE;
    nombre empleado.nombre%TYPE;
    apellido empleado.apellido%TYPE;
    salario empleado.salario%TYPE;
    fecha_contratacion empleado.fecha_contratacion%TYPE;
    clave empleado.clave%TYPE;
    CURSOR c_empleado IS
        SELECT * FROM empleado;
    BEGIN
        OPEN c_empleado;
        LOOP
            FETCH c_empleado INTO id_empleado, id_cargo, nombre, apellido, salario, fecha_contratacion, clave;
            EXIT WHEN c_empleado%NOTFOUND;
            DBMS_OUTPUT.PUT_LINE(id_empleado || ' ' || id_cargo || ' ' || nombre || ' ' || apellido || ' ' || salario || ' ' || fecha_contratacion || ' ' || clave);
        END LOOP;
        CLOSE c_empleado;
    END;

CREATE OR REPLACE PROCEDURE ModificarEmpleado (
    id_empleado IN NUMBER,
    id_cargo IN NUMBER,
    nombre IN VARCHAR2,
    apellido IN VARCHAR2,
    salario IN NUMBER,
    fecha_contratacion IN DATE,
    clave IN VARCHAR2
)
IS
    BEGIN
        UPDATE empleado
        SET id_cargo = id_cargo,
            nombre = nombre,
            apellido = apellido,
            salario = salario,
            fecha_contratacion = fecha_contratacion,
            clave = clave
        WHERE id_empleado = id_empleado;
    END;

CREATE OR REPLACE PROCEDURE EliminarEmpleado (
    id_empleado IN NUMBER
)
IS
    BEGIN
        DELETE FROM empleado
        WHERE id_empleado = id_empleado;
    END;
-- ********************************************************
-- ********************************************************
CREATE OR REPLACE VIEW total_ventas AS
SELECT TO_CHAR(Fecha_emision, 'MM') AS Mes,  SUM(Total_venta) AS Monto
FROM Venta
GROUP BY TO_CHAR(Fecha_emision, 'MM');
