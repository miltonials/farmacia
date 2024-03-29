--SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'EMPLEADO';
/*
DROP TABLE DETALLE_VENTA;
DROP TABLE VENTA;
DROP TABLE BITACORA;
DROP TABLE EMPLEADO;
DROP TABLE CARGO_EMPLEADO;
DROP TABLE CLIENTE;
DROP TABLE PRODUCTO;
DROP TABLE TIPO_PRODUCTO;
DROP TABLE FARMACEUTICA;
*/



CREATE TABLE Farmaceutica (
    ID_Farmaceutica NUMBER GENERATED ALWAYS AS IDENTITY,
    Nombre VARCHAR2(50) CONSTRAINT NN_Nombre_Farmaceutica NOT NULL
        CONSTRAINT UNI_Nombre_Farmaceutica UNIQUE,
    Telefono VARCHAR2(20) CONSTRAINT NN_Telefono_Farmaceutica NOT NULL
        CONSTRAINT UNI_Telefono_Farmaceutica UNIQUE,
    Correo_electronico VARCHAR2(100) CONSTRAINT NN_CorreoElectronico_Farmaceutica NOT NULL
        CONSTRAINT UNI_CorreoElectronico_Farmaceutica UNIQUE,
    
    CONSTRAINT PK_Farmaceutica PRIMARY KEY (ID_Farmaceutica)
);

CREATE TABLE Tipo_producto (
    ID_tipo_producto NUMBER GENERATED ALWAYS AS IDENTITY,
    nombre VARCHAR2(100)
        CONSTRAINT UNI_Nombre_TipoProducto UNIQUE
        CONSTRAINT NN_Nombre_TipoProducto NOT NULL,
    
    CONSTRAINT PK_tipo_producto PRIMARY KEY (ID_tipo_producto)
);

-- Creacion de la tabla Producto
CREATE TABLE Producto (
    ID_Producto NUMBER GENERATED ALWAYS AS IDENTITY,
    ID_Farmaceutica NUMBER CONSTRAINT NN_IDFarmaceutica_Producto NOT NULL,
    ID_tipo_producto NUMBER CONSTRAINT NN_IDTipoProducto_Producto NOT NULL,
    Nombre VARCHAR2(50) CONSTRAINT NN_Nombre_Producto NOT NULL,
    Descripcion VARCHAR2(500) CONSTRAINT NN_Descripcion_Producto NOT NULL,
    Precio NUMBER(10,2) CONSTRAINT NN_Precio_Producto NOT NULL,
    Cantidad_stock NUMBER CONSTRAINT NN_CantidadStock_Producto NOT NULL,
    --Fecha_caducidad DATE CONSTRAINT NN_FechaCaducidad_Producto NOT NULL,
    
    CONSTRAINT PK_Producto PRIMARY KEY (ID_Producto),
    CONSTRAINT FK_Producto_Farmaceutica FOREIGN KEY (ID_Farmaceutica)
        REFERENCES Farmaceutica (ID_Farmaceutica)
        ON DELETE CASCADE,
        
    CONSTRAINT FK_Producto_tipoProducto FOREIGN KEY (ID_tipo_producto)
        REFERENCES tipo_producto (ID_tipo_producto)
        ON DELETE CASCADE
);

-- Creacion de la tabla Cliente
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

