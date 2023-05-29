package com.ck4u.ex3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    TextInputEditText edname, edemail, edphone;
    Button  btnSave, btnCancel;
    AppCompatButton btn;
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;
    SQLiteDatabase mydatabase;
    int SELECT_IMAGE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edname = findViewById(R.id.input_name);
        edemail = findViewById(R.id.input_email);
        edphone = findViewById(R.id.input_phone);
        btn = findViewById(R.id.btn);
        btnSave = findViewById(R.id.btnsave);
        btnCancel = findViewById(R.id.btncancel);
        imageView = findViewById(R.id.pickedImage);
        // tao list view
        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, R.layout.item_list, mylist);
        lv.setAdapter(myadapter);
        mydatabase = openOrCreateDatabase("qlsinhvien.db", MODE_PRIVATE, null);
        // tao table de chua du lieu
        try {
            String sql = "CREATE TABLE tbllop(name TEXT primary key, email TEXT, phone INTEGER)";
            mydatabase.execSQL(sql);
        }
        catch (Exception e) {
            Log.e("Error", "table da ton tai");
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edname.getText().toString();
                String email = edemail.getText().toString();
                int phone = Integer.parseInt(edphone.getText().toString());
                ContentValues myvalue = new ContentValues();
                myvalue.put("name", name);
                myvalue.put("email", email);
                myvalue.put("phone", phone);
                String msg = "";
                if(mydatabase.insert("tbllop", null, myvalue) == -1) {
                    msg = "KHONG LUU DC";
                } else  {
                    msg = "LUU THANH CONG";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Title"),SELECT_IMAGE_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1) {
            Uri uri = data.getData();
            imageView.setImageURI(uri);
        }
    }
}