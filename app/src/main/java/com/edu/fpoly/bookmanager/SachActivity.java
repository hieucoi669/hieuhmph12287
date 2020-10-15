package com.edu.fpoly.bookmanager;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.edu.fpoly.bookmanager.dao.SachDAO;
import com.edu.fpoly.bookmanager.dao.TheLoaiDAO;
import com.edu.fpoly.bookmanager.dao.UserDAO;
import com.edu.fpoly.bookmanager.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class SachActivity extends AppCompatActivity {

    TextInputLayout edMaSach, edTenSach, edTacGia, edNXB, edSoLuong, edGiaBia;
    Spinner spnTheLoai;
    TheLoaiDAO theLoaiDAO;
    SachDAO sachDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);
        setTitle("Sách");

        edMaSach = findViewById(R.id.edMaSach);
        edTenSach = findViewById(R.id.edTenSach);
        edTacGia = findViewById(R.id.edTacGia);
        edNXB = findViewById(R.id.edNXB);
        edSoLuong = findViewById(R.id.edSoLuong);
        edGiaBia = findViewById(R.id.edGiaBia);
        spnTheLoai = findViewById(R.id.spnTheLoai);

        ArrayList<String> listTenTL = new ArrayList<>();

        theLoaiDAO = new TheLoaiDAO(this);
        List<TheLoai> listTL = theLoaiDAO.getAllTheLoai();
        for(TheLoai list : listTL){
            listTenTL.add(list.getTenTL());
        }
    }

    public void addBook(View view) {
    }

    public void showBook(View view) {
    }
}