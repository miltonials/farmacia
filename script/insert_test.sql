INSERT INTO Farmaceutica (Nombre, Telefono, Correo_electronico) VALUES ('Farmacia El Camino', '555-1234', 'farmaciaelcamino@gmail.com');
INSERT INTO Farmaceutica (Nombre, Telefono, Correo_electronico) VALUES ('Farmacia San Juan', '555-5678', 'farmaciasanjuan@hotmail.com');
INSERT INTO Farmaceutica (Nombre, Telefono, Correo_electronico) VALUES ('Farmacia La Esperanza', '555-9012', 'farmacialaesperanza@yahoo.com');
INSERT INTO Farmaceutica (Nombre, Telefono, Correo_electronico) VALUES ('Farmacia La Salud', '555-3456', 'farmacialasalud@outlook.com');
INSERT INTO Farmaceutica (Nombre, Telefono, Correo_electronico) VALUES ('Farmacia La Fe', '555-7890', 'farmacialafe@gmail.com');
delete from farmaceutica;
select * from farmaceutica;

exec farmacia_eliminar.eliminar_farmaceutica(2);


SELECT CONSTRAINT_NAME, TABLE_NAME
FROM USER_CONSTRAINTS
WHERE R_CONSTRAINT_NAME IN (
    SELECT CONSTRAINT_NAME
    FROM USER_CONSTRAINTS
    WHERE TABLE_NAME = 'EMPLEADO' AND CONSTRAINT_TYPE = 'P'
);
