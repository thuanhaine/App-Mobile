package com.ck4u.ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    Button readData;
    EditText editData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readData = findViewById(R.id.btnreaddata);
        editData = findViewById(R.id.edData);

        readData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data;
                @SuppressLint("ResourceType") InputStream in = getResources().openRawResource(R.raw.myfile);
                InputStreamReader inreader = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(inreader);
                StringBuilder builder = new StringBuilder();
                if(in!= null) {
                    try {
                        while ((data=bufferedReader.readLine()) != null)
                        {
                            builder.append(data);
                            builder.append("\n");
                        }
                        in.close();
                        editData.setText(builder.toString());
                    }
                    catch (IOException ex) {
                        Log.e("ERROR", ex.getMessage());
                    }
                }
            }
        });
    }
}