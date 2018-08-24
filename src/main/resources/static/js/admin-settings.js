$(document).ready(function () {
   $('#settings-setfree').click(function () {
       var $this = $(this);
       var r = confirm("Are you sure to "+$this.text()+" students setting free time?");
       if(r == false)
           return;

       if($this.text() == "enable")
           $this.text("disable");
       else
           $this.text("enable");

       $.ajax({
           url: "/admin/setting/toggle-free",
           type: "GET",
           success: function (data) {
               $.notify(data.msg, "success");
           },
           error: function (e) {
               alert(e);
           }
       });
   });

   $('#settings-save').click(function () {
       var workload = $('#settings-workload').val();
       if(!$.isNumeric(workload)) {
           $.notify($(this), "must be integer", "error");
           return;
       }

       $.ajax({
           url: "/admin/setting/workload/"+workload,
           type: "GET",
           success: function (data) {
               $.notify(data.msg, "success");
               $('#workload-label').text('current max workload: ' + workload + ' min');
           },
           error: function (e) {
               alert(e);
           }
       });
   })
});