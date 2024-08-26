package com.example.projekbaru;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class halamanDetail extends AppCompatActivity {
    TextView nimm, namaa, tanggalLahirr, jenisKelaminn, alamatt;
    Button kembali;
    protected Cursor cursor;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_detail);
        //button kembali kehalaman tampil
        kembali = findViewById(R.id.btnBack);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(halamanDetail.this, halamanTampil.class);
                startActivity(pindah);
                finish();
            }
        });
        //deklarasi dbHelper
        dbHelper = new DatabaseHelper(this);
        //hubungkan variable xml
        nimm = findViewById(R.id.Nim);
        namaa = findViewById(R.id.Nama);
        tanggalLahirr = findViewById(R.id.Tanggal);
        jenisKelaminn = findViewById(R.id.JK);
        alamatt = findViewById(R.id.Alamat);
        //mendapatkan data dari halamanTambah
        Intent intent = getIntent();
        String nim = intent.getStringExtra("nim");
        String nama = intent.getStringExtra("nama");
        String tanggalLahir = intent.getStringExtra("tanggalLahir");
        String jk = intent.getStringExtra("jk");
        String alamat = intent.getStringExtra("alamat");
        // Menampilkan data di TextView
        nimm.setText(nim);
        namaa.setText(nama);
        tanggalLahirr.setText(tanggalLahir);
        jenisKelaminn.setText(jk);
        alamatt.setText(alamat);
    }
}