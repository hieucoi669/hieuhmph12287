package com.edu.fpoly.bookmanager;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.fpoly.bookmanager.dao.TheLoaiDAO;
import com.edu.fpoly.bookmanager.model.TheLoai;


public class TheLoaiActivity extends AppCompatActivity {
    TextInputLayout edMaTheLoai, edTenTheLoai, edMoTa, edViTri;
    TheLoaiDAO theLoaiDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("THỂ LOẠI");
        setContentView(R.layout.activity_them_the_loai);
        edMaTheLoai = findViewById(R.id.edMaTheLoai);
        edTenTheLoai = findViewById(R.id.edTenTheLoai);
        edMoTa = findViewById(R.id.edMoTa);
        edViTri = findViewById(R.id.edViTri);
    }

    public void themTheLoai(View view) {
        theLoaiDAO = new TheLoaiDAO(TheLoaiActivity.this);
        TheLoai theLoai = new TheLoai(edMaTheLoai.getEditText().getText().toString()
                ,edTenTheLoai.getEditText().getText().toString(),
                edMoTa.getEditText().getText().toString(),
                edViTri.getEditText().getText().toString());

        try {
            if(theLoaiDAO.insertTheLoai(theLoai)>0)
            {
                Toast.makeText(getApplicationContext(),"Thêm thể loại thành công!",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Thêm thể loại thất bại!",Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e)
        {
            Log.e("Loi:",e.toString());
        }
        finish();
    }


}
