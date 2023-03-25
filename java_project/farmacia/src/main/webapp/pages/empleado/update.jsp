<%-- 
    Document   : index
    Created on : Mar 20, 2023, 12:43:10 AM
    Author     : milto
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
                        <h1 class="h2">Editar Empleado | ${empModificar.getId()}</h1>
                        <h3 class="h3">${errorMjs}</h3>
                    </div>
                    <!-- update.jsp -->
                    <form action="ControladorEmpleado" method="POST">
                        <div class="form-group">
                            <label for="cargo">Cargo</label>
                            <select class="form-control" name="multiCargo" required>
                                    <c:forEach var="cargo" items="${farmacia.getCargosEmpleados()}">
                                        <c:choose>
                                            <c:when test="${cargo.id == empModificar.idCargo}">
                                                <option value="${cargo.id}" selected>${cargo.nombre}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${cargo.id}">${cargo.nombre}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                            </select>
                            
                            <label for="cedula">N° Cédula</label>
                            <input type="text" class="form-control" placeholder="Cedula" name="txtCedula" value = "${empModificar.getCedula()}" required>
                            
                            <label for="nombre">Nombre</label>
                            <input type="text" class="form-control" placeholder="Nombre" name="txtNombre" value = "${empModificar.getNombre()}" required>
                            
                            <label for="apellido">Apellido</label>
                            <input type="text" class="form-control" placeholder="Apellido" name="txtApellido" value = "${empModificar.getApellido()}" required>
                            
                            <label for="salario">Salario</label>
                            <input type="number" class="form-control" placeholder="Salario" name="txtSalario" step="0.01" min = "0" value = "${empModificar.getSalario()}" required>
                            
                            <label for="fecha">Fecha de contratación</label>
                            <input type="date" class="form-control" name="txtFechaContratacion" min="2023-03-01" max="2024-03-01" value = "${empModificar.getFecha()}" required>
                            
                        </div>
                        <input type="submit" class="btn btn-primary" name="accion" value="update">
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
