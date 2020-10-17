package com.edu.fpoly.bookmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.edu.fpoly.bookmanager.database.DatabaseHelper;
import com.edu.fpoly.bookmanager.model.Sach;
import com.edu.fpoly.bookmanager.model.TheLoai;
import com.edu.fpoly.bookmanager.model.User;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "Sach";
    public static final String SQL_SACH = "create table "+TABLE_NAME+"(" +
            "masach text PRIMARY KEY," +
            "tensach text," +
            "tentheloai text," +
            "tacgia text," +
            "nxb text," +
            "giabia text," +
            "soluong text);";

    public SachDAO(Context context)
    {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<Sach> getAllSach()
    {
        List<Sach> listS = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Sach s = new Sach();
            s.setMasach(cursor.getString(0));
            s.setTensach(cursor.getString(1));
            s.setTentheloai(cursor.getString(2));
            s.setTacgia(cursor.getString(3));
            s.setNxb(cursor.getString(4));
            s.setGiabia(cursor.getString(5));
            s.setSoluong(cursor.getString(6));
            listS.add(s);
            cursor.moveToNext();
        }
        cursor.close();
        return listS;
    }

    public int insertSach(Sach sach)
    {
        ContentValues values = new ContentValues();
        values.put("masach",sach.getMasach());
        values.put("tensach",sach.getTensach());
        values.put("tentheloai",sach.getTentheloai());
        values.put("tacgia",sach.getTacgia());
        values.put("nxb",sach.getNxb());
        values.put("giabia",sach.getGiabia());
        values.put("soluong",sach.getSoluong());
        try
        {
            if(db.insert(TABLE_NAME,null,values)==-1)
            {
                return -1;
            }
        }
        catch (Exception e)
        {

        }
        return 1;
    }

    public int deleteSach(String tensach)
    {
        int kq = db.delete(TABLE_NAME,"tensach=?",new String[]{tensach});
        if(kq==0)
        {
            return -1;//xoa khong thanh cong
        }
        return 1;//xoa thanh cong
    }

    public Sach checkSachExist(String masach)
    {
        Cursor result = db.query(TABLE_NAME,null,"masach=?",new String[]{masach},null,null,null);
        result.moveToFirst();
        if(result.getCount() != 0)
        {
            Sach s = new Sach();
            s.setMasach(result.getString(0));
            s.setTensach(result.getString(1));
            s.setTentheloai(result.getString(2));
            s.setTacgia(result.getString(3));
            s.setNxb(result.getString(4));
            s.setGiabia(result.getString(5));
            s.setSoluong(result.getString(6));
            return s;
        }else {
            return null;
        }
    }

    public int updateSach(Sach sach)
    {
        ContentValues values = new ContentValues();
        values.put("masach", sach.getMasach());
        values.put("tentheloai", sach.getTentheloai());
        values.put("tacgia", sach.getTacgia());
        values.put("nxb" ,sach.getNxb());
        values.put("giabia" ,sach.getGiabia());
        values.put("soluong" ,sach.getSoluong());
        int kq = db.update(TABLE_NAME,values,"maSach=?", new String[]{sach.getMasach()});
        if(kq==0)
        {
            return -1;
        }
        return 1;
    }
}
