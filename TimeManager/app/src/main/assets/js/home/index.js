function calendarClick() {
    window.location.href = "file:///android_asset/html/home/index-calendar.html";
}

function suiji(){
    var v = document.getElementById("nm").value;
    if(v==""){
        window.test.toast1();
    }else{
        window.test.toast2(v);
    }
}