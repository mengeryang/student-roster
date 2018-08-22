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
    });

    $('button.edit-password-submit').click(function () {
        var orig_pwd = $('#original-pwd');
        var new_pwd = $('#new-pwd');
        var confirm_pwd = $('#confirm-pwd');
        var stuId = $('.stuId').text();
        var sdata = [];
        var tmp1 = {};
        var tmp2 = {};

        if(new_pwd.val() == "") {
            $.notify(new_pwd, "password should not be empty", {className: 'error', position:"right"});
            return;
        }

        if(new_pwd.val() != confirm_pwd.val()) {
            $.notify(confirm_pwd, "password confirmed fail", {className: 'error', position:"right"});
            return;
        }

        tmp1["id"] = stuId;
        tmp1["password"] = orig_pwd.val();
        sdata.push(tmp1);
        tmp2["id"] = stuId;
        tmp2["password"] = new_pwd.val();
        sdata.push(tmp2);

        $.ajax({
            url: "/student/service/"+stuId+"/edit-password",
            type: "POST",
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            data: JSON.stringify(sdata),
            success: function (data) {
                if(data.msg == "success")
                    $.notify("update success!", "success");
                else
                    $.notify(orig_pwd, "wrong password", {className: 'error', position:"right"});
            },
            error: function (e) {
                alert(e.toString());
            }
        });
    });
});