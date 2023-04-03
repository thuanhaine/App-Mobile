package com.example.bt4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText edit_text = (EditText) findViewById(R.id.inputText);
        final AlertDialog ad = new AlertDialog.Builder(this).create();

        edit_text.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode == keyEvent.KEYCODE_ENTER || keyCode == keyEvent.KEYCODE_DPAD_CENTER){
                    String message = edit_text.getText().toString();
                    ad.setMessage(message);
                    ad.show();
                    return true;
                }
                return false;
            }
        });
    }
}