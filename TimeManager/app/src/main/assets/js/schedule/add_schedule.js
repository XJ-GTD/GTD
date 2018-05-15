
function init_add() {
    
}

function addClick() {
    var scheduleName = document.getElementById("schedule_name").value;
    var scheduleDetial = document.getElementById("schedule_detail").value;
    var scheduleStartDate = document.getElementById("pickdate").value + " " + document.getElementById("picktime").value;
    var scheduledEndDate = document.getElementById("pickdate2").value + " " + document.getElementById("picktime2").value;

    var remind = document.getElementById("remind_time");
    var index = remind.selectedIndex;
    var scheduledRenindDate = remindTimeSpilt(document.getElementById("pickdate").value, document.getElementById("picktime").value, remind.options[index].value);

    var remind_repeat = document.getElementById("remind_repeat");
    var index_repeat = remind_repeat.selectedIndex;
    var scheduledRenindRepeatType = remind_repeat.options[index_repeat].value;

    var executor = document.getElementById("schedule_person").value;

    var createGroup = document.getElementById("createGroup");
    var groupIndex = createGroup.selectedIndex;
    var flagFocus = createGroup.options[groupIndex].value;
    var focus = document.getElementById("focus");
    var focusIndex = focus.selectedIndex;
    var flagCreateGroup =focus.options[focusIndex].value;

    window.index_schedule.add_Schedule(scheduleName, scheduleDetial, scheduleStartDate, scheduledEndDate, scheduledRenindDate, scheduledRenindRepeatType, executor, flagFocus, flagCreateGroup);
}

function remindDate() {
    var remind = document.getElementById("remind_time");
    var index = remind.selectedIndex;
    if (remind.options[index].value != "time_9") {
        document.getElementById("remind_Date").setAttribute("hidden", true);
    } else {
        document.getElementById("remind_Date").removeAttribute("hidden");
    }
}

/*=======================================================*/

function remindTimeSpilt(remindTime1, remindTime2, remindType) {
    var time = null;
    alert(remindType);
    if (remindType === "time_2") {
        time = remindTime1  + " " + remindTime2;
    } else if (remindType === "time_3") {

    } else if (remindType === "time_4") {

    } else if (remindType === "time_5") {

    } else if (remindType === "time_6") {

    } else if (remindType === "time_7") {

    } else if (remindType === "time_8") {

    } else if (remindType === "time_9") {
        time = document.getElementById("pickdate3").value + " " + document.getElementById("picktime3").value;
    } else {
        return null;
    }
    return time;
}