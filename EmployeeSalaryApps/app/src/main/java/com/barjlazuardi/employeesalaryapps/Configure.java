package com.barjlazuardi.employeesalaryapps;

public class Configure {
    // Pegawai
    public static final String URL_ADD_EMP = "http://192.168.0.19/mobile/EmployeeSalaryApps/addEmp.php";
    public static final String URL_GET_ALL_EMP = "http://192.168.0.19/mobile/EmployeeSalaryApps/viewAllEmp.php";
    public static final String URL_GET_EMP = "http://192.168.0.19/mobile/EmployeeSalaryApps/viewEmp.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.0.19/mobile/EmployeeSalaryApps/updateEmp.php";
    public static final String URL_DELETE_EMP = "http://192.168.0.19/mobile/EmployeeSalaryApps/deleteEmp.php?id=";

    // Golongan
    public static final String URL_ADD_GOL = "http://192.168.0.19/mobile/EmployeeSalaryApps/addGol.php";
    public static final String URL_GET_ALL_GOL = "http://192.168.0.19/mobile/EmployeeSalaryApps/viewAllGol.php";
    public static final String URL_GET_GOL = "http://192.168.0.19/mobile/EmployeeSalaryApps/viewGol.php?id_golongan=";
    public static final String URL_UPDATE_GOL = "http://192.168.0.19/mobile/EmployeeSalaryApps/updateGol.php";
    public static final String URL_DELETE_GOL = "http://192.168.0.19/mobile/EmployeeSalaryApps/deleteGol.php?id_golongan=";

    //Kunci yang akan digunakan untuk mengirim permintaan ke Script PHP
    // Pegawai
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAMA = "name";
    public static final String KEY_EMP_JABATAN = "desg"; //desg adalah variabel jabatan
    public static final String KEY_EMP_GAJI = "salary"; //salary adalah variabel gaji
    public static final String KEY_EMP_DIVISI = "division"; //division adalah variabel divisi

    // Golongan
    public static final String KEY_GOL_ID_GOLONGAN = "id_golongan";
    public static final String KEY_GOL_NAMA_GOLONGAN = "name_group";
    public static final String KEY_GOL_GAJI_POKOK = "sal_group"; //desg adalah variabel jabatan
    public static final String KEY_GOL_TUNJANGAN_MAKAN = "meal_allowance"; //meal_allowance adalah variabel tunjangan_makan
    public static final String KEY_GOL_TUNJANGAN_KELUARGA = "family_allowance"; //family_allowance adalah variabel tunjangan_keluarga
    public static final String KEY_GOL_TUNJANGAN_JABATAN = "position_allowance"; //family_allowance adalah variabel tunjangan_keluarga

    //JSON Tags
    // Pegawai
    public static final String TAG_JSON_ARRAY_EMP = "result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "name";
    public static final String TAG_JABATAN = "desg";
    public static final String TAG_GAJI = "salary";
    public static final String TAG_DIVISI = "division";

    // Golongan
    public static final String TAG_JSON_ARRAY_GOL = "result";
    public static final String TAG_ID_GOLONGAN = "id_golongan";
    public static final String TAG_NAMA_GOLONGAN = "name_group";
    public static final String TAG_GAJI_POKOK = "sal_group";
    public static final String TAG_TUNJANGAN_MAKAN = "meal_allowance";
    public static final String TAG_TUNJANGAN_KELUARGA = "family_allowance";
    public static final String TAG_TUNJANGAN_JABATAN = "position_allowance";

    //ID Pegawai
    //EMP Adalah singkatan Employee
    public static final String EMP_ID = "emp_id";

    //ID Golongan
    //GOL Adalah singkatan Employee
    public static final String GOL_ID = "gol_id";

}
