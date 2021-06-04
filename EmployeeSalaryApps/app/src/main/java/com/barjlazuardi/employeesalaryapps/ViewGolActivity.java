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

public class ViewGolActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etIdGol, etNameGroup, etSalGroup, etMealAllow, etFamAllow, etPosAllow;
    private Button btnUpdate, btnDelete;

    private String id_golongan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_gol);

        Intent intent = getIntent();

        id_golongan = intent.getStringExtra(Configure.GOL_ID);

        etIdGol = (EditText) findViewById(R.id.et_id_golongan);
        etNameGroup = (EditText) findViewById(R.id.et_name_group);
        etSalGroup = (EditText) findViewById(R.id.et_sal_group);
        etMealAllow = (EditText) findViewById(R.id.et_meal_allowance);
        etFamAllow = (EditText) findViewById(R.id.et_family_allowance);
        etPosAllow = (EditText) findViewById(R.id.et_position_allowance);

        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnDelete = (Button) findViewById(R.id.btn_delete);

        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        etIdGol.setText(id_golongan);

        getGolongan();
    }

    private void getGolongan() {
        class GetGolongan extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewGolActivity.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showGolongan(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Configure.URL_GET_GOL,id_golongan);
                return  s;
            }
        }

        GetGolongan gg = new GetGolongan();
        gg.execute();
    }

    private void showGolongan(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Configure.TAG_JSON_ARRAY_GOL);
            JSONObject c = result.getJSONObject(0);
            String name_group = c.getString(Configure.TAG_NAMA_GOLONGAN);
            String sal_group = c.getString(Configure.TAG_GAJI_POKOK);
            String meal_allowance = c.getString(Configure.TAG_TUNJANGAN_MAKAN);
            String family_allowance = c.getString(Configure.TAG_TUNJANGAN_KELUARGA);
            String position_allowance = c.getString(Configure.TAG_TUNJANGAN_JABATAN);

            etNameGroup.setText(name_group);
            etSalGroup.setText(sal_group);
            etMealAllow.setText(meal_allowance);
            etFamAllow.setText(family_allowance);
            etPosAllow.setText(position_allowance);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateGolongan() {
        final String name_group = etNameGroup.getText().toString().trim();
        final String sal_group = etSalGroup.getText().toString().trim();
        final String meal_allowance = etMealAllow.getText().toString().trim();
        final String family_allowance = etFamAllow.getText().toString().trim();
        final String position_allowance = etPosAllow.getText().toString().trim();

        class UpdateGolongan extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewGolActivity.this, "Updating...", "Wait...", false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewGolActivity.this,s,Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Configure.KEY_GOL_ID_GOLONGAN,id_golongan);
                hashMap.put(Configure.KEY_GOL_NAMA_GOLONGAN,name_group);
                hashMap.put(Configure.KEY_GOL_GAJI_POKOK,sal_group);
                hashMap.put(Configure.KEY_GOL_TUNJANGAN_MAKAN,meal_allowance);
                hashMap.put(Configure.KEY_GOL_TUNJANGAN_KELUARGA,family_allowance);
                hashMap.put(Configure.KEY_GOL_TUNJANGAN_JABATAN,position_allowance);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Configure.URL_UPDATE_GOL,hashMap);

                return s;
            }
        }

        UpdateGolongan ug = new UpdateGolongan();
        ug.execute();

    }
    private void deleteGolongan(){
        class DeleteGolongan extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewGolActivity.this, "Updating...", "Tunggu...", false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewGolActivity.this,s,Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Configure.URL_DELETE_GOL, id_golongan);
                return s;
            }
        }

        DeleteGolongan dg = new DeleteGolongan();
        dg.execute();
    }

    private void confirmDeleteGolongan(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure want to delete this ?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                deleteGolongan();
                startActivity(new Intent(ViewGolActivity.this, ViewAllGolActivity.class));
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
            updateGolongan();
        }

        if (v == btnDelete){
            confirmDeleteGolongan();
        }

    }

    public void onBackPressed() {
        startActivity(new Intent(ViewGolActivity.this, ViewAllGolActivity.class));
    }

}