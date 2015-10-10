$(function() {
    save = 0;
    validar = function() {
        $("#form_login").validate({
            rules: {
                txtEmail: {
                    required: true,
                    email: true
                },
                password: {
                    required: true
                }
            },
            submitHandler: function(form) {
                if (save == 0) {
                    save = 1;
                    if ($('#txtEmail').val() == 'maquila@gmail.com' && $('#password').val() == '1234567890') {
                        window.location = 'pages/admin.jsp';
                    } else {
                        msn('Error!','Correo o Contraseña Equivocado.')
                    }
                    save = 0;
                }
            }
        });
    }



    function msn(title, msn) {
        $('.alert-warning').remove();
        n = '<div class=\"alert alert-warning alert-dismissible fade in\" role=\"alert\" style=\"position: fixed; z-index: 9999; right: 5px; bottom: -17px; width: 30%;background-color: #663F35 !important; border-color: #663F35 !important; color: #B06D3B !important; \">' +
                '<button class=\"close\" data-dismiss=\"alert\" type=\"button\">' +
                '<span aria-hidden=\"true\">×</span><span class=\"sr-only\">Cerrar</span>' +
                '</button>' +
                '<strong>' + title + '!</strong> <br>' + msn +
                '</div>';
        $('body').prepend(n);
        $('.alert-warning').delay(12000).fadeOut(600);
    }


    $('#txtEmail').focus();
    validar();
});