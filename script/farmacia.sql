-- Creación de la tabla Proveedor
/*
DROP TABLE DETALLE_FACTURA;
DROP TABLE FACTURA;
DROP TABLE EMPLEADO;
DROP TABLE CLIENTE;
DROP TABLE PRODUCTO;
DROP TABLE PROVEEDOR;
*/

CREATE TABLE proveedor (
    ID_Proveedor NUMBER GENERATED ALWAYS AS IDENTITY,
    Nombre VARCHAR2(50) NOT NULL,
    Telefono VARCHAR2(20),
    Correo_electronico VARCHAR2(100),
    
    CONSTRAINT PK_Proveedor PRIMARY KEY (ID_Proveedor)
);

-- Creación de la tabla Producto
CREATE TABLE producto (
    ID_Producto NUMBER GENERATED ALWAYS AS IDENTITY,
    Nombre VARCHAR2(50) NOT NULL,
    Descripcion VARCHAR2(500),
    Tipo VARCHAR2(50),
    Precio NUMBER(10,2),
    Cantidad_stock NUMBER,
    Fecha_caducidad DATE,
    ID_Proveedor NUMBER,
    
    CONSTRAINT PK_Producto PRIMARY KEY (ID_Producto),
    CONSTRAINT FK_Producto_Proveedor FOREIGN KEY (ID_Proveedor)
        REFERENCES proveedor (ID_Proveedor)
        ON DELETE CASCADE
);

-- Creación de la tabla Cliente
CREATE TABLE cliente (
    ID_Cliente NUMBER GENERATED ALWAYS AS IDENTITY,
    Nombre VARCHAR2(50) NOT NULL,
    Telefono VARCHAR2(20),
    Correo_electronico VARCHAR2(100),
    Fecha_nacimiento DATE,
    Genero VARCHAR2(10),
    
    CONSTRAINT PK_Cliente PRIMARY KEY (ID_Cliente)
);

-- Creación de la tabla Empleado
CREATE TABLE empleado (
    ID_Empleado NUMBER GENERATED ALWAYS AS IDENTITY,
    Nombre VARCHAR2(50) NOT NULL,
    Cargo VARCHAR2(50),
    Salario NUMBER(10,2),
    Fecha_contratacion DATE,
    
    CONSTRAINT PK_Empleado PRIMARY KEY (ID_Empleado)
);

-- Creación de la tabla Factura
CREATE TABLE factura (
    ID_Factura NUMBER GENERATED ALWAYS AS IDENTITY,
    Fecha_emision DATE,
    ID_Cliente NUMBER,
    ID_Empleado NUMBER,
    Total_factura NUMBER(10,2),
    
    CONSTRAINT PK_Factura PRIMARY KEY (ID_Factura),
    CONSTRAINT FK_Factura_Cliente FOREIGN KEY (ID_Cliente)
        REFERENCES cliente (ID_Cliente)
        ON DELETE CASCADE,
        
    CONSTRAINT FK_Factura_Empleado FOREIGN KEY (ID_Empleado)
        REFERENCES empleado (ID_Empleado)
        ON DELETE CASCADE
);

-- Creación de la tabla Detalle_Factura
CREATE TABLE detalle_factura (
    ID_Factura NUMBER,
    ID_Producto NUMBER,
    Cantidad NUMBER,
    Precio_unitario NUMBER(10,2),
    
    CONSTRAINT PK_DetalleFactura PRIMARY KEY (ID_Factura, ID_Producto),
    
    CONSTRAINT FK_DetalleFactura_Factura FOREIGN KEY (ID_Factura)
        REFERENCES factura (ID_Factura)
        ON DELETE CASCADE,
    
    CONSTRAINT FK_DetalleFactura_Producto FOREIGN KEY (ID_Producto)
        REFERENCES producto (ID_Producto)
        ON DELETE CASCADE
);