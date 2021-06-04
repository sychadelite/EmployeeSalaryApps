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

public class PegawaiActivity extends AppCompatActivity implements View.OnClickListener {

    //Perintah Mendefinisikan View
    private EditText etName, etDesg, etSalary, etDivision;
    private Button btnAdd, btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pegawai);

        etName = (EditText) findViewById(R.id.et_name);
        etDesg = (EditText) findViewById(R.id.et_desg);
        etSalary = (EditText) findViewById(R.id.et_salary);
        etDivision = (EditText) findViewById(R.id.et_division);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnView = (Button) findViewById(R.id.btn_view);

        //Setting listeners to button
        btnAdd.setOnClickListener(this);
        btnView.setOnClickListener(this);

    }

    //Perintah Menambahkan Pegawai (CREATE)
    private void addEmployee() {

        final String name = etName.getText().toString().trim();
        final String desg = etDesg.getText().toString().trim();
        final String salary = etSalary.getText().toString().trim();
        final String division = etDivision.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(PegawaiActivity.this, "Menambahkan...", "Tunggu...", false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(PegawaiActivity.this,s,Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Configure.KEY_EMP_NAMA,name);
                params.put(Configure.KEY_EMP_JABATAN,desg);
                params.put(Configure.KEY_EMP_GAJI,salary);
                params.put(Configure.KEY_EMP_DIVISI,division);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Configure.URL_ADD_EMP, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override

    public void onClick(View v) {
        if (v == btnAdd) {
            addEmployee();
        }

        if (v == btnView) {
            startActivity(new Intent(this, ViewAllEmpActivity.class));
        }

    }

    public void onBackPressed() {
        startActivity(new Intent(PegawaiActivity.this, MainActivity.class));
    }

}