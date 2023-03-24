<%-- 
    Document   : modificar
    Created on : Mar 22, 2023, 4:41:50 PM
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
                        <h1 class="h2">Editar producto | ${productoModificar.getId()}</h1>
                        <h3 class="h3">${errorMjs}</h3>
                    </div>
                    <!-- update.jsp -->
                    <form action="ControladorProducto" method="POST">
                        <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <input type="text" class="form-control" placeholder="Nombre del producto" name="txtNombreProducto" value="${productoModificar.getNombre()}" required>
                            <label for="tipoProducto">Tipo de producto </label>
                            <select class="form-control" name="multiTipoProducto" required>
                                    <c:forEach var="tipo" items="${farmacia.getTiposProductos()}">
                                        <c:choose>
                                            <c:when test="${tipo.id == productoModificar.tipo.id}">
                                                <option value="${tipo.id}" selected>${tipo.nombre}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${tipo.id}">${tipo.nombre}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                            </select>
                            <label for="descripcionProducto">Descripción</label>
                            <textarea class="form-control" rows="3" name = "txtDescripcion" placeholder="Descripción del producto" required>${productoModificar.getDescripcion()}</textarea>
                            <label for="precio">Precio</label>
                            <input type="number" class="form-control" placeholder="Precio unitario" name="txtPrecio" step="0.01" min = "0" value ="${productoModificar.getPrecio()}" required>
                            <label for="farmaceutica">Farmaceutica</label>
                            <select class="form-control" name="multiFarmaceutica" required>
                                <c:forEach var="farmaceutica" items="${farmacia.getFarmaceuticas()}">
                                        <c:choose>
                                            <c:when test="${farmaceutica.getId() == productoModificar.tipo.id}">
                                                <option value="${farmaceutica.getId()}" selected>${farmaceutica.getNombre()}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${farmaceutica.getId()}">${farmaceutica.getNombre()}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                            </select>
                            <label for="precio">cantidad</label>
                            <input type="number" class="form-control" placeholder="Cantidad" min="1" name="txtCantidad" value="${productoModificar.getCantidadStock()}" required>
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

