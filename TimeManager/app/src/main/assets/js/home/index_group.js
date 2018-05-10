// function groupShow(groupJson) {
//     document.write("<div class=\"chatrecord\">\n" +
//         "        <!--消息记录搜索栏-->\n" +
//         "        <div class=\"contact\">\n" +
//         "            <div class=\"contact-search\">\n" +
//         "                <i class=\"icon-uniE90F\"></i>\n" +
//         "                <input type=\"search\" placeholder=\"搜索\">\n" +
//         "            </div>\n" +
//         "        </div>\n" +
//         "        <!--消息记录搜索栏end-->\n" +
//         "        <!--会话记录-->\n" +
//         "        <ul class=\"chatrecord-content\">");
//     if (groupJson != null) {
//         // for (var i = 0; i < groupJson.length; i++) {
//         //     document.getElementById("groupList").innerHTML = "<li>\n" +
//         //         "                <a href=\"cardexchange.html\">\n" +
//         //         "                    <div class=\"News-icon-box News-icon-box2\">\n" +
//         //         "                        <i class=\"icon-uniE920\"></i>\n" +
//         //         "                    </div>\n" +
//         //         "                    <em class=\"nav-badge\">1</em>\n" +
//         //         "                    <div class=\"chatrecord-txt\">\n" +
//         //         "                        <span class=\"user-name\">群组A</span>\n" +
//         //         "                        <p>最新消息1.。。。</p>\n" +
//         //         "                    </div>\n" +
//         //         "                    <span>16:43</span>\n" +
//         //         "                </a>\n" +
//         //         "            </li>";
//         // }
//     } else {
//         document.write("<span>暂无群组，去试试创建日程寻找时间之友吧</span>");
//
//     }
//     document.write("</ul>\n" +
//         "\n" +
//         "    </div>")
// }

function init() {
    // window.index_group.getGroupList();
}

function groupShow(groupJson) {
    // var data = JSON.parse(groupJson);
    alert(groupJson.length);
    alert(groupJson[0].roleName);
    for (var i = 1; i < groupJson.length + 1; i++) {

        document.getElementById("group_name" + i).innerText = groupJson[0].groupName;
    }
        // var data = {
        //
        // }
        // if (groupJson != null) {
        //
        // } else {
        //     document.getElementById("groupList").innerHTML = "<span>暂无群组，去试试创建日程寻找时间之友吧</span>";
        // }
}
