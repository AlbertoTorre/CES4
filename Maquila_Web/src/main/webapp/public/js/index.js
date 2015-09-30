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
                    mi_form = form;
                    

                    console.log(datos_form);
                }
            }
        });
    }


    validar();
});