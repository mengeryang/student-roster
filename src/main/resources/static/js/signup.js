
$(document).ready(function(){
    $("button#register").click(function () {
        $.ajax({
            url: "signupinfo.html",
            type: "POST",
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            data: JSON.stringify({
                userId: $("input#id").val(),
                username: $("input#name").val(),
                password: $("input#password").val()
            })
        });
    });
});