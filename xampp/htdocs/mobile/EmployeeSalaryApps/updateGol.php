<?php

    if($_SERVER['REQUEST_METHOD']=='POST'){

        //Mendapatkan Nilai Dari Variabel
        $id_golongan = $_POST['id_golongan'];
        $name_group = $_POST['name_group'];
		$sal_group = $_POST['sal_group'];
		$meal_allowance = $_POST['meal_allowance'];
		$family_allowance = $_POST['family_allowance'];
		$position_allowance = $_POST['position_allowance'];

        //Import File Koneksi Database
        require_once('connect.php');

        //Membuat SQL Query 
        $sql = "UPDATE tb_golongan SET nama_golongan = '$name_group', gaji_pokok = '$sal_group', tunjangan_makan = '$meal_allowance', tunjangan_keluarga = '$family_allowance', tunjangan_jabatan = '$position_allowance' WHERE id_golongan = $id_golongan;";

        //Mengupdate Database
        if(mysqli_query($con,$sql)){
            echo 'Berhasil Update Data Golongan';
        }else{
            echo 'Gagal Update Data Golongan';
        }

        mysqli_close($con);
    }
    
?>