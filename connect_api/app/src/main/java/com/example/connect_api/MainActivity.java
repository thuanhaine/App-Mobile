package com.example.connect_api;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.connect_api.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.Console;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText userEd;
    TextView outputName, outputEmail,outputPhone;
    Button btnFetch;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEd = findViewById(R.id.user_et);
        outputName = findViewById(R.id.output_name_tv);
        outputEmail = findViewById(R.id.output_email_tv);
        outputPhone = findViewById(R.id.output_phone_tv);
        btnFetch = findViewById(R.id.btn_fet);

        btnFetch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.btn_fet){
            userName = userEd.getText().toString();
            try {
                getData();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getData( ) throws MalformedURLException {
        Uri uri = Uri.parse("https://jsonplaceholder.typicode.com/users").buildUpon().build();
        URL url = new URL(uri.toString());
        new DOTask().execute(url);
    }
    class DOTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL ... urls) {
            URL url = urls [0];
            String data = null;
            try{
                data = NetworkUtils.makeHTTPRequest(url);
            }catch (IOException e){
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s){
            try{
                parseJon(s);
            }
            catch(JSONException e){
                e.printStackTrace();
            }
        }
        public void parseJon (String data) throws JSONException{
            JSONObject jsonObject = null;
            try {
//                jsonObject = new JSONObject(" {\"data\": " + data + "}");
                System.out.println("hehe"+data);
//                JSONArray dataArray = jsonObject.getJSONArray("data");
                JSONArray dataArray = new JSONArray(data);
                for (int i = 0; i<dataArray.length();i++){
                    JSONObject dataItem = dataArray.getJSONObject(i);
                    String userN = dataItem.get("username").toString();
                    if(userN.equals(userName)){
                        outputName.setText(dataItem.get("name").toString());
                        outputEmail.setText(dataItem.get("email").toString());
                        outputPhone.setText(dataItem.get("phone").toString());
                        break;
                    }
                    else {
                        outputName.setText("Not found");
                    }
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

        }
    }
}