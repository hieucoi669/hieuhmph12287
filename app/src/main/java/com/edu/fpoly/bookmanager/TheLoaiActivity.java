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

        theLoaiDAO = new TheLoaiDAO(TheLoaiActivity.this);
    }

    public void themTheLoai(View view) {
        String matl = checkMaTL();
        String tentl = checkTenTL();
        String mota = checkMoTaTL();
        String vitri = checkViTriTL();

        if(matl != null && tentl != null && mota != null && vitri != null){
            TheLoai tl = new TheLoai();
            tl.setMaTL(matl);
            tl.setTenTL(tentl);
            tl.setViTri(vitri);
            tl.setMoTa(mota);

            if(theLoaiDAO.insertTheLoai(tl)>0)
            {
                Toast.makeText(getApplicationContext(),
                        "Thêm thể loại thành công!",Toast.LENGTH_LONG).show();
                finish();
            }
        }else{
            Toast.makeText(getApplicationContext(),
                    "Thêm thể loại không thành công!",Toast.LENGTH_LONG).show();
        }
    }

    private String checkMaTL(){
        edMaTheLoai.setError(null);
        try{
            String matl = edMaTheLoai.getEditText().getText().toString();
            if(matl.length() == 0){
                edMaTheLoai.setError("Mã thể loại không được để trống!");
                return null;
            }else if(matl.length() != 6){
                edMaTheLoai.setError("Mã thể loại phải có 6 ký tự!");
                return null;
            }else if(theLoaiDAO.checkTLExist(matl) != null){
                edMaTheLoai.setError("Mã thể loại đã tồn tại!");
                return null;
            }else{
                return matl;
            }
        }catch (Exception e){
            edMaTheLoai.setError("Mã thể loại không hợp lệ!");
            return null;
        }
    }

    private String checkTenTL(){
        edTenTheLoai.setError(null);
        try{
            String tenTL = edTenTheLoai.getEditText().getText().toString();
            if(tenTL.length() == 0){
                edTenTheLoai.setError("Tên thể loại không được để trống!");
                return null;
            }else if(tenTL.length() < 5 || tenTL.length() > 20){
                edTenTheLoai.setError("Tên thể loại có độ dài từ 5-20 ký tự");
                return null;
            }else{
                return tenTL;
            }
        }catch (Exception e){
            edTenTheLoai.setError("Tên thể loại không hợp lệ!");
            return null;
        }
    }

    private String checkMoTaTL(){
        edMoTa.setError(null);
        try{
            String mota = edMoTa.getEditText().getText().toString();
            if(mota.length() == 0){
                edMoTa.setError("Mô tả không được để trống!");
                return null;
            }else if(mota.length() < 8 || mota.length() > 50){
                edMoTa.setError("Mô tả có độ dài từ 8-50 ký tự");
                return null;
            }else{
                return mota;
            }
        }catch (Exception e){
            edMoTa.setError("Mô tả không hợp lệ!");
            return null;
        }
    }

    private String checkViTriTL(){
        edViTri.setError(null);
        try{
            String viTri = edViTri.getEditText().getText().toString();
            if(viTri.length() == 0){
                edViTri.setError("Vị trí không được để trống!");
                return null;
            }else if(viTri.length() < 8 || viTri.length() > 25){
                edViTri.setError("Vị trí có độ dài từ 8-25 ký tự");
                return null;
            }else{
                return viTri;
            }
        }catch (Exception e){
            edViTri.setError("Vị trí không hợp lệ!");
            return null;
        }
    }

}
