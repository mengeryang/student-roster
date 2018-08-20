$(document).ready(function(){
    $("#add-new-staff").click(function () {
        $(".main-board").load("/service/addstaff");
    })
});