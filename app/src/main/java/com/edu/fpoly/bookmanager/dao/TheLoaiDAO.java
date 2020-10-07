package com.edu.fpoly.bookmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.edu.fpoly.bookmanager.database.DatabaseHelper;
import com.edu.fpoly.bookmanager.model.TheLoai;


public class TheLoaiDAO {
    private SQLiteDatabase db;
    private DatabaseHelper databaseHelper;
    public static final String TABLE_NAME = "TheLoai";
    public static final String SQL_THE_LOAI = "create table " +TABLE_NAME+"(" +
            "matheloai text PRIMARY KEY," +
            "tentheloai text," +
            "mota text," +
            "vitri text);";
    public static final String TAG = "TheLoaiDAO";

    public void TheLoaiDAO(Context context)
    {
        databaseHelper = new DatabaseHelper(context);
        db = databaseHelper.getWritableDatabase();
    }

    public int insertTheLoai(TheLoai tl)
    {
        ContentValues values = new ContentValues();
        values.put("matheloai",tl.getMaTL());
        values.put("tentheloai",tl.getTenTL());
        values.put("mota",tl.getMoTa());
        values.put("vitri",tl.getViTri());
        try
        {
            if(db.insert(TABLE_NAME,null,values)==-1)
            {
                return -1;
            }
        }
        catch (Exception e)
        {
            Log.e(TAG,e.toString());
        }
        return 1;
    }
}
