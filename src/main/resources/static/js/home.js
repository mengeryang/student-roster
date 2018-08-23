$(document).ready(function () {
    $('#homedatepicker').datepicker({
        format: "mm/dd/yyyy",
        calendarWeeks: true,
        todayHighlight: true
    });

    $('button.home-search').click(function () {
        var date = $('input.home-datepicker').val();
        var dptId = $('select.home-department').find(':selected').val();
        $('.home-schedule-table').load('/admin/home/'+dptId+'/'+date.replace(/\//g,"-"));
    });

    $(document).on('click', 'button.admin-reply', function () {
        var $this = $(this);
        var opt = $this.text();
        var msgId = $this.val();
        // alert(opt + '/' + msgId);
        $.ajax({
            url: "/admin/home/reply/"+msgId+"/"+opt,
            type: "GET",
            success: function () {
                $this.parent().parent().parent().remove();
            },
            error: function (e) {
                alert(e);
            }
        });
    })

});
