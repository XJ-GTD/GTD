function calendarClick() {
    // window.location.href = "file:///android_asset/html/home/index_calendar.html";

    // document.getElementById("main_html").innerHTML = '<object type="text/html" data="index_calendar.html" width="100%" height="100%"></object>';
}

function groupClick() {
    window.location.href = "file:///android_asset/html/home/index_group.html";

}
function addSchedule() {
    window.location.href = "file:///android_asset/html/schedule/add_schedule.html";
}

/*============= ============ group相关 =========== =============*/
function suiji(){
    var v = document.getElementById("nm").value;
    if(v==""){
        window.test.toast1();
    }else{
        window.test.toast2(v);
    }
}

