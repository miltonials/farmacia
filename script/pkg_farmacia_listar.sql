CREATE OR REPLACE VIEW productos_mas_vendidos AS
    SELECT p.nombre,
           SUM(df.cantidad) AS cantidad
    FROM producto p, detalle_venta df
        WHERE p.id_producto = df.id_producto
    GROUP BY p.nombre
    ORDER BY cantidad DESC;
    
-- Vista de monto total vendido por mes
CREATE VIEW monto_total_vendido_por_mes AS
    SELECT TO_CHAR(Fecha_emision, 'YYYY-MM') AS mes,
           SUM(Total_venta) AS monto_total_vendido
    FROM Venta
    GROUP BY TO_CHAR(Fecha_emision, 'YYYY-MM');

-- Vista de productos más vendidos por mes
CREATE VIEW productos_mas_vendidos_por_mes AS
  SELECT TO_CHAR(Fecha_emision, 'YYYY-MM') AS mes,
         Producto.Nombre AS nombre_producto,
         SUM(Cantidad) AS cantidad_vendida,
         SUM(Precio_unitario * Cantidad) AS monto_total_vendido
  FROM Venta
    JOIN Detalle_venta ON Venta.ID_venta = Detalle_venta.ID_venta
    JOIN Producto ON Detalle_venta.ID_Producto = Producto.ID_Producto
  GROUP BY TO_CHAR(Fecha_emision, 'YYYY-MM'), Producto.ID_Producto, Producto.Nombre
  ORDER BY TO_CHAR(Fecha_emision, 'YYYY-MM'), cantidad_vendida DESC;

-- Vista de ventas por empleado por mes
CREATE VIEW ventas_por_empleado_por_mes AS
  SELECT TO_CHAR(Fecha_emision, 'YYYY-MM') AS mes,
         Empleado.Nombre || ' ' || Empleado.Apellido AS nombre_empleado,
         COUNT(*) AS cantidad_ventas,
         SUM(Total_venta) AS monto_total_vendido
  FROM Venta
    JOIN Empleado ON Venta.ID_Empleado = Empleado.ID_Empleado
  GROUP BY TO_CHAR(Fecha_emision, 'YYYY-MM'), Empleado.ID_Empleado, Empleado.Nombre, Empleado.Apellido
  ORDER BY TO_CHAR(Fecha_emision, 'YYYY-MM'), monto_total_vendido DESC;


-- Vista de cliente del mes (es el que más monto gasta)
/*
CREATE VIEW cliente_del_mes AS
    SELECT TO_CHAR(Fecha_emision, 'YYYY-MM') AS mes,
           Cliente.Nombre AS nombre_cliente,
           SUM(Total_venta) AS monto_total_gastado
    FROM Venta
        JOIN Cliente ON Venta.ID_Cliente = Cliente.ID_Cliente
    GROUP BY TO_CHAR(Fecha_emision, 'YYYY-MM'), Cliente.ID_Cliente, Cliente.Nombre
    HAVING SUM(Total_venta) = (SELECT MAX(monto_total_mes) FROM (
            SELECT SUM(Total_venta) AS monto_total_mes
            FROM Venta
            WHERE TO_CHAR(Fecha_emision, 'YYYY-MM') = TO_CHAR(v.Fecha_emision, 'YYYY-MM')
            GROUP BY TO_CHAR(Fecha_emision, 'YYYY-MM'), ID_Cliente
    )
    WHERE TO_CHAR(v.Fecha_emision, 'YYYY-MM') = TO_CHAR(Fecha_emision, 'YYYY-MM'));
*/

drop view ventas_por_empleado_por_mes;
drop view productos_mas_vendidos_por_mes;
drop view monto_total_vendido_por_mes;
drop view productos_mas_vendidos;