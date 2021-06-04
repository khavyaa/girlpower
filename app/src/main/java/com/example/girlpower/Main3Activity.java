package com.example.girlpower;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    Button b1,b2,b3;
    SQLiteDatabase db;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        b1=(Button)findViewById(R.id.add);
        b2=(Button)findViewById(R.id.view);
        b3=(Button)findViewById(R.id.home);
        db=openOrCreateDatabase("MiniProject", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS GUARD(Name varchar(15), Num varchar(10));");
        cursor=db.rawQuery("SELECT * FROM GUARD", null);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cursor.getCount() <= 1) {
                    Intent i4=new Intent(getApplicationContext(), Main4Activity.class);
                    startActivity(i4);
                } else {
                    Toast.makeText(getApplicationContext(), "Number of guardians limit reached!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor=db.rawQuery("SELECT * FROM GUARD", null);
                if(cursor.getCount()==0)
                {
                    Toast.makeText(getApplicationContext(), "No records found!", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(cursor.moveToNext())
                {
                    buffer.append("Name: "+cursor.getString(cursor.getColumnIndex("Name"))+"\n");
                    buffer.append("Number: "+cursor.getString(cursor.getColumnIndex("Num"))+"\n");
                }
                showMessage("Guardian Details", buffer.toString());
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i5);
            }
        });
    }

    private void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
