//首页添加日程
function addClick() {
    var scheduleName = document.getElementById("schedule_name").value;
    var scheduleDetail = document.getElementById("schedule_detail").value;
    var scheduleStartDate = document.getElementById("pickdate").value + " " + document.getElementById("picktime").value;
    var scheduleEndDate = document.getElementById("pickdate2").value + " " + document.getElementById("picktime2").value;

    var remind = document.getElementById("remind_time");
    var index = remind.selectedIndex;
    var scheduleRemindDate = remindTimeSpilt(document.getElementById("pickdate").value, document.getElementById("picktime").value, remind.options[index].value);

    var remind_repeat = document.getElementById("remind_repeat");
    var index_repeat = remind_repeat.selectedIndex;
    var scheduleRemindRepeatType = remind_repeat.options[index_repeat].value;

    var executor = document.getElementById("schedule_person").value;

    var createGroup = document.getElementById("createGroup");
    var groupIndex = createGroup.selectedIndex;
    var flagCreateGroup = createGroup.options[groupIndex].value;
    var focus = document.getElementById("focus");
    var focusIndex = focus.selectedIndex;
    var flagFocus =focus.options[focusIndex].value;

    window.personSchedule.editPersonSchedule(scheduleName, scheduleDetail, scheduleStartDate, scheduleEndDate, scheduleRemindDate, scheduleRemindRepeatType, executor, flagCreateGroup, flagFocus);
}

//群组内添加日程
function addGroupClick() {
    var scheduleName = document.getElementById("schedule_name").value;
    var scheduleDetail = document.getElementById("schedule_detail").value;
    var scheduleStartDate = document.getElementById("pickdate").value + " " + document.getElementById("picktime").value + ":00";
    var scheduleEndDate = document.getElementById("pickdate2").value + " " + document.getElementById("picktime2").value + ":00";

    var remind = document.getElementById("remind_time");
    var index = remind.selectedIndex;
    var scheduleRemindDate = remindTimeSpilt(document.getElementById("pickdate").value, document.getElementById("picktime").value, remind.options[index].value);

    var remind_repeat = document.getElementById("remind_repeat");
    var index_repeat = remind_repeat.selectedIndex;
    var scheduleRemindRepeatType = remind_repeat.options[index_repeat].value;

    var executor = document.getElementById("schedule_person").value;

    var focus = document.getElementById("focus");
    var focusIndex = focus.selectedIndex;
    var flagFocus =focus.options[focusIndex].value;

    window.group_schedule.add_Schedule_Group(scheduleName, scheduleDetail, scheduleStartDate, scheduleEndDate, scheduleRemindDate, scheduleRemindRepeatType, executor, flagFocus);
}

//编辑日程初始化
function editInit() {
    var data = window.personSchedule.getPersonSchedule();
    var scheduleData = null;
    if (data != null) {
        scheduleData = JSON.parse(data);
    }

    document.getElementById("schedule_name").innerHTML = scheduleData.scheduleName;
    document.getElementById("schedule_detail").innerHTML = scheduleData.schedule_detail;
    // document.getElementById("remind_time").innerHTML = scheduleData.scheduleName;
    // document.getElementById("schedule_name").innerHTML = scheduleData.scheduleName;

}

//群组内编辑日程
function editGroupScheduleClick() {

    var remind = document.getElementById("remind_time");
    var index = remind.selectedIndex;
    var scheduleRemindDate = remindTimeSpilt(document.getElementById("pickdate").value, document.getElementById("picktime").value, remind.options[index].value);

    var remind_repeat = document.getElementById("remind_repeat");
    var index_repeat = remind_repeat.selectedIndex;
    var scheduleRemindRepeatType = remind_repeat.options[index_repeat].value;

    var executor = document.getElementById("schedule_person").value;

    var focus = document.getElementById("focus");
    var focusIndex = focus.selectedIndex;
    var flagFocus =focus.options[focusIndex].value;

    window.personSchedule.editPersonSchedule(scheduleRemindDate, scheduleRemindRepeatType, executor, flagFocus);
}

/*=======================================================*/
//控制提醒时间输入框显隐
function remindDate() {
    var remind = document.getElementById("remind_time");
    var index = remind.selectedIndex;
    if (remind.options[index].value === "time_9") {
        document.getElementById("remind_Date").removeAttribute("hidden");
    } else {
        document.getElementById("remind_Date").setAttribute("hidden", true);
    }
}

//判断提醒时间重复类型
function remindTimeSpilt(remindTime1, remindTime2, remindType) {
    var time = null;
    if (remindType === "time_2") {
        time = remindTime1  + " " + remindTime2 + ":00";
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