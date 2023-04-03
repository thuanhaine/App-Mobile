package com.example.bt2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.net.Uri;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button callButton = (Button) findViewById(R.id.callButton);
        final EditText phoneNumber = (EditText) findViewById(R.id.phoneNumber);

        callButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v){
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + phoneNumber.getText()));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(callIntent);
            }
        });
    }


}