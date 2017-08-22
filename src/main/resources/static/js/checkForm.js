   $(document).ready(function(){
        $('form').submit(function(event){
            $(".jqueryError").remove();
            $('input').each(function(){
                var getInputVal = $(this).val();
                if($(this).attr("id")!="phoneNumberInput"){
                    if(getInputVal===""){
                        $(this).parent().addClass("has-error");
                        $('<div class="jqueryError alert alert-danger error-message" role="alert">Inputs must be not null!</div>').appendTo($(this).parent());
                        event.preventDefault();
                    } else {
                        $(this).parent().removeClass("has-error");
                    }
                }
            });
        });
    });