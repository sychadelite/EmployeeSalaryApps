<?php

    //Mendapatkan Nilai Dari Variabel ID Pegawai yang ingin ditampilkan
    $id_golongan = $_GET['id_golongan'];

    //Importing Database
    require_once('connect.php');

    //Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
    $sql = "SELECT * FROM tb_golongan WHERE id_golongan=$id_golongan";

    //Mendapatkan Hasil
    $r = mysqli_query($con,$sql);

    //Memasukkan Hasil Kedalam Array
    $result = array();
    $row = mysqli_fetch_array($r);
    array_push($result,array(
        "id_golongan"=>$row['id_golongan'],
        "name_group"=>$row['nama_golongan'],
        "sal_group"=>$row['gaji_pokok'],
        "meal_allowance"=>$row['tunjangan_makan'],
	    "family_allowance"=>$row['tunjangan_keluarga'],
        "position_allowance"=>$row['tunjangan_jabatan']
    ));

    //Menampilkan dalam format JSON
    echo json_encode(array('result'=>$result));

    mysqli_close($con);
    
?>