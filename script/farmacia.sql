--SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'CARGO_EMPLEADO';
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

-- Creaci�n de la tabla Producto
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

-- Creaci�n de la tabla Bitacora que tiene fecha, hora, empleado y valor modificado de la tabla empleado
CREATE TABLE bitacora (
    ID_Bitacora NUMBER GENERATED ALWAYS AS IDENTITY,
    Fecha DATE CONSTRAINT NN_Fecha_Bitacora NOT NULL,
    Hora DATE CONSTRAINT NN_Hora_Bitacora NOT NULL,
    ID_Empleado NUMBER CONSTRAINT NN_IDEmpleado_Bitacora NOT NULL,
    Valor_modificado VARCHAR2(100) CONSTRAINT NN_ValorModificado_Bitacora NOT NULL,
    
    CONSTRAINT PK_Bitacora PRIMARY KEY (ID_Bitacora),
    CONSTRAINT FK_Bitacora_Empleado FOREIGN KEY (ID_Empleado)
        REFERENCES empleado (ID_Empleado)
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


-- ********************************************************
-- ********************************************************
CREATE OR REPLACE VIEW total_ventas AS
SELECT TO_CHAR(Fecha_emision, 'MM') AS Mes,  SUM(Total_venta) AS Monto
FROM Venta
GROUP BY TO_CHAR(Fecha_emision, 'MM');

/******************************
*  Procedimiento Andy *
******************************/

-- Procedimiento para crear un Farmaceutico(farmacia) usando un trigger
CREATE OR REPLACE PROCEDURE CrearFarmaceutica (
    id_farmaceutica IN NUMBER,
    nombre IN VARCHAR2,
    Telefono IN VARCHAR2
)
IS
    BEGIN
        INSERT INTO Farmaceutica (id_farmaceutica, nombre, Telefono)
        VALUES (id_farmaceutica, nombre, Telefono);
    END;

-- Procedimiento para modificar un farmaceutico
CREATE OR REPLACE PROCEDURE ModificarFarmaceutica (
    id_farmaceutico IN NUMBER,
    nombre IN VARCHAR2,
    Telefono IN VARCHAR2
)
IS
    BEGIN
        UPDATE Farmaceutica
        SET nombre = nombre,
            Telefono = Telefono
        WHERE id_farmaceutica = id_farmaceutica;
    END;


-- Procedimiento para modificar un cliente
CREATE OR REPLACE PROCEDURE ModificarCliente (
    id_cliente IN NUMBER,
    nombre IN VARCHAR2,
    apellido IN VARCHAR2,
    Telefono IN VARCHAR2,
    Correo_electronico IN VARCHAR2,
    Fecha_nacimiento IN VARCHAR2
    Genero IN VARCHAR2
)
IS
    BEGIN
        UPDATE cliente
        SET nombre = nombre,
            apellido = apellido,
            Telefono = Telefono,
            Correo_electronico = Correo_electronico,
            Fecha_nacimiento = Fecha_nacimiento,
            Genero = Genero
        WHERE id_cliente = id_cliente;
    END;
/

