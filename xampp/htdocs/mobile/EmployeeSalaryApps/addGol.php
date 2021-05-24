<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Mendapatkan Nilai Variabel
		$name_group = $_POST['name_group'];
		$sal_group = $_POST['sal_group'];
		$meal_allowance = $_POST['meal_allowance'];
		$family_allowance = $_POST['family_allowance'];
		$position_allowance = $_POST['position_allowance'];

		//Pembuatan Syntax SQL
		$sql = "INSERT INTO tb_golongan (nama_golongan,gaji_pokok,tunjangan_makan,tunjangan_keluarga,tunjangan_jabatan) VALUES ('$name_group','$sal_group','$meal_allowance','$family_allowance','$position_allowance')";

		//Import File Koneksi database
		require_once('connect.php');

		//Eksekusi Query database
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Menambahkan Golongan';
		}else{
			echo 'Gagal Menambahkan Golongan';
		}

		mysqli_close($con);
	}
?>