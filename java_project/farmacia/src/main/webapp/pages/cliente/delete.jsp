<%-- 
    Document   : eliminar
    Created on : Mar 24, 2023, 1:05:14 AM
    Author     : Andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" href="./css/styles.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    </head>
    <body>
        <header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">

        </header>
        <div class="container-fluid">
            <div class="row">
                <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse" style="">

                </nav>

                <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                    <div class="chartjs-size-monitor">
                        <div class="chartjs-size-monitor-expand">
                            <div class=""></div>
                        </div>
                        <div class="chartjs-size-monitor-shrink">
                            <div class=""></div>
                        </div>
                    </div>
                    <div
                        class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                        <h1 class="h2">Visualizando Cliente | ${clienteModificar.getId()}</h1>
                        <h3 class="h3">${errorMjs}</h3>
                    </div>
                    <!-- update.jsp -->
                    <form action="ControladorCliente" method="POST">
                        <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <input type="text" class="form-control" placeholder="Nombre del Cliente" name="txtNombreCliente" value="${clienteModificar.getNombre()}" disabled>
                            <label for="apellido">Apellido</label>
                            <input type="text" class="form-control" placeholder="Apellido del Cliente" name="txtApellido" value="${clienteModificar.getApellido()}" disabled>                       
                            <label for="telefono">Telefono</label>
                            <input type="text" class="form-control" placeholder="Telefono del Cliente" name="txtTelefono" value="${clienteModificar.getTelefono()}" disabled>
                            <label for="correo_electronico">Correo Electronico</label>
                            <input type="text" class="form-control" placeholder="Correo Electronico del Cliente" name="txtCorreoElectronico" value="${clienteModificar.getCorreoElectronico()}" disabled>
                            <label for="fecha_nacimiento">Fecha de nacimiento</label>
                            <input type="date" class="form-control" placeholder="Fecha de nacimiento del Cliente" name="txtFechaNacimiento" value="${clienteModificar.getFechaNacimiento()}" disabled>
                            <label for="genero">Genero</label>
                            <input type="text" class="form-control" placeholder="Genero del Cliente" name="txtGenero" value="${clienteModificar.getGenero()}" disabled>

                        </div>
                        <input type="submit" class="btn btn-danger" name="accion" value="delete">
                    </form>
            </div>
        </main>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" 
        integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" 
crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js" integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha" crossorigin="anonymous"></script>

<script type="module" src="./js/main.js"></script>
</body>
</html>

