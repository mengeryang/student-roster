$(document).ready(function () {
    $('#homedatepicker').datepicker({
        format: "mm/dd/yyyy",
        calendarWeeks: true,
        todayHighlight: true
    });

    $('button.stu-home-search').click(function () {
        var date = $('input.stu-home-datepicker').val();
        var dptId = $('select.stu-home-department').find(':selected').val();
        $('.stu-home-schedule-table').load('/student/'+dptId+'/'+date.replace(/\//g,"-"));
    })
});