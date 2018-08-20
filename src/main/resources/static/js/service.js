$(document).ready(function(){
    // $('#delschd-datepicker').datepicker({
    //     format: "dd/mm/yyyy",
    //     calendarWeeks: true,
    //     todayHighlight: true
    // });

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

    });

    
    $(".filter-info").change(function () {
        $("#add-schedule-stuList").empty();
    });

    $("#delete-schedule-delete").click(function () {
        var del_sched_list = [];

        if(!confirm("Delete selected schedules?"))
            return;

        $("tr.sched-row").each(function () {
            var $this = $(this);
            var v = $this.find("input.checkbox");
            var sched = {};
            if(v.prop('checked')) {
                sched['stuId'] = $this.find("td.stu-id").text();
                sched['dptId'] = $this.find("td.dpt-id").text();
                sched['weekday'] = $("select.weekday").find(":selected").val();
                sched['interval'] = "";
                del_sched_list.push(sched);
                $this.remove();
            }
        });

        $.ajax({
            url: "/service/delete-schedule",
            type: "POST",
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            data: JSON.stringify(del_sched_list),
            success: function (data) {
                $.notify(data.msg, "success");
            },
            error: function (e) {
                alert(e.toString());
            }
        });
    })

});