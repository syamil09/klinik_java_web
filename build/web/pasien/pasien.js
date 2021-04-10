/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    
        var nik, nama, gender, tmpLahir, tglLahir, alamat, telepon, page;
        
        // save value from view to variable        
        function getInputValue(){
            id = $("#id").val();
            nama = $("#nama").val();
            gender = $("#jenkel").children("option:selected").val();
            tmpLahir = $("#tmpLahir").val();
            tglLahir = $("#tgl_lahir").val();
            alamat = $("#alamat").val();
            telepon = $("#telepon").val();
            noKtp = $("#no_ktp").val();
            noHp = $("#no_hp").val();
            password = $("#password").val();
            golonganDarah = $("#golongan_darah").val();
        }
        
        // procedure add data
        $("#btnAdd").click(function(){
                $("#myModal").modal('show');
                $("#titel1").show();
                $("#titel2").hide();
                $("#nik").prop('disabled', false);
                page="tambah";
                console.log("add");
        });    
        
        // save data
        $("#btnSave").on('click', function(){
    	getInputValue();
    	if (id === "" && page === "edit") {
            alert("ID Harus Diisi!!");
            $("#id").focus();
    	}
    	else if (nama === "") {
            alert("Nama Harus Diisi!!");
            $("#nama").focus();
    	}
    	else{             
            $.post('/PBO_klinik/PasienCtr', {
    		page: page,
    		id: id,
    		nama: nama,
    		gender: gender,
    		noKtp: noKtp,
    		tglLahir: tglLahir,
    		alamat: alamat,
    		noHp: noHp,
                golDarah: golonganDarah,
                password: password
                },
                    function(data, status) {
                        alert(data);
                        
                        if (data === "Data Berhasil diupdate" || data === "Data Berhasil disimpan") {location.reload(); }
                    });
                }
        });

        $("#btnCancel").on('click', function() {
    	$("#myModal").modal('hide');
    });

    // get all data 
    $.ajax({
        url: "/PBO_klinik/PasienCtr?page=active", 
        method: "GET", 
        dataType: "json",
        success:
            function(data){
                    $("#dataTable").DataTable({
                    serverside: true,
                    processing: true,
                    data: data,
                    sort: true,
                    searching: true,
                    paging: true,
                    columns: [
                            {'data': 'idPasien', 'name': 'idPasien', 'type': 'string'},
                            {'data': 'nama'},
                            {'data': 'jenisKelamin', className: 'text-center'},
                            {'data': 'tglLahir', className: 'text-center'},
                            {'data': 'noHp'},   
                            {'data': 'noKtp'},
                            {'data': null, 'className': 'dt-right', 'mRender': function(o){
                                    return "<a class='btn btn-outline-warning btn-sm'"
                                    + "id = 'btnEdit' href='#'>Edit</a>"
                                    + "&nbsp;&nbsp;"
                                    + "<a class='btn btn-outline-danger btn-sm' "
                                    + "id='btnDel' href='#'>Hapus</a>";
                                }
                            }
                        ]
                    });
                
            }
         });
         
         // procedure delete data
//         $('#dataTable tbody').on('click', '#btnDel', function() {
//             // get nik when clicked btn in the current row
//             let baris = $(this).closest('tr');
//             let id = baris.find("td:eq(0)").text();
//             let nama = baris.find("td:eq(1)").text();
//             page = 'hapus';
//             
//             if (confirm(`Anda yakin data  : ${id} - ${nama} akan dihapus ?`)) {
//                 console.log('okokok hapus');
//                 $.post("/PBO_klinik/PasienCtr", {
//                        page: page,
//                        id: id
//                 },
//                 function(data, status) {
//                     alert(data);
//                     location.reload();
//                 });
//
//             }
//         });
         
         // procedure delete data
         $('#dataTable tbody').on('click', '#btnDel', function() {
             // get id when clicked btn in the current row
             let baris = $(this).closest('tr');
             let id = baris.find("td:eq(0)").text();
             let nama = baris.find("td:eq(1)").text();       
             page = 'hapus';
             console.log(id, nama);
             if (confirm(`Anda yakin data  : ${id} - ${nama} akan dihapus ?`)) {
                 console.log('hapus')
                 $.post("/PBO_klinik/PasienCtr", {
                        page: page,
                        id: id
                 },
                 function(data, status) {
                     alert(data);
                     location.reload();
                 });
            }
        });
         
         // procedure edit data
         $("#dataTable tbody").on("click", "#btnEdit", function() {
                $("#myModal").modal('show');
                $("#titel1").hide();
                $("#titel2").show();
                $("#id").prop('disabled', true);
                page = "tampil";
                
                let baris = $(this).closest('tr');
                let id = baris.find("td:eq(0)").text();
                console.log(id)
                $.post('/PBO_klinik/PasienCtr', {
                        page: page,
                        id: id
                 },
                 function(data, status) {
                     console.log(data);
                     $('#nik').val(data.nik);
                     $('#nama').val(data.nama);
                     $('#tmpLahir').val(data.tmpLahir);
                     $('#tglLahir').val(data.tglLahir);
                     $('#alamat').val(data.alamat);
                     $('#telepon').val(data.telepon);
                     $('#gender').val(data.gender);
                 });
                 page = "edit";
                
         });
        

});




