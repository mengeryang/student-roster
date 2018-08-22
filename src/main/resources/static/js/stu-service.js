$(document).ready(function () {
    var reverse_weekmap = {};
    reverse_weekmap["Mon"] = 1;
    reverse_weekmap["Tue"] = 2;
    reverse_weekmap["Wed"] = 3;
    reverse_weekmap["Thu"] = 4;
    reverse_weekmap["Fri"] = 5;
    reverse_weekmap["Sat"] = 6;
    reverse_weekmap["Sun"] = 0;

    $('.free-time-checkbox').change(function () {
        var btn_del = $('.btn-free-time-delete') ;
        if($('input:checked').length)
            btn_del.prop('disabled', false);
        else
            btn_del.prop('disabled', true);
    });

    $('button.btn-free-time-delete').click(function () {
        var free_time_list = [];
        var stuId = $('.stuId').text();
        $('tr.free-time-row').each(function () {
            var $this = $(this);
            var slots = $this.find('.free-time-slot');
            // var boxes = $this.find('input:checked');
            if(slots.find('input:checked').length) {
                var tmp = {};
                var times = [];
                tmp["stuId"] = stuId;
                tmp["weekday"] = reverse_weekmap[$this.find('.free-time-weekday').text()];
                tmp["timeSlots"] = times;
                slots.find('input:checked').each(function () {
                    tmp["timeSlots"].push($(this).val())
                });
                slots.each(function () {
                    if($(this).find('input:checked').length)
                        $(this).remove();
                });
                free_time_list.push(tmp);
            }
        });

        // alert(free_time_list);
        $.ajax({
            url: "/student/service/"+stuId+"/set-freetime/delete",
            type: "POST",
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            data: JSON.stringify(free_time_list),
            success: function (data) {
                $.notify(data.msg, "success");
            },
            error: function (e) {
                alert(e.toString());
            }
        });
    })
});