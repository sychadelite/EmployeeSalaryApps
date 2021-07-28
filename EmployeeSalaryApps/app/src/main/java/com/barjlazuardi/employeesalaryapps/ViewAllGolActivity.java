package com.barjlazuardi.employeesalaryapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewAllGolActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_gol);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
    }

    private void showGolongan() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Configure.TAG_JSON_ARRAY_GOL);

            for(int i = 0; i<result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Configure.TAG_ID_GOLONGAN);
                String name = jo.getString(Configure.TAG_NAMA_GOLONGAN);

                HashMap<String, String> golongans = new HashMap<>();
                golongans.put(Configure.TAG_ID_GOLONGAN, id);
                golongans.put(Configure.TAG_NAMA_GOLONGAN, name);
                list.add(golongans);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ViewAllGolActivity.this, list, R.layout.list_item_gol,
                new String[]{Configure.TAG_ID_GOLONGAN, Configure.TAG_NAMA_GOLONGAN},
                new int[]{R.id.id, R.id.name});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewAllGolActivity.this, "Mengambil Data", "Mohon Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showGolongan();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Configure.URL_GET_ALL_GOL);
                return s;
            }
        }

        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ViewGolActivity.class);
        HashMap<String,String> map = (HashMap)parent.getItemAtPosition(position);
        String golId = map.get(Configure.TAG_ID_GOLONGAN).toString();
        intent.putExtra(Configure.GOL_ID,golId);
        startActivity(intent);
    }

    public void onBackPressed() {
        startActivity(new Intent(ViewAllGolActivity.this, GolonganActivity.class));
    }

}