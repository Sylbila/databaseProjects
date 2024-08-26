package com.example.projekbaru;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class halamanEdit extends AppCompatActivity {
DatabaseHelper dbHelper;
Button btnSimpan, btnBack;
EditText nim, nama, tanggal, JK, alamat;
String sqlText;
protected Cursor cursor;
private SQLiteOpenHelper dhHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_edit);
        dbHelper = new DatabaseHelper(this);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnBack = findViewById(R.id.btnBack);
        nim = findViewById(R.id.edtNim);
        nama = findViewById(R.id.edtNama);
        tanggal = findViewById(R.id.edtTanggal);
        JK = findViewById(R.id.edtJK);
        alamat = findViewById(R.id.edtAlamat);

        SQLiteDatabase db = dhHelper.getReadableDatabase();
        cursor = db.rawQuery("select * from data where nama = '" +
                getIntent().getStringExtra("nama")+"'",null);
        cursor.moveToFirst();
        {
            cursor.moveToPosition(0);
            nim.setText(cursor.getString(0).toString());
            nama.setText(cursor.getString(1).toString());
            tanggal.setText(cursor.getString(2).toString());
            JK.setText(cursor.getString(3).toString());
            alamat.setText(cursor.getString(4).toString());
        }
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                sqlText="update data set nama='"+ nama.getText().toString()+
                        "', tanggalLahir='" + tanggal.getText().toString()+
                        "', jk='" + JK.getText().toString()+
                        "', alamat='" + alamat.getText().toString()+
                        "', where nim='"+ nim.getText().toString()+"'";
                db.execSQL(sqlText);
                Toast.makeText(getApplicationContext(),
                        "perubahan data berhasil tersimpan", Toast.LENGTH_SHORT).show();
                finish();
                Intent pindah = new Intent(halamanEdit.this, halamanTampil.class);
                startActivity(pindah);
            }
        });
    }
}