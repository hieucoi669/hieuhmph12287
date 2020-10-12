package com.edu.fpoly.bookmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.edu.fpoly.bookmanager.database.DatabaseHelper;
import com.edu.fpoly.bookmanager.model.TheLoai;

import java.util.ArrayList;
import java.util.List;


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

    public TheLoaiDAO(Context context)
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

    public List<TheLoai> getAllTheLoai()
    {
        List<TheLoai> listTL = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false)
        {
            TheLoai tl = new TheLoai();
            tl.setMaTL(cursor.getString(0));
            tl.setTenTL(cursor.getString(1));
            tl.setMoTa(cursor.getString(2));
            tl.setViTri(cursor.getString(3));
            listTL.add(tl);
            cursor.moveToNext();
        }
        cursor.close();
        return listTL;
    }

    public int deleteTheLoai(String maTL)
    {
        int kq = db.delete(TABLE_NAME, "maTL=?", new String[]{maTL});
        if(kq==0)
        {
            return -1;
        }
        return 1;
    }

    public int updateTheLoai(TheLoai tl)
    {
        ContentValues values = new ContentValues();
        values.put("maTL", tl.getMaTL());
        values.put("tenTL", tl.getTenTL());
        values.put("moTa", tl.getMoTa());
        values.put("viTri" ,tl.getViTri());
        int kq = db.update(TABLE_NAME,values,"maTL=?", new String[]{tl.getMaTL()});
        if(kq==0)
        {
            return -1;
        }
        return 1;
    }
}
