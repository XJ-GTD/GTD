function register() {
    var accountName = document.getElementById("accountName").value;
    var accountMobile = document.getElementById("accountMobile").value;
    var accountMobile_verify = document.getElementById("accountMobile_verify").value;
    var accountCode = document.getElementById("accountCode").value;
    var accountPassword = document.getElementById("accountPassword").value;
    if (accountName === "") {
        alert("用户名不能为空！");
        document.forms[0].target="rfFrame";  //将提交的表单设置为iframe
    } else if (accountMobile === "") {
        alert("手机号不能为空！");
        document.forms[0].target="rfFrame";  //将提交的表单设置为iframe
    } else if (accountPassword === "") {
        alert("密码不能为空！");
        document.forms[0].target="rfFrame";  //将提交的表单设置为iframe
    } else {
        window.signIn.signIn(accountName, accountMobile, accountMobile_verify, accountPassword);
    }
}