<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Farmacia</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <link href="./css/styles.css" />
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-center mt-5">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            Iniciar sesión
                        </div>
                        <div class="card-body">
                            <form action="./Controlador" method="POST">
                                <div class="form-group">
                                    <label for="txt_usuario">ID de usuario</label>
                                    <input type="text" class="form-control"  name="txt_usuario">
                                </div>
                                <div class="form-group">
                                    <label for="txt_contrasena">Contraseña</label>
                                    <input type="password" class="form-control" id="contrasena" name="txt_contrasena">
                                </div>
                                <button type="submit" class="btn btn-primary" name="accion" value="btn_iniciarSesion">Iniciar sesión</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    </body>
</html>