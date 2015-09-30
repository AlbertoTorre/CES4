<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<html lang="es">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link rel="stylesheet" href="public/complementos/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="public/complementos/bootstrap/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="public/css/index.css">
</head>
<body>
    <div class="container">
        <div class="col-md-12">
            <div class="col-md-4"></div>
            <div class="col-md-4" id="login">
                <form class="form-signin" role="form">
                    <div class="text-center">
                        <img id="avatar" src="public/img/logo.png" alt="avatar">
                    </div>
                    <input id="txtEmail" type="email" class="form-control" placeholder="Email">
                    <input type="password" class="form-control" placeholder="Password">
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Iniciar sesión</button>
                </form>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
    <script src="public/js/jquery.js"></script>
    <script src="public/complementos/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>