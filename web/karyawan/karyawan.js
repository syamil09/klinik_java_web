/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    
        var nik, nama, gender, tmpLahir, tglLahir, alamat, telepon, page;
        console.log(sessionStorage.getItem("data"))
        // save value from view to variable        
        function getInputValue(){
            id = $("#id_karyawan").val();
            nama = $("#nama").val();
            gender = $("#jenkel").children("option:selected").val();
            pekerjaan = $("#pekerjaan").val();
            tglLahir = $("#tgl_lahir").val();
            alamat = $("#alamat").val();
            nohp = $("#no_hp").val();
            noKtp = $("#no_ktp").val();
            email = $("#email").val();
            npwp = $("#npwp").val();
            tmpLahir = $("#tmpLahir").val();
        }
        
        // procedure add data
        $("#btnAdd").click(function(){
                $("#myModal").modal('show');
                $("#titel1").show();
                $("#titel2").hide();
                $("#nik").prop('disabled', false);
                page="tambah";
                console.log(page);
        });    
        
        // save data
        $("#btnSave").on('click', function(){
    	getInputValue();
    	
    	if (nama === "") {
                        alert("Nama Karyawan Harus Diisi!!");
                        $("#nama").focus();
    	}
        else if (tglLahir === "") {
                        alert("Tgl Lahir harus diisi");
                        $("#tgl_lahir").focus();
    	}
        else if (email === "") {
                        alert("email harus diisi");
                        $("#email").focus();
    	}
    	else{
                 console.log(gender, noKtp)                 
                 console.log(pekerjaan, tglLahir)
                 console.log("page : "+page)
            $.post('/PBO_klinik/KaryawanCtr', {
    		page: page,
    		id: nik,
    		nama: nama,
    		jenisKelamin: gender,
    		pekerjaan: pekerjaan,
    		tglLahir: tglLahir,
    		alamat: alamat,
    		noHp: nohp,
                noKtp: noKtp,
                noNpwp: npwp,
                email: email
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
        url: "/PBO_klinik/KaryawanCtr?page=active", 
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
                            {'data': 'idKaryawan', 'name': 'idKaryawan', 'type': 'string'},
                            {'data': 'nama'},
                            {'data': 'tglLahir', className: 'text-center'},
                            {'data': 'bidangPekerjaan'},
                            {'data': 'email'},    
                            {'data': 'noKtp'},
                            {'data': 'jenisKelamin'},
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
         $('#tabelkaryawan tbody').on('click', '#btnDel', function() {
             // get nik when clicked btn in the current row
             let baris = $(this).closest('tr');
             let nik = baris.find("td:eq(0)").text();
             let nama = baris.find("td:eq(1)").text();
             page = 'hapus';
             
             if (confirm(`Anda yakin data  : ${nik} - ${nama} akan dihapus ?`)) {
                 $.post("/PBO_koperasi/KaryawanCtr", {
                        page: page,
                        nik: nik
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
                $("#nik").prop('disabled', true);
                page = "tampil";
                
                let baris = $(this).closest('tr');
                let id = baris.find("td:eq(0)").text();
                $.post('/PBO_klinik/KaryawanCtr', {
                        page: page,
                        id: id
                 },
                 function(data, status) {
                     console.log(data);
                     $("#container_id").removeClass("d-none");
                     $("#id_karyawan").prop('disabled', true);
                     $('#id_karyawan').val(data.idKaryawan);
                     $('#nama').val(data.nama);
                     $('#npwp').val(data.noNpwp);
                     $('#tgl_lahir').val(data.tglLahir);
                     $('#alamat').val(data.alamat);
                     $('#no_hp').val(data.noHp);
                     $('#jenkel').val(data.jenisKelamin);
                     $("#email").val(data.email);
                     $("#no_ktp").val(data.noKtp);
                     $("#pekerjaan").val(data.bidangPekerjaan);
                 });
                 page = "edit";
                
         });
         
});




