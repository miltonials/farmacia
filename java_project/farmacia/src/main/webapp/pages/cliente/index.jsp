<%-- 
    Document   : index
    Created on : Mar 22, 2023, 12:43:10 AM
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
                        <h1 class="h2">clientes</h1>
                    </div>
                    <a class="btn btn-primary" href="ControladorCliente?accion=agregar" method="GET">Agregar Cliente</a>
                    <div class="table-responsive">
                        <table class="table table-striped table-sm">
                            <thead>
                                <tr>
                                    <!-- Nombre, Apellido, Telefono, Correo_electronico, Fecha_nacimiento, Genero -->
                                    <th scope="col">ID</th>
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Apellido</th>
                                    <th scope="col">Telefono</th>
                                    <th scope="col">Correo Electronico</th>
                                    <th scope = "col">Fecha de nacimiento</th>
                                    <th scope="col">Genero</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                
                            <c:forEach items="${farmacia.getClientes()}"  var="cliente">
                                <tr>
                                    <td>${cliente.getId()}</td>
                                    <td>${cliente.getNombre()}</td>
                                    <td>${cliente.getApellido()}</td>
                                    <td>${cliente.getTelefono()}</td>
                                    <td>${cliente.getCorreoElectronico()}</td>
                                    <td>${cliente.getFechaNacimiento()}</td>
                                    <td>${cliente.getGenero()}</td>
                                    <td>
                                        <a class="btn btn-primary" href="ControladorCliente?accion=paginaEditar&id=${cliente.getId()}" method="POST">Editar</a>
                                        <a class="btn btn-danger" href="ControladorCliente?accion=paginaEliminar&id=${cliente.getId()}" method="POST">Eliminar</a>
                                    </td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
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
