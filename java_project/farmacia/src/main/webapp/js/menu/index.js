const menu = () => {
    const el = `
        <div class="position-sticky pt-3 sidebar-sticky">
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="Controlador?accion=home" method="GET">
                        📊 Dashboard
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ControladorCargoEmpleado?accion=index" method="POST">
                        🏢 Cargos de empleado
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ControladorDetalleVenta?accion=index" method="POST">
                        📄 Detalle de ventas
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        🛒 Ventas
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        💼 Empleados
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ControladorFarmaceutica?accion=index" method="POST">
                        🏥 Farmaceuticas
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ControladorProducto?accion=index" method="POST">
                        💊 Productos
                    </a>
                </li>
                <li>
                    <a class="nav-link" href="#">
                        📃 Tipos de productos
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">
                        💵 Clientes
                    </a>
                </li>
            </ul>
        </div>
    `;
    document.querySelector('nav').innerHTML = el;
}

export default menu;