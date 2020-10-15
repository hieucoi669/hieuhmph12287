package com.edu.fpoly.bookmanager;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.fpoly.bookmanager.dao.TheLoaiDAO;
import com.edu.fpoly.bookmanager.model.TheLoai;

public class TheLoaiDetailActivity extends AppCompatActivity {

    TextView tvMaTL;
    TextInputLayout edTenTL, edMoTaTL, edViTriTL;
    TheLoaiDAO theLoaiDAO;
    String maTL, tenTL, moTaTL, viTriTL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai_detail);

        tvMaTL = findViewById(R.id.tvMaTheLoai);
        edTenTL = findViewById(R.id.edTenTheLoaiSua);
        edMoTaTL = findViewById(R.id.edMoTaSua);
        edViTriTL = findViewById(R.id.edViTriSua);
        theLoaiDAO = new TheLoaiDAO(this);

        Bundle bundle = getIntent().getExtras();
        maTL = bundle.getString("maTL","");
        tenTL = bundle.getString("tenTL","");
        moTaTL = bundle.getString("moTaTL","");
        viTriTL = bundle.getString("viTriTL","");

        tvMaTL.setText("Mã thể loại: " + maTL);
        edTenTL.getEditText().setText(tenTL);
        edMoTaTL.getEditText().setText(moTaTL);
        edViTriTL.getEditText().setText(viTriTL);
    }

    public void suaTheLoai(View view) {
        String tentl = checkTenTL();
        String moTatl = checkMoTaTL();
        String viTritl = checkViTriTL();

        if(tentl != null && moTatl != null && viTritl != null){
            TheLoai tl = new TheLoai();
            tl.setMaTL(maTL);
            tl.setTenTL(tentl);
            tl.setMoTa(moTatl);
            tl.setViTri(viTritl);
            if(theLoaiDAO.updateTheLoai(tl) > 0){
                Toast.makeText(getApplicationContext(),"Cập nhật thành công!",Toast.LENGTH_LONG).show();
                finish();
            }else{
                Toast.makeText(getApplicationContext(),"Cập nhật không thành công!",Toast.LENGTH_LONG).show();
            }
        }
    }
    private String checkTenTL(){
        edTenTL.setError(null);
        try{
            String tenTL = edTenTL.getEditText().getText().toString();
            if(tenTL.length() == 0){
                edTenTL.setError("Tên thể loại không được để trống!");
                return null;
            }else if(tenTL.length() < 5 || tenTL.length() > 20){
                edTenTL.setError("Tên thể loại có độ dài từ 5-20 ký tự");
                return null;
            }else{
                return tenTL;
            }
        }catch (Exception e){
            edTenTL.setError("Tên thể loại không hợp lệ!");
            return null;
        }
    }

    private String checkMoTaTL(){
        edMoTaTL.setError(null);
        try{
            String mota = edMoTaTL.getEditText().getText().toString();
            if(mota.length() == 0){
                edMoTaTL.setError("Mô tả không được để trống!");
                return null;
            }else if(mota.length() < 8 || mota.length() > 50){
                edMoTaTL.setError("Mô tả có độ dài từ 8-50 ký tự");
                return null;
            }else{
                return mota;
            }
        }catch (Exception e){
            edMoTaTL.setError("Mô tả không hợp lệ!");
            return null;
        }
    }

    private String checkViTriTL(){
        edViTriTL.setError(null);
        try{
            String viTri = edViTriTL.getEditText().getText().toString();
            if(viTri.length() == 0){
                edViTriTL.setError("Vị trí không được để trống!");
                return null;
            }else if(viTri.length() < 8 || viTri.length() > 25){
                edViTriTL.setError("Vị trí có độ dài từ 8-25 ký tự");
                return null;
            }else{
                return viTri;
            }
        }catch (Exception e){
            edViTriTL.setError("Vị trí không hợp lệ!");
            return null;
        }
    }
}