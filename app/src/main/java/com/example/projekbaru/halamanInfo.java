package com.example.projekbaru;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class halamanInfo extends AppCompatActivity {
Button kembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_info);
        kembali = findViewById(R.id.btnBack);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(halamanInfo.this, MainActivity.class);
                startActivity(pindah);
            }
        });
    }
}