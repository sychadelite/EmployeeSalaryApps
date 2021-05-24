<?php

    //Mendapatkan Nilai ID
    $id = $_GET['id'];

    //Import File Koneksi Database
    require_once('connect.php');

    //Membuat SQL Query
    $sql = "DELETE FROM tb_pegawai WHERE id = $id;";

    //Menghapus Nilai Pada Database
    if(mysqli_query($con,$sql)){
        echo 'Berhasil Menghapus Pegawai';
    }else{
        echo 'Gagal Menghapus Pegawai';
    }

    mysqli_close($con);

?>