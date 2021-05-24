<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Mendapatkan Nilai Variabel
		$name = $_POST['name'];
		$desg = $_POST['desg'];
		$sal = $_POST['salary'];
		$div = $_POST['division'];

		//Pembuatan Syntax SQL
		$sql = "INSERT INTO tb_pegawai (nama,jabatan,gaji,divisi) VALUES ('$name','$desg','$sal','$div')";

		//Import File Koneksi database
		require_once('connect.php');

		//Eksekusi Query database
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Menambahkan Pegawai';
		}else{
			echo 'Gagal Menambahkan Pegawai';
		}

		mysqli_close($con);
	}
?>