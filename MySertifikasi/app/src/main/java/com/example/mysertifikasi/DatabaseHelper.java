package com.example.mysertifikasi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String table_pemasukan = "pemasukan";
    public static final String id_pemasukan = "id";
    public static final String nominal = "nominal";
    public static final String keterangan = "keterangan";
    public static final String tanggal_pm = "tanggal_pemasukan";

    public static final String table_pengeluaran = "pengeluaran";
    public static final String id_pengeluaran = "id";
    public static final String nominal_pengeluaran = "nominal_pengeluaran";
    public static final String keterangan_pengeluaran = "keterangan_pengeluaran";
    public static final String tanggal_pg = "tanggal_pengeluaran";

    private SQLiteDatabase db;
    public DatabaseHelper(Context context) {
        super(context, "sertifikasi.db", null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE session(id integer PRIMARY KEY, login text)");
//        db.execSQL("CREATE TABLE user(id integer PRIMARY KEY AUTOINCREMENT, username text, password text)");
        String query = "CREATE TABLE " + table_pemasukan + "(" + id_pemasukan + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + nominal + " DOUBLE," + keterangan + " TEXT," + tanggal_pm + " TEXT)";
        db.execSQL(query);
        String query_pg = "CREATE TABLE " + table_pengeluaran + "(" + id_pengeluaran + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + nominal_pengeluaran + " DOUBLE," + keterangan_pengeluaran + " TEXT," + tanggal_pg + " TEXT)";
        db.execSQL(query_pg);
//        db.execSQL("INSERT INTO session(id, login) VALUES(1, 'kosong')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS session");
//        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS "+ table_pemasukan);
        db.execSQL("DROP TABLE IF EXISTS "+ table_pengeluaran);
        onCreate(db);
    }

    //check session
//    public Boolean checkSession(String sessionValues) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM session WHERE login = ?", new String[]{sessionValues});
//        if (cursor.getCount() > 0) {
//            return true;
//        }
//        else {
//            return false;
//        }
//    }

    //upgradde session
//    public Boolean upgradeSession(String sessionValues, int id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("login", sessionValues);
//        long update = db.update("session", contentValues, "id="+id, null);
//        if (update == -1) {
//            return false;
//        }
//        else {
//            return true;
//        }
//    }

    //insert pemasukan
    public void insertPemasukan(ContentValues values) {
        db.insert(table_pemasukan, null, values);
    }

    //insert pengeluaran
    public void insertPengeluaran(ContentValues values) {
        db.insert(table_pengeluaran, null, values);
    }

    //insert user
//    public Boolean insertUser(String username, String password) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("username", username);
//        contentValues.put("password", password);
//        long insert = db.insert("user", null, contentValues);
//        if (insert == -1) {
//            return false;
//        }
//        else {
//            return true;
//        }
//    }

    // detail
    Cursor readPemasukan(){
        String query = "select id, nominal as nominal, keterangan as keterangan, tanggal_pemasukan as tanggal," +
                " '[ + ]' as status, 0 as panah from pemasukan union select id, nominal_pengeluaran as nominal, " +
                "keterangan_pengeluaran as keterangan, tanggal_pengeluaran as tanggal, '[ - ]' as status, 1 as panah from pengeluaran";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public ArrayList<DetailFLow> detail(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select id, nominal as nominal, keterangan as keterangan, tanggal_pemasukan as tanggal," +
                " '[ + ]' as status, 0 as panah from pemasukan union select id, nominal_pengeluaran as nominal, " +
                "keterangan_pengeluaran as keterangan, tanggal_pengeluaran as tanggal, '[ - ]' as status, 1 as panah from pengeluaran", null);

        ArrayList<DetailFLow> DetailArrayList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                DetailArrayList.add(new DetailFLow(cursor.getString(0),
                        cursor.getDouble(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return DetailArrayList;
    }

    Cursor readPengeluaran(){
        String query = "SELECT * FROM " + table_pengeluaran;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    // total pemasukan
    public int totalPemasukan(){
        int result = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM ("+nominal+") FROM "+ table_pemasukan, null);
        if (cursor.moveToFirst()) result = cursor.getInt(0);
        return result;
    }

    //total pengeluaran
    public int totalPengeluaran() {
        int result = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM ("+nominal_pengeluaran+") FROM "+ table_pengeluaran, null);
        if (cursor.moveToFirst()) result = cursor.getInt(0);
        return result;
    }

    //select password
//    public int showPassword() {
//        int result = 0;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT password FROM user", null);
//        if (cursor.moveToFirst()) result = cursor.getInt(0);
//        return result;
//    }

    //update password
//    public void updatePassword(String password, String passbaru){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put("password", password);
//
//        db.update("user", values, "password = ?", new String[]{passbaru});
//        db.close();
//    }

//    public void updatePass(String newPass){
//        Cursor cur = db.rawQuery("UPDATE user SET password = '"+newPass+"' WHERE password = ?", new String[]{newPass});
//    }

    // check login
//    public Boolean checkLogin(String username, String password) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ? AND password = ?", new String[]{username, password});
//        if (cursor.getCount() > 0 ) {
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
}
