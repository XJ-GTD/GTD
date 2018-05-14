function load_group() {
    document.getElementById("main_html").innerHTML = '<object type="text/html" data="index_group.html" width="100%" height="100%"></object>';
}
function load_calendar() {
    document.getElementById("main_html").innerHTML = '<object type="text/html" data="index_calendar.html" width="100%" height="100%"></object>';
}
function load_add() {
    // document.getElementById("main_html").innerHTML = '<object type="text/html" data="../schedule/add_schedule.html" width="100%" height="100%"></object>';
    window.index_group.addSchedule();
}
// function groupClick() {
//     window.location.href = "file:///android_asset/html/home/index_group.html";
//
// }
// function addSchedule() {
//     window.location.href = "file:///android_asset/html/schedule/add_schedule.html";
// }
var record_switch=false;
function audio_record() {
    if(!record_switch){
        window.index_group.recordStart();
        record_switch=true;
    }else if(record_switch){
        window.index_group.recordEnd();
        record_switch=false;
    }
}

/*============= ============ group相关 =========== =============*/
function suiji(){
    var v = document.getElementById("nm").value;
    if(v==""){
        window.login.toast1();
    }else{
        window.login.toast2(v);
    }
}

