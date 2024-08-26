package com.example.projekbaru;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.content.Intent;

public class halamanTampil extends AppCompatActivity {
    Button kembali;
    String[] daftar;
    ListView ListView1;
    protected Cursor cursor;
    DatabaseHelper dbcenter;
    //===<<sesuaikan dengan nama activity anda>>===
    public static halamanTampil da;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_tampil);
        kembali = findViewById(R.id.btnBack);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(halamanTampil.this, MainActivity.class);
                startActivity(pindah);
            }
        });
        da = this;
        dbcenter = new DatabaseHelper(this);
        //====<<merefresh list untuk memanggil data terbaru>>==
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        // Membuka tabel, pastikan nama tabel sesuai pada tabel class DatabaseHelper
        Cursor cursor = db.rawQuery("SELECT * FROM data", null);

        // Proses menyalin isi database ke dalam listview
        String[] daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1); // Mengambil data dari kolom kedua
        }

        // Mendapatkan referensi ke ListView
        ListView ListView1 = findViewById(R.id.ListViewData);

        // Menyetel adapter untuk ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, daftar);
        ListView1.setAdapter(adapter);

        // Menambahkan OnItemClickListener untuk menampilkan AlertDialog
        ListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Mengambil data yang dipilih
                cursor.moveToPosition(position);
                final String nim = cursor.getString(0); // Mengambil data nim dari kolom pertama
                final String nama = cursor.getString(1); // Mengambil data nama dari kolom kedua
                final String tanggalLahir = cursor.getString(2); // Mengambil data tanggalLahir dari kolom ketiga
                final String jk = cursor.getString(3); // Mengambil data jk dari kolom keempat
                final String alamat = cursor.getString(4); // Mengambil data alamat dari kolom kelima

                Intent intent = new Intent(halamanTampil.this, halamanDetail.class);
                intent.putExtra("nim", nim);
                intent.putExtra("nama", nama);
                intent.putExtra("tanggalLahir", tanggalLahir);
                intent.putExtra("jk", jk);
                intent.putExtra("alamat", alamat);
                startActivity(intent);

                // Membuat AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(halamanTampil.this);
                builder.setTitle("Pilihan");
                builder.setMessage("Pilih aksi yang ingin dilakukan untuk " + nama);

                // Tombol Lihat Detail
                builder.setPositiveButton("Lihat Detail", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Membuat Intent untuk membuka Activity detail data
                        Intent pindah1 = new Intent(halamanTampil.this, halamanDetail.class); // buat halaman baru, yaitu halamanDetail
                        pindah1.putExtra("nim", nim);
                        pindah1.putExtra("nama", nama);
                        pindah1.putExtra("tanggalLahir", tanggalLahir);
                        pindah1.putExtra("jk", jk);
                        pindah1.putExtra("alamat", alamat);
                        startActivity(pindah1);
                    }
                });

                // Tombol Edit Data
                builder.setNeutralButton("Edit Data", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Membuat Intent untuk membuka Activity edit data
                        Intent pindah2 = new Intent(halamanTampil.this, halamanEdit.class); // buat halaman baru, yaitu halamanEdit
                        pindah2.putExtra("nim", nim);
                        pindah2.putExtra("nama", nama);
                        pindah2.putExtra("tanggalLahir", tanggalLahir);
                        pindah2.putExtra("jk", jk);
                        pindah2.putExtra("alamat", alamat);
                        startActivity(pindah2);
                    }
                });

                // Tombol Batal
                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();

                // Tombol Hapus Data
                AlertDialog.Builder deleteBuilder = new AlertDialog.Builder(halamanTampil.this);
                deleteBuilder.setTitle("Konfirmasi");
                deleteBuilder.setMessage("Apakah Anda yakin ingin menghapus data " + nama + "?");
                deleteBuilder.setPositiveButton("Hapus Data", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Menghapus data dari database
                        SQLiteDatabase db = dbcenter.getWritableDatabase();
                        db.execSQL("DELETE FROM data WHERE nim = '" + nim + "'");
                        RefreshList(); // Merefresh list setelah data dihapus
                    }
                });
                deleteBuilder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                deleteBuilder.create().show();
            }
        });

        // Menutup cursor
        cursor.close();
    }
}
