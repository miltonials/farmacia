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
                        <h1 class="h2">Editar venta | ${ventaModificar.getId()}</h1>
                        <h3 class="h3">${errorMjs}</h3>
                    </div>
                    <!-- update.jsp -->
                    <form action="ControladorVenta" method="POST">
                        <div class="form-group">
                            <label for="cliente">Cliente </label>
                            <select class="form-control" name="multiCliente" required>
                                    <c:forEach var="cliente" items="${farmacia.getClientes()}">
                                        <c:choose>
                                            <c:when test="${cliente.id == ventaModificar.cliente.id}">
                                                <option value="${cliente.id}" selected>${cliente.nombre}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${cliente.id}">${cliente.nombre}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                            </select>
                            
                            <label for="empleado">Empleado </label>
                            <select class="form-control" name="multiEmpleado" required>
                                    <c:forEach var="empleado" items="${farmacia.getEmpleados()}">
                                        <c:choose>
                                            <c:when test="${empleado.id == ventaModificar.empleado.id}">
                                                <option value="${empleado.id}" selected>${empleado.nombre}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${empleado.id}">${empleado.nombre}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                            </select>
                            
                            <label for="fecha">Fecha de venta</label>
                            <input type="date" class="form-control" name="txtFecha" min = "24/03/2023" value ="${ventaModificar.getFecha()}" required>
                            
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