-- Creacion de la tabla Empleado
CREATE TABLE Empleado (
    ID_Empleado NUMBER GENERATED ALWAYS AS IDENTITY,
    ID_Cargo NUMBER CONSTRAINT NN_IDCargo_Empleado NOT NULL,
    Cedula VARCHAR2(50) CONSTRAINT NN_Cedula_empleado NOT NULL
        CONSTRAINT UNI_Cedula_Empleado UNIQUE,
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

-- Creacion de la tabla Venta
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

-- Creacion de la tabla Detalle_Factura
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

-- Creacion de la tabla Bitacora que tiene fecha, hora, empleado y valor modificado de la tabla empleado
CREATE TABLE bitacora (
    ID_Bitacora NUMBER GENERATED ALWAYS AS IDENTITY,
    Fecha DATE CONSTRAINT NN_Fecha_Bitacora NOT NULL,
    Hora VARCHAR2(100) CONSTRAINT NN_Hora_Bitacora NOT NULL,
    ID_Empleado NUMBER CONSTRAINT NN_IDEmpleado_Bitacora NOT NULL,
    ID_Producto NUMBER CONSTRAINT NN_IDProducto_Bitacora NOT NULL,
    Columna_modificada VARCHAR2(100) CONSTRAINT NN_ValorModificado_Bitacora NOT NULL,
    Valor_anterior VARCHAR(100) CONSTRAINT NN_ValorAnterior NOT NULL,
    Nuevo_Valor VARCHAR(100) CONSTRAINT NN_NuevoValor NOT NULL,
    
    CONSTRAINT PK_Bitacora PRIMARY KEY (ID_Bitacora),
    CONSTRAINT FK_Bitacora_Producto FOREIGN KEY (ID_Producto)
        REFERENCES Producto (ID_Producto)
        ON DELETE CASCADE
);
delete from farmaceutica;
delete from tipo_producto;
delete from producto;

select * from farmaceutica;
select * from tipo_producto;
select * from producto;
select * from bitacora;

insert into farmaceutica (nombre, telefono, correo_electronico) values ('a', 'b', 'c23fa@gmail.com');
insert into tipo_producto (nombre) values ('a');
insert into producto (id_farmaceutica, id_tipo_producto, nombre, descripcion, precio, cantidad_stock) values (1,1,'a','b', 3, 10);

-- prueba del trigger
UPDATE producto
SET cantidad_stock = 1
WHERE id_producto = 1;
CREATE OR REPLACE TRIGGER BITACORA_PRODUCTO
AFTER UPDATE ON PRODUCTO
FOR EACH ROW
DECLARE
    V_COLUMNA_MODIFICADA VARCHAR2(100);
BEGIN
    IF UPDATING('NOMBRE') THEN
        V_COLUMNA_MODIFICADA := 'Nombre';
        INSERT INTO BITACORA (FECHA, HORA, ID_EMPLEADO, ID_PRODUCTO, COLUMNA_MODIFICADA, VALOR_ANTERIOR, NUEVO_VALOR)
        VALUES (SYSDATE, TO_CHAR(SYSDATE, 'HH24:MI:SS'), 1, :NEW.ID_PRODUCTO, V_COLUMNA_MODIFICADA, :OLD.NOMBRE, :NEW.NOMBRE);
    ELSIF UPDATING('DESCRIPCION') THEN
        V_COLUMNA_MODIFICADA := 'Descripcion';
        INSERT INTO BITACORA (FECHA, HORA, ID_EMPLEADO, ID_PRODUCTO, COLUMNA_MODIFICADA, VALOR_ANTERIOR, NUEVO_VALOR)
        VALUES (SYSDATE, TO_CHAR(SYSDATE, 'HH24:MI:SS'), 1, :NEW.ID_PRODUCTO, V_COLUMNA_MODIFICADA, :OLD.DESCRIPCION, :NEW.DESCRIPCION);
    ELSIF UPDATING('PRECIO') THEN
        V_COLUMNA_MODIFICADA := 'Precio';
        INSERT INTO BITACORA (FECHA, HORA, ID_EMPLEADO, ID_PRODUCTO, COLUMNA_MODIFICADA, VALOR_ANTERIOR, NUEVO_VALOR)
        VALUES (SYSDATE, TO_CHAR(SYSDATE, 'HH24:MI:SS'), 1, :NEW.ID_PRODUCTO, V_COLUMNA_MODIFICADA, :OLD.PRECIO, :NEW.PRECIO);
    ELSIF UPDATING('CANTIDAD_STOCK') THEN
        V_COLUMNA_MODIFICADA := 'Cantidad_stock';
        INSERT INTO BITACORA (FECHA, HORA, ID_EMPLEADO, ID_PRODUCTO, COLUMNA_MODIFICADA, VALOR_ANTERIOR, NUEVO_VALOR)
        VALUES (SYSDATE, TO_CHAR(SYSDATE, 'HH24:MI:SS'), 1, :NEW.ID_PRODUCTO, V_COLUMNA_MODIFICADA, :OLD.CANTIDAD_STOCK, :NEW.CANTIDAD_STOCK);
    END IF;
END;
