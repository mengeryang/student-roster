$(document).ready(function () {
    $('#homedatepicker').datepicker({
        format: "mm/dd/yyyy",
        calendarWeeks: true,
        todayHighlight: true,
    });

    $(function () {
        $('[data-toggle="popover"]').popover()
    });

    $('button.home-search').click(function () {
        var date = $('input.home-datepicker').val();
        var dptId = $('select.home-department').find(':selected').val();

        if(date == "" || dptId == -1) {
            $.notify($(this), "please set both department and date", "error");
            return;
        }

        $('.home-schedule-table').load('/admin/home/'+dptId+'/'+date.replace(/\//g,"-"));
    });

    // function home_search() {
    //     var date = $('input.home-datepicker').val();
    //     var dptId = $('select.home-department').find(':selected').val();
    //
    //     if(date == "" || dptId == -1)
    //         return;
    //
    //     $('.home-schedule-table').load('/admin/home/'+dptId+'/'+date.replace(/\//g,"-"));
    // }

    // $('select.home-filter').on('change', home_search);
    // $('input.home-filter').bind('input', home_search);

    $(document).on('click', 'button.admin-reply', function () {
        var $this = $(this);
        var opt = $this.text();
        var msgId = $this.val();
        // alert(opt + '/' + msgId);
        $.ajax({
            url: "/admin/home/reply/"+msgId+"/"+opt,
            type: "GET",
            success: function () {
                $this.parent().parent().parent().parent().remove();
            },
            error: function (e) {
                alert(e);
            }
        });
    })

});
