$(document).ready(function(){
    $("#add-schedule-filter-button").click(function () {
        var dpt = $("#add-schedule-dpt");
        var weekday = $("#add-schedule-weekday");
        var worktime =  $("#add-schedule-worktime");
        $.ajax({
            url: "/service/add-schedule/filter",
            type: "POST",
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            data: JSON.stringify({
                stuId: "",
                dptId: dpt.find(":selected").val(),
                weekday: weekday.find(":selected").val(),
                interval: worktime.val()
            }),
            success: function (data) {
                var stulist = $("#add-schedule-stuList");
                stulist.empty();
                $.each(data, function (i, stu) {

                    stulist.append($("<option></option>").attr("value", stu.id).text(stu.name));
                })
            },
            error: function (e) {
                alert(e);
            }
        });

        dpt.attr("disabled", true);
        weekday.attr("disabled", true);
        worktime.prop("readonly", true);

    });

    $("#add-schedule-reset-button").click(function () {
        var dpt = $("#add-schedule-dpt");
        var weekday = $("#add-schedule-weekday");
        var worktime =  $("#add-schedule-worktime");
        dpt.attr("disabled", false);
        weekday.attr("disabled", false);
        worktime.prop("readonly", false);
    })

});