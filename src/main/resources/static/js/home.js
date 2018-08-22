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
    })

});
