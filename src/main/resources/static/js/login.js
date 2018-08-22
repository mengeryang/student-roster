$(document).ready(function () {
    var msg = $("p.msg").text();
    if(msg != "")
        $.notify($("#login-signin"),msg, "error");

    // $("button#signin").click(function () {
    //     $.ajax({
    //         url: "loginCheck.html",
    //         type: "POST",
    //         contentType:"application/json; charset=utf-8",
    //         dataType:"json",
    //         data: JSON.stringify({
    //             userId: $("input#userId").val(),
    //             username: "",
    //             password: $("input#password").val()
    //         }),
    //         success: function (data, textStatus) {
    //             window.location.replace(data.url);
    //         }
    //     });
    // })
});