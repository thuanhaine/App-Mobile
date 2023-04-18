package com.example.change_backgroundcolor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_red = (Button) findViewById(R.id.btn_red);
        Button btn_blue = (Button) findViewById(R.id.btn_blue);
        Button btn_green = (Button) findViewById(R.id.btn_green);
        Button btn_gray = (Button) findViewById(R.id.btn_gray);
        TextView result_tv = (TextView) findViewById(R.id.result_tv);

                btn_red.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        result_tv.setBackground(btn_red.getBackground());
                    }
                });

        btn_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result_tv.setBackground(btn_blue.getBackground());
            }
        });

        btn_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result_tv.setBackground(btn_green.getBackground());
            }
        });
        btn_gray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result_tv.setBackground(btn_gray.getBackground());
            }
        });
    }
}