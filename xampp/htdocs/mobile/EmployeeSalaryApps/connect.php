<?php

    define('HOST','localhost');
    define('USER','root');
    define('PASS','');
    define('DB','database_penggajian');

    //membuat koneksi dengan database
    $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

    mysqli_query($con,"ALTER TABLE tb_pegawai AUTO_INCREMENT = 1");
mysqli_query($con,"ALTER TABLE tb_golongan AUTO_INCREMENT = 1");

?>
