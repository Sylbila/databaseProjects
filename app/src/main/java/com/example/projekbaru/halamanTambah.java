package com.example.projekbaru;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

public class halamanTambah extends AppCompatActivity {
    protected Cursor cursor;
    DatabaseHelper dbHelper;
EditText nim, nama, tanggalLahir, jenisKelamin, alamat;
Button simpan, kembali, hapus;
String sqlText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_tambah);
        dbHelper = new DatabaseHelper(this);
        nim = findViewById(R.id.edtNim);
        nama = findViewById(R.id.edtNama);
        tanggalLahir = findViewById(R.id.edtTanggal);
        jenisKelamin = findViewById(R.id.edtJK);
        alamat = findViewById(R.id.edtAlamat);
        simpan = findViewById(R.id.btnSimpan);
        kembali = findViewById(R.id.btnBack);
        hapus = findViewById(R.id.btnClear);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nimText = nim.getText().toString();
                String namaText = nama.getText().toString();
                String tanggalLahirText = tanggalLahir.getText().toString();
                String jenisKelaminText = jenisKelamin.getText().toString();
                String alamatText = alamat.getText().toString();

                // Validasi inputan
                if (nimText.isEmpty() || namaText.isEmpty() || tanggalLahirText.isEmpty() || jenisKelaminText.isEmpty() || alamatText.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Harap mengisi inputan secara lengkap.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Menyimpan data ke database
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("nim", nimText);
                values.put("nama", namaText);
                values.put("tanggalLahir", tanggalLahirText);
                values.put("jk", jenisKelaminText);
                values.put("alamat", alamatText);
                // Menyisipkan data dan mendapatkan ID baris baru
                long newRowId = db.insert("data", null, values);
                // Cek apakah penyisipan berhasil atau gagal
                if (newRowId == -1) {
                    Toast.makeText(getApplicationContext(), "Gagal menyimpan data.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Data berhasil disimpan.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nim.setText("");
                nama.setText("");
                tanggalLahir.setText("");
                jenisKelamin.setText("");
                alamat.setText("");
                Toast.makeText(getApplicationContext(), "Data berhasil di Hapus", Toast.LENGTH_SHORT).show();
                return;
            }
        });
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(halamanTambah.this, MainActivity.class);
                startActivity(pindah);
            }
        });
    }
}