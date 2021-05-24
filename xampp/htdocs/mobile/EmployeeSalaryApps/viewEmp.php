<?php

    //Mendapatkan Nilai Dari Variabel ID Pegawai yang ingin ditampilkan
    $id = $_GET['id'];

    //Importing Database
    require_once('connect.php');

    //Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
    $sql = "SELECT * FROM tb_pegawai WHERE id=$id";

    //Mendapatkan Hasil
    $r = mysqli_query($con,$sql);

    //Memasukkan Hasil Kedalam Array
    $result = array();
    $row = mysqli_fetch_array($r);
    array_push($result,array(
        "id"=>$row['id'],
        "name"=>$row['nama'],
        "desg"=>$row['jabatan'],
        "salary"=>$row['gaji'],
	    "division"=>$row['divisi']
    ));

    //Menampilkan dalam format JSON
    echo json_encode(array('result'=>$result));

    mysqli_close($con);
    
?>