$(document).ready(function(){ 
    $("#new-comment").submit(function(){
        $.post("Comment", $(this).serialize(),
            function (data) {
                alert(data);
            });
        
//        return false; 
        return true; 
    });
});