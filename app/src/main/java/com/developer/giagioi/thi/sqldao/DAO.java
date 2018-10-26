package com.developer.giagioi.thi.sqldao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.developer.giagioi.thi.database.Constant;
import com.developer.giagioi.thi.database.DatabaseHelper;
import com.developer.giagioi.thi.model.Oto;

import java.util.ArrayList;
import java.util.List;

public class DAO implements Constant {
    private SQLiteDatabase database;
    private final DatabaseHelper databaseHelper;

    public DAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void insertOto(Oto oto) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_COLUMN_NAME, oto.getName());
        values.put(TB_Add, oto.getNam());

        sqLiteDatabase.insert(TABLE_NAME, null, values);
        Log.d("insert", "insert ");

        sqLiteDatabase.close();
    }
    public int updateOto(Oto oto) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TB_COLUMN_NAME, oto.getName());
        contentValues.put(TB_Add, oto.getNam());
        return sqLiteDatabase.update(TABLE_NAME,
                contentValues, TB_COLUMN_ID + "=?",
                new String[]{String.valueOf(oto.getID())});

    }
    public int deleteOto(String id) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,
                TB_COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }
    public List<Oto> getAllOto(){
        List<Oto> otos = new ArrayList<>();
        String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL, null);
        if (cursor.moveToFirst()) {
            do {
                Oto oto = new Oto();
                oto.setID(cursor.getString(0));
                oto.setName(cursor.getString(1) + "");
                oto.setNam(cursor.getString(2));
                otos.add(oto);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return otos;
    }
//    public Oto getTypeBookByID(String typeID) {
//        Oto typeBook = null;
//        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
//
//        Cursor cursor = sqLiteDatabase.query(TABLE_TYPE_BOOK, new String[]{TB_COLUMN_ID, TB_COLUMN_NAME, TB_COLUMN_DES, TB_COLUMN_POS},
//                TB_COLUMN_ID + "=?", new String[]{typeID}, null, null, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            String id = cursor.getString(1);
//            String name = cursor.getString(2);
//            String des = cursor.getString(3);
//            String pos = cursor.getString(4);
//
//            typeBook = new Oto();
//            typeBook.setID(id);
//            typeBook.setName(name);
//            typeBook.setDes(des);
//            typeBook.setPos(pos);
//        }
//        return typeBook;
//
//    }
}
