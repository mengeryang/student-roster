$(document).ready(function () {
    $('#homedatepicker').datepicker({
        format: "mm/dd/yyyy",
        calendarWeeks: true,
        todayHighlight: true
    });

    $('button.stu-home-search').click(function () {
        var date = $('input.stu-home-datepicker').val();
        var dptId = $('select.stu-home-department').find(':selected').val();

        if(date == "" || dptId == -1) {
            $.notify($(this), "please set both department and date", "error");
            return;
        }

        $('.stu-home-schedule-table').load('/student/home/'+dptId+'/'+date.replace(/\//g,"-"));
    });
});