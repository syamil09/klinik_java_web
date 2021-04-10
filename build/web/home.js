/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    
    data = JSON.parse(sessionStorage.getItem('data'))
    console.log(sessionStorage.getItem('data'))
    if (data == null) {
        // alert("Anda harus login terlebih dahulu")
        // window.location.href = "/PBO_koperasi/login"
    }
    console.log(data);
    // $("#welcomeNama").html(`Halo, ${data.nama}`)
    //$("#welcome").html(`Anda masuk sebagai ${data.level}`)
    
//    if (data.level !== "Admin") {
//        $("#menuUser").remove()
//    }
    
    function logout() {
        if (confirm("Anda ingin logout dari akun ini?")) {
            sessionStorage.clear()
            window.location.href = "/PBO_klinik/login/";
        }
    }
    
    $("#btnLogout").click(function(){
        logout();
    })
})

