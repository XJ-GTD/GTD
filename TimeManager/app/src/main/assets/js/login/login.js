function loginPost() {
    var accountName = document.getElementById("login_account").value;
    var password = document.getElementById("login_password").value;
    if (accountName != "" && password != "") {
        window.login.loginSuccess(accountName,password);
    }
}

function signin() {
    window.login.signIn();
}