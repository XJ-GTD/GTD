function register() {
    var accountName = document.getElementById("accountName").value;
    var accountMobile = document.getElementById("accountMobile").value;
    var accountMobile_verify = document.getElementById("accountMobile_verify").value;
    var accountCode = document.getElementById("accountCode").value;
    var accountPassword = document.getElementById("accountPassword").value;
    window.signIn.signIn(accountName, accountMobile, accountMobile_verify, accountPassword);
}