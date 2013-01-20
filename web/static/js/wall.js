owner = null;
user = null

/**
 * reaise a modal dialog with a form to create a new album
 */
function createAlbum () {
}


$(document).ready(function(){ 
    $.get("/WebApplication2/wall/user", {
        type:"user"
    }, function(data) {
        user = data;
        
    }, "json");
    
    $.get("/WebApplication2/wall/user", {
        type:"owner"
    }, function(data) {
        owner = data;
        
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
    
    /* 
     * Open Photo Tab
     */
    photos_opened = false;
    $("#photos-link").click(function () {
        if($(this).parent().hasClass("active")) {
            return false;
        }
        $(this).parent().parent().children().toggleClass("active");
        
        if(photos_opened) {
            $("#comments-container").fadeOut(1000, function () {
                $("#albums-container").fadeIn(1000);
            });
            return false;
        } else {
            photos_opened = true;
        }
        
        // Hide content
        $("#comments-container").fadeOut(1000, function () {
            $.get("/WebApplication2/photos/albums/" + owner.id, {},
                function (data) {
                    $("#main-container").append(data);
                    /* Load the script */
                    $script = $("<script />");
                    $script.attr("type", "text/javascript");
                    $script.attr("src", "/WebApplication2/js/photos.js");
                    $("script").last().append($script);
                });
        });
        return false;
    });
        
    /*
     * Wall Tab
     */
    $("#wall-link").click(function () {
        if($(this).parent().hasClass("active")) {
            return false;
        }
        $(this).parent().parent().children().toggleClass("active");        
        $("#albums-container").fadeOut(1000, function () {
            $("#comments-container").fadeIn(1000);
        });
        return false;
    });
    
});
