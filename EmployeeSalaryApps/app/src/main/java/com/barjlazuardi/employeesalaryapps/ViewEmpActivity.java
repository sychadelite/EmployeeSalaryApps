package com.barjlazuardi.employeesalaryapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ViewEmpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etId, etName, etDesg, etSalary, etDivision;
    private Button btnUpdate, btnDelete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_emp);

        Intent intent = getIntent();

        id = intent.getStringExtra(Configure.EMP_ID);

        etId = (EditText) findViewById(R.id.et_id);
        etName = (EditText) findViewById(R.id.et_name);
        etDesg = (EditText) findViewById(R.id.et_desg);
        etSalary = (EditText) findViewById(R.id.et_salary);
        etDivision = (EditText) findViewById(R.id.et_division);

        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnDelete = (Button) findViewById(R.id.btn_delete);

        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        etId.setText(id);

        getEmployee();
    }

    private void getEmployee() {
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewEmpActivity.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Configure.URL_GET_EMP,id);
                return  s;
            }
        }

        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Configure.TAG_JSON_ARRAY_EMP);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(Configure.TAG_NAMA);
            String desg = c.getString(Configure.TAG_JABATAN);
            String salary = c.getString(Configure.TAG_GAJI);
            String division = c.getString(Configure.TAG_DIVISI);

            etName.setText(name);
            etDesg.setText(desg);
            etSalary.setText(salary);
            etDivision.setText(division);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateEmployee() {
        final String name = etName.getText().toString().trim();
        final String desg = etDesg.getText().toString().trim();
        final String salary = etSalary.getText().toString().trim();
        final String division = etDivision.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewEmpActivity.this, "Updating...", "Wait...", false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewEmpActivity.this,s,Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Configure.KEY_EMP_ID,id);
                hashMap.put(Configure.KEY_EMP_NAMA,name);
                hashMap.put(Configure.KEY_EMP_JABATAN,desg);
                hashMap.put(Configure.KEY_EMP_GAJI,salary);
                hashMap.put(Configure.KEY_EMP_DIVISI,division);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Configure.URL_UPDATE_EMP,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();

    }
    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewEmpActivity.this, "Updating...", "Tunggu...", false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewEmpActivity.this,s,Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Configure.URL_DELETE_EMP, id);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure want to delete this ?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                deleteEmployee();
                startActivity(new Intent(ViewEmpActivity.this, ViewAllEmpActivity.class));
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == btnUpdate){
            updateEmployee();
        }

        if (v == btnDelete){
            confirmDeleteEmployee();
        }

    }

    public void onBackPressed() {
        startActivity(new Intent(ViewEmpActivity.this, ViewAllEmpActivity.class));
    }

}