package com.developer.giagioi.thi.database;

public interface Constant {


    String DATABASE_NAME = "BangOto ";
    String TABLE_NAME = "Oto ";

    String TB_COLUMN_ID = "MaOto ";
    String TB_COLUMN_NAME = "NhanHieu ";
    String TB_Add = "NamSanXuat ";

    String CREATE_TABLE_NAME = " CREATE TABLE " + TABLE_NAME + "(" +
            TB_COLUMN_ID + " VARCHAR PRIMARY KEY, " +
            TB_COLUMN_NAME + " VARCHAR, " +
            TB_Add + " VARCHAR " +
            ")";
}
