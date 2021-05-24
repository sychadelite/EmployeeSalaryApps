package com.barjlazuardi.employeesalaryapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class GolonganActivity extends AppCompatActivity implements View.OnClickListener {

    //Perintah Mendefinisikan View
    private EditText etNameGroup, etSalGroup, etMealAllow, etFamAllow, etPosAllow;
    private Button btnAdd, btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_golongan);

        etNameGroup = (EditText) findViewById(R.id.et_name_group);
        etSalGroup = (EditText) findViewById(R.id.et_sal_group);
        etMealAllow = (EditText) findViewById(R.id.et_meal_allowance);
        etFamAllow = (EditText) findViewById(R.id.et_family_allowance);
        etPosAllow = (EditText) findViewById(R.id.et_position_allowance);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnView = (Button) findViewById(R.id.btn_view);

        //Setting listeners to button
        btnAdd.setOnClickListener(this);
        btnView.setOnClickListener(this);

    }

    //Perintah Menambahkan Pegawai (CREATE)
    private void addGolongan() {

        final String name_group = etNameGroup.getText().toString().trim();
        final String sal_group = etSalGroup.getText().toString().trim();
        final String meal_allowance = etMealAllow.getText().toString().trim();
        final String family_allowance = etFamAllow.getText().toString().trim();
        final String position_allowance = etPosAllow.getText().toString().trim();

        class AddGolongan extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(GolonganActivity.this, "Menambahkan...", "Tunggu...", false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(GolonganActivity.this,s,Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Configure.KEY_GOL_NAMA_GOLONGAN,name_group);
                params.put(Configure.KEY_GOL_GAJI_POKOK,sal_group);
                params.put(Configure.KEY_GOL_TUNJANGAN_MAKAN,meal_allowance);
                params.put(Configure.KEY_GOL_TUNJANGAN_KELUARGA,family_allowance);
                params.put(Configure.KEY_GOL_TUNJANGAN_JABATAN,position_allowance);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Configure.URL_ADD_GOL, params);
                return res;
            }
        }

        AddGolongan ag = new AddGolongan();
        ag.execute();
    }

    @Override

    public void onClick(View v) {
        if (v == btnAdd) {
            addGolongan();
        }

        if (v == btnView) {
            startActivity(new Intent(this, ViewAllGolActivity.class));
        }

    }

    public void onBackPressed() {
        startActivity(new Intent(GolonganActivity.this, MainActivity.class));
    }

}