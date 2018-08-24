$(document).ready(function(){
    // $('#delschd-datepicker').datepicker({
    //     format: "dd/mm/yyyy",
    //     calendarWeeks: true,
    //     todayHighlight: true
    // });

    // $("button.free-time-search").click(function () {
    //    var stuId = $("input.free-time-stuId").val();
    //    $("#free-time-table-template").load("/service/set-freetime/" + stuId, function () {
    //        $('.btn-free-time-delete').prop('disabled', true);
    //        if($('.tr-free-time').length)
    //            $('.btn-free-time-add').prop('disabled', false);
    //        else
    //            $('.btn-free-time-add').prop('disabled', true);
    //    });
    //
    // });
    // $('.schedule-checkbox').change(function () {
    //     var btn_del = $('.btn-schedule-delete') ;
    //     if($('input:checked').length)
    //         btn_del.prop('disabled', false);
    //     else
    //         btn_del.prop('disabled', true);
    // });

    // $('#edit-schedule-search').click(function () {
    //     var dptId = $('#edit-schedule-dptlist').find(':selected').val();
    //     var weekday = $('#edit-schedule-weekdaylist').find(':selected').val();
    //
    //     if(dptId == '-1' || weekday == '-1')
    //         return;
    //
    //     $('div.schedule-table').load("/admin/service/edit-schedule/" + dptId + "/" + weekday, function () {
    //         $('.btn-schedule-add').prop("disabled", false);
    //     });
    // });

    $('select.filter-info').change(function () {
        // $('#edit-schedule-search').trigger('click');
        var dptId = $('#edit-schedule-dptlist').find(':selected').val();
        var weekday = $('#edit-schedule-weekdaylist').find(':selected').val();

        if(dptId == '-1' || weekday == '-1') {
            $('.btn').prop('disabled', true);
            return;
        }
        else
            $('.btn').prop('disabled', false);

        $('div.schedule-table').load("/admin/service/edit-schedule/" + dptId + "/" + weekday, function () {
            $('.btn-schedule-add').prop("disabled", false);
        });
    });

    $('button.modal-filter').click(function () {
       var timeslots = [];
       var dptId = $('#edit-schedule-dptlist').find(":selected").val();
       var weekday = $('#edit-schedule-weekdaylist').find(":selected").val();

       timeslots.push($('input.filter').val());
       // alert(dptId + '/' + weekday + '/' + timeslots);
        $.ajax({
                    url: "/admin/service/edit-schedule/filter",
                    type: "POST",
                    contentType:"application/json; charset=utf-8",
                    dataType:"json",
                    data: JSON.stringify({
                        stuId: "",
                        dptId: dptId,
                        weekday: weekday,
                        timeSlots: timeslots,
                        rawSlots: ""
                    }),
                    success: function (data) {
                        var stulist = $("#edit-schedule-stuList");
                        stulist.empty();
                        $.each(data, function (i, stu) {
                            stulist.append($("<option></option>").attr("value", stu.id).text(stu.name));
                        });
                        if(!stulist.has('option').length > 0)
                            stulist.append($("<option></option>").attr("value", '').text('no available'));
                        else
                            $('input.filter').prop('readonly', true);
                    },
                    error: function (e) {
                        alert(e);
                    }
                });
    });

    $('button.btn-schedule-add').click(function () {
        $('input.filter').val('');
        $('#edit-schedule-stuList').empty();
        $('#edit-schedule-stuList').append($("<option></option>").attr("value", '-1').text('select-student'));
    });

    $('button.modal-submit').click(function () {

        var timeSlots=[];
        var dptId = $('#edit-schedule-dptlist').find(":selected").val();
        var weekday = $('#edit-schedule-weekdaylist').find(":selected").val();
        var stuId = $('#edit-schedule-stuList').find(":selected").val();
        timeSlots.push($('input.filter').val());
        var mydata = {};

        if(stuId == '-1' || stuId == ''){
            $.notify($(this), "please select invalid student", "error");
            return;
        }

        mydata["stuId"] = stuId;
        mydata["dptId"] = dptId;
        mydata["weekday"] = weekday;
        mydata["timeSlots"] = timeSlots;

        $.ajax({
            url: "/admin/service/edit-schedule/add",
            type: "POST",
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            data: JSON.stringify(mydata),
            success: function () {
                $('select.filter-info').trigger('change');
            },
            error: function (e) {
                alert(e);
            }
        });
        // $('div.schedule-table').load('/admin/service/edit-schedule/add', JSON.stringify(mydata));
        $('#modal-2').modal('toggle');
    });

    $('button.btn-schedule-delete').click(function () {
        if(!$('input:checked').length)
            return;

        var cf = confirm("Delete select schedule(s)?");
        if(!cf)
            return;

        var delete_list = [];
        var dptId = $('#edit-schedule-dptlist').find(":selected").val();
        var weekday = $('#edit-schedule-weekdaylist').find(":selected").val();

        $('tr.schedule-row').each(function () {
            var $this = $(this);
            var slots = $this.find('.schedule-slot');
            // var boxes = $this.find('input:checked');
            if(slots.find('input:checked').length) {
                var tmp = {};
                var times = [];
                tmp["stuId"] = $this.find('.schedule-stuId').text();
                tmp["dptId"] = dptId;
                tmp["weekday"] = weekday;
                tmp["timeSlots"] = times;
                slots.find('input:checked').each(function () {
                    tmp["timeSlots"].push($(this).val())
                });
                // slots.each(function () {
                //     if($(this).find('input:checked').length)
                //         $(this).remove();
                // });
                delete_list.push(tmp);
            }
        });

        // alert(free_time_list);
        $.ajax({
            url: "/admin/service/edit-schedule/delete",
            type: "POST",
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            data: JSON.stringify(delete_list),
            success: function (data) {
                $('select.filter-info').trigger('change');
                // $.notify(data.msg, "success");
            },
            error: function (e) {
                alert(e.toString());
            }
        });
    });

    // $("#add-schedule-filter-button").click(function () {
    //     var dpt = $("#add-schedule-dpt");
    //     var weekday = $("#add-schedule-weekday");
    //     var worktime =  $("#add-schedule-worktime");
    //     $.ajax({
    //         url: "/service/add-schedule/filter",
    //         type: "POST",
    //         contentType:"application/json; charset=utf-8",
    //         dataType:"json",
    //         data: JSON.stringify({
    //             stuId: "",
    //             dptId: dpt.find(":selected").val(),
    //             weekday: weekday.find(":selected").val(),
    //             interval: worktime.val()
    //         }),
    //         success: function (data) {
    //             var stulist = $("#add-schedule-stuList");
    //             stulist.empty();
    //             $.each(data, function (i, stu) {
    //
    //                 stulist.append($("<option></option>").attr("value", stu.id).text(stu.name));
    //             })
    //         },
    //         error: function (e) {
    //             alert(e);
    //         }
    //     });
    //
    // });
    //
    //
    // $(".filter-info").change(function () {
    //     $("#add-schedule-stuList").empty();
    // });
    //
    // $("#delete-schedule-delete").click(function () {
    //     var del_sched_list = [];
    //
    //     if(!confirm("Delete selected schedules?"))
    //         return;
    //
    //     $("tr.sched-row").each(function () {
    //         var $this = $(this);
    //         var v = $this.find("input.checkbox");
    //         var sched = {};
    //         if(v.prop('checked')) {
    //             sched['stuId'] = $this.find("td.stu-id").text();
    //             sched['dptId'] = $this.find("td.dpt-id").text();
    //             sched['weekday'] = $("select.weekday").find(":selected").val();
    //             sched['interval'] = "";
    //             del_sched_list.push(sched);
    //             $this.remove();
    //         }
    //     });
    //
    //     $.ajax({
    //         url: "/service/delete-schedule",
    //         type: "POST",
    //         contentType:"application/json; charset=utf-8",
    //         dataType:"json",
    //         data: JSON.stringify(del_sched_list),
    //         success: function (data) {
    //             $.notify(data.msg, "success");
    //         },
    //         error: function (e) {
    //             alert(e.toString());
    //         }
    //     });
    // });
    //
    // $('input:checkbox').change(function () {
    //     alert($('.checkbox-free-time:checked').length);
    //     if($('.checkbox-free-time:checked').length)
    //         $('.btn-free-time-delete').prop('disabled', false);
    //     else
    //         $('.btn-free-time-delete').prop('disabled', true);
    // });

});