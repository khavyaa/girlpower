package com.example.girlpower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {

    EditText e1,e2;
    Button b1,b2;
    SQLiteDatabase db;
    String gname,gno;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        e1=(EditText)findViewById(R.id.name);
        e2=(EditText)findViewById(R.id.num);
        b1=(Button)findViewById(R.id.add_guard);
        b2=(Button)findViewById(R.id.back);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openOrCreateDatabase("MiniProject", MODE_PRIVATE, null);
                db.execSQL("CREATE TABLE IF NOT EXISTS GUARD(Name varchar(15), Num varchar(10));");
                gname=e1.getText().toString();
                gno=e2.getText().toString();
                db.execSQL("INSERT INTO GUARD VALUES('"+gname+"','"+gno+"');");
                Toast.makeText(getApplicationContext(), "Guardian saved successfully.", Toast.LENGTH_SHORT).show();
                e1.setText(null);
                e2.setText(null);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i6=new Intent(getApplicationContext(), Main3Activity.class);
                startActivity(i6);
            }
        });
    }
}
