package com.edu.fpoly.bookmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.edu.fpoly.bookmanager.dao.SachDAO;
import com.edu.fpoly.bookmanager.dao.TheLoaiDAO;
import com.edu.fpoly.bookmanager.dao.UserDAO;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "datasach";
    public static final int VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UserDAO.SQL_USER);
        sqLiteDatabase.execSQL(SachDAO.SQL_SACH);
        sqLiteDatabase.execSQL(TheLoaiDAO.SQL_THE_LOAI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + UserDAO.TABLE_NAME);
        sqLiteDatabase.execSQL("drop table if exists " + SachDAO.TABLE_NAME);
        sqLiteDatabase.execSQL("drop table if exists " + TheLoaiDAO.TABLE_NAME);
    }
}
