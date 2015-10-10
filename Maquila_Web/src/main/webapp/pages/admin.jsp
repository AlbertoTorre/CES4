<%-- 
    Document   : admin
    Created on : 2/10/2015, 11:29:42 PM
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administracion</title>
        <link rel="stylesheet" href="../public/complementos/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="../public/complementos/bootstrap/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="../public/css/index.css">

    </head>
    <body>









        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#ModalInsumo">
            Insumos
        </button>

        <!-- Modal -->
        <div class="modal fade" id="ModalInsumo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Maestro Insumos</h4>
                    </div>
                    <div class="modal-body">

                        <form id="form_insumo" class="form-horizontal">
                            <div class="form-group">
                                <label for="txtcodigo" class="col-sm-2 control-label">Codigo</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="txtcodigo" name="txtcodigo" placeholder="Codigo">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="txtnombre" class="col-sm-2 control-label">Nombre</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="txtnombre" name="txtnombre" placeholder="Nombre">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="txtvalor" class="col-sm-2 control-label">Valor</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="txtvalor" name="txtvalor" placeholder="Valor">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="txtcantidad" class="col-sm-2 control-label">Cantidad</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="txtcantidad" name="txtcantidad" placeholder="Cantidad">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-default">Guardar</button>
                                </div>
                            </div>
                        </form>


                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        
                    </div>
                </div>
            </div>
        </div>








        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#ModalDocumento">
            Tipo Documento
        </button>

        <!-- Modal -->
        <div class="modal fade" id="ModalDocumento" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Maestro Tipo Documento</h4>
                    </div>
                    <div class="modal-body">
                        <form id="form_insumo" class="form-horizontal">
                            <div class="form-group">
                                <label for="txtcodigo" class="col-sm-2 control-label">Codigo</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="txtcodigo" name="txtcodigo" placeholder="Codigo">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="txtnombre" class="col-sm-2 control-label">Nombre</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="txtnombre" name="txtnombre" placeholder="Nombre">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-default">Guardar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>







        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#ModalPersona">
            Persona
        </button>

        <!-- Modal -->
        <div class="modal fade" id="ModalPersona" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                    </div>
                    <div class="modal-body">
                        ...
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        
                    </div>
                </div>
            </div>
        </div>





        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#ModalCliente">
            Cliente
        </button>

        <!-- Modal -->
        <div class="modal fade" id="ModalCliente" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                    </div>
                    <div class="modal-body">
                        ...
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        
                    </div>
                </div>
            </div>
        </div>









        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#ModalEmpleado">
            Empleado
        </button>

        <!-- Modal -->
        <div class="modal fade" id="ModalEmpleado" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                    </div>
                    <div class="modal-body">
                        ...
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        
                    </div>
                </div>
            </div>
        </div>





        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#ModalServicios">
            Servicios
        </button>

        <!-- Modal -->
        <div class="modal fade" id="ModalServicios" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                    </div>
                    <div class="modal-body">
                        ...
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        
                    </div>
                </div>
            </div>
        </div>  







        <script src="../public/js/jquery.js"></script>
        <script src="../public/complementos/bootstrap/js/bootstrap.min.js"></script>

    </body>
</html>
