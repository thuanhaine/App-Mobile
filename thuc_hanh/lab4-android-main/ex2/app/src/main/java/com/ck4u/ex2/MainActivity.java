package com.ck4u.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRead, btnWrite;
    EditText editData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = findViewById(R.id.btnreaddata);
        btnWrite = findViewById(R.id.btnwritedata);
        editData = findViewById(R.id.edData);

        btnRead.setOnClickListener(this);
        btnWrite.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnreaddata)
        {
            readData();
        } else if (view.getId()==R.id.btnwritedata) {
            writeData();
        }
    }

    private void writeData() {
        try {
            FileOutputStream out = openFileOutput("ex2.txt", 0);
            OutputStreamWriter writer = new OutputStreamWriter(out);
            writer.write(editData.getText().toString());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readData() {
        try {
            FileInputStream in = openFileInput("ex2.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String data = "";
            StringBuilder builder = new StringBuilder();
            while ((data=reader.readLine())!= null){
                builder.append(data);
                builder.append("\n");
            }
            in.close();
            editData.setText(builder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}