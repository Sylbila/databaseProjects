package com.example.projekbaru;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    //===<<mendeklarasikan nama database dan versinya dalam string>>===
    private static final String database_name = "dataMhs.db";
    private static final int database_version = 1;

    //===<<Membuat koneksi database>>===
    public DatabaseHelper(Context context) {
        super(context, database_name, null, database_version);
    }

    @Override
    //===<<perintah untuk membuat tabel dan memasukkan data awal>>===
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table data (nim text primary key, nama text null, "
                +
                "tanggalLahir text null, jk text null, alamat text null);";
        Log.d("data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO data (nim, nama, tanggalLahir, jk, alamat) " +
                " VALUES ('2201010695', 'sylbila', '2004-09-10', 'wanita','tabanan');";
        db.execSQL(sql);
        sql = "INSERT INTO data (nim, nama, tanggalLahir, jk, alamat) " +
                " VALUES ('2201010677', 'najla', '2004-10-09', 'wanita','denpasar');";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("data", "Upgrading database from version " + oldVersion + " to " + newVersion);
        // Menghapus tabel lama jika ada
        db.execSQL("DROP TABLE IF EXISTS data");
        // Membuat tabel baru
        onCreate(db);
    }
}

