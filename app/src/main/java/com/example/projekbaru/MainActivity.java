package com.example.projekbaru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
Button tampil, input, info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tampil = findViewById(R.id.btnTampil);
        input = findViewById(R.id.btnInput);
        info = findViewById(R.id.btnInfo);
        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //halaman yg sekarang         //halaman tujuan
                Intent pindah = new Intent(MainActivity.this, halamanTampil.class);
                startActivity(pindah);
            }
        });
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //halaman yg sekarang         //halaman tujuan
                Intent pindah2 = new Intent(MainActivity.this, halamanTambah.class);
                startActivity(pindah2);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //halaman yg sekarang         //halaman tujuan
                Intent pindah3 = new Intent(MainActivity.this, halamanInfo.class);
                startActivity(pindah3);
            }
        });

    }
}