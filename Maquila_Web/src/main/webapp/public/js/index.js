$(function (){
    save=0;
     validar =function() {
        $("#form_login").validate({
            rules:{
                txtEmail:{
                    required:true,
                    email:true
                },
                password:{
                    required:true
                }
            },
            submitHandler: function(form){
                if(save == 0){
                    save = 1;
                    if($('#txtEmail').val()=='maquila@gmail.com' && $('#password').val()=='1234567890'){
                        alert('correcto');
                    }else{
                        alert('error');
                    }
                    save = 0;
                }
            }
        });
    }

    $('#txtEmail').focus();
    validar();
});