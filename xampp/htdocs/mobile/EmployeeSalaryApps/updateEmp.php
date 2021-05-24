<?php

    if($_SERVER['REQUEST_METHOD']=='POST'){

        //Mendapatkan Nilai Dari Variabel
        $id = $_POST['id'];
        $name = $_POST['name'];
	    $desg = $_POST['desg'];
	    $sal = $_POST['salary'];
    	$div = $_POST['division'];

        //Import File Koneksi Database
        require_once('connect.php');

        //Membuat SQL Query 
        $sql = "UPDATE tb_pegawai SET nama = '$name', jabatan = '$desg', gaji = '$sal', divisi = '$div' WHERE id = $id;";

        //Mengupdate Database
        if(mysqli_query($con,$sql)){
            echo 'Berhasil Update Data Pegawai';
        }else{
            echo 'Gagal Update Data Pegawai';
        }

        mysqli_close($con);
    }
    
?>