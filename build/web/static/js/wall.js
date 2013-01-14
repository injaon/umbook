owner = null;
user = null

$(document).ready(function(){ 
    $.get("/WebApplication2/wall/user", {
        type:"user"
    }, function(data) {
        user = data;
        
    }, "json");
    
    
    $("#new-comment").submit(function(){
        $.post("/WebApplication2/wall/comment", $(this).serialize(), 
            function (data) {
                var $div = $("div.comment-box").first().clone();                 
                $div.hide();
                $div.find("img").attr("src", "/WebApplication2" + user.photo);
                $div.find("a").attr("href", "/WebApplication2/wall/" + user.id);
                $div.find(".origin-name").first().html(user.fullName);              
                $div.find(".comment-body").first().html(data.body);            
                $("#comments-container").prepend($div);
                $div.fadeIn(1000);
             
            }, "json");
       
        $("#new-comment").find("input[type=text]").val("");
        return false; 
   
    });
    
});
