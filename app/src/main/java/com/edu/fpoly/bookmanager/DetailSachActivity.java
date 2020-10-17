package com.edu.fpoly.bookmanager;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.fpoly.bookmanager.dao.SachDAO;
import com.edu.fpoly.bookmanager.dao.TheLoaiDAO;
import com.edu.fpoly.bookmanager.model.Sach;
import com.edu.fpoly.bookmanager.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class DetailSachActivity extends AppCompatActivity {

    TextView tvMaSachSua;
    TextInputLayout edTacGiaSua, edNXBSua, edGiaBiaSua, edSoLuongSua, tilTheLoaiSua, edTenSachSua;
    InstantAutoComplete actvTheLoaiSua;
    SachDAO sachDAO;
    TheLoaiDAO theLoaiDAO;
    String maSach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sach);

        tvMaSachSua = findViewById(R.id.tvMaSachSua);
        edTenSachSua = findViewById(R.id.edTenSachSua);
        edTacGiaSua = findViewById(R.id.edTacGiaSua);
        edNXBSua = findViewById(R.id.edNXBSua);
        edGiaBiaSua = findViewById(R.id.edGiaBiaSua);
        edSoLuongSua = findViewById(R.id.edSoLuongSua);
        tilTheLoaiSua = findViewById(R.id.tilTheLoaiSua);
        actvTheLoaiSua = findViewById(R.id.actvTheLoaiSua);

        theLoaiDAO = new TheLoaiDAO(this);
        sachDAO = new SachDAO(DetailSachActivity.this);
        Bundle bundle = getIntent().getExtras();
        maSach = bundle.getString("maSach");

        String tensach = bundle.getString("tenSach");
        String tacGia = bundle.getString("tacGia");
        String NXB = bundle.getString("NXB");
        String giaBia = bundle.getString("giaBia");
        String soLuong = bundle.getString("soLuong");
        String theloai = bundle.getString("tenTL");

        tvMaSachSua.setText("Mã sách: " + maSach);
        edTenSachSua.getEditText().setText(tensach);
        actvTheLoaiSua.setText(theloai);
        edTacGiaSua.getEditText().setText(tacGia);
        edNXBSua.getEditText().setText(NXB);
        edGiaBiaSua.getEditText().setText(giaBia);
        edSoLuongSua.getEditText().setText(soLuong);

        ArrayList<String> listTenTL = new ArrayList<>();
        List<TheLoai> listTL = theLoaiDAO.getAllTheLoai();
        for(TheLoai list : listTL){
            listTenTL.add(list.getTenTL());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, listTenTL);

        actvTheLoaiSua.setAdapter(adapter);
    }

    public void suaSach(View view) {
        String tensach = checkTenSach();
        String tentl = checkTenTheLoai();
        String tacgia = checkTacGia();
        String nxb = checkNxb();
        String giabia = checkGiaBia();
        String soluong = checkSoLuong();

        if(tacgia != null && nxb != null && giabia != null && soluong != null &&
            tensach != null && tentl != null){
            Sach s = new Sach();
            s.setMasach(maSach);
            s.setTensach(tensach);
            s.setTentheloai(tentl);
            s.setTacgia(tacgia);
            s.setNxb(nxb);
            s.setGiabia(giabia);
            s.setSoluong(soluong);
            if(sachDAO.updateSach(s) > 0){
                Toast.makeText(getApplicationContext(),
                        "Sửa thông tin sách thành công!",Toast.LENGTH_LONG).show();
                finish();
            }
        }else{
            Toast.makeText(getApplicationContext(),
                    "Sửa thông tin sách không thành công!",Toast.LENGTH_LONG).show();
        }

    }

    private String checkTenSach(){
        edTenSachSua.setError(null);
        try{
            String tenSach = edTenSachSua.getEditText().getText().toString();
            if(tenSach.length() == 0){
                edTenSachSua.setError("Tên sách không được để trống!");
                return null;
            }else if(tenSach.length() < 2 || tenSach.length() > 30){
                edTenSachSua.setError("Tên sách có độ dài từ 2-30 ký tự");
                return null;
            }else{
                return tenSach;
            }
        }catch (Exception e){
            edTenSachSua.setError("Tên sách không hợp lệ!");
            return null;
        }
    }

    private String checkTenTheLoai(){
        tilTheLoaiSua.setError(null);
        try{
            String tentl = actvTheLoaiSua.getText().toString();
            if(tentl.length() == 0){
                tilTheLoaiSua.setError("Tên thể loại không được để trống!");
                return null;
            }else if(theLoaiDAO.checkTLExist(tentl) == null){
                tilTheLoaiSua.setError("Thể loại không tồn tại");
                return null;
            }else{
                return tentl;
            }
        }catch (Exception e){
            tilTheLoaiSua.setError("Tên thể loại không hợp lệ!");
            return null;
        }
    }

    private String checkTacGia(){
        edTacGiaSua.setError(null);
        try{
            String tacgia = edTacGiaSua.getEditText().getText().toString();
            if(tacgia.length() == 0){
                edTacGiaSua.setError("Tên tác giả không được để trống!");
                return null;
            }else if(tacgia.length() < 8 || tacgia.length() > 35){
                edTacGiaSua.setError("Tên tác giả có độ dài từ 8-35 ký tự");
                return null;
            }else{
                return tacgia;
            }
        }catch (Exception e){
            edTacGiaSua.setError("Tên tác giả không hợp lệ!");
            return null;
        }
    }

    private String checkNxb(){
        edNXBSua.setError(null);
        try{
            String nxb = edNXBSua.getEditText().getText().toString();
            if(nxb.length() == 0){
                edNXBSua.setError("Nhà xuất bản không được để trống!");
                return null;
            }else if(nxb.length() < 5 || nxb.length() > 30){
                edNXBSua.setError("Tên nhà xuất bản có độ dài từ 5-30 ký tự");
                return null;
            }else{
                return nxb;
            }
        }catch (Exception e){
            edNXBSua.setError("Tên nhà xuất bản không hợp lệ!");
            return null;
        }
    }

    private String checkGiaBia(){
        edGiaBiaSua.setError(null);
        try{
            String giabia = edGiaBiaSua.getEditText().getText().toString();
            Double gia = Double.parseDouble(giabia);
            if(giabia.length() == 0){
                edGiaBiaSua.setError("Giá bìa không được để trống!");
                return null;
            }else if(gia <= 0){
                edGiaBiaSua.setError("Giá bìa phải lớn hơn 0!");
                return null;
            }else{
                return giabia;
            }
        }catch (Exception e){
            edGiaBiaSua.setError("Giá bìa không hợp lệ!");
            return null;
        }
    }

    private String checkSoLuong(){
        edSoLuongSua.setError(null);
        try{
            String soluong = edSoLuongSua.getEditText().getText().toString();
            Double so = Double.parseDouble(soluong);
            if(soluong.length() == 0){
                edSoLuongSua.setError("Số lượng không được để trống!");
                return null;
            }else if(so <= 0){
                edSoLuongSua.setError("Số lượng phải lớn hơn 0!");
                return null;
            }else{
                return soluong;
            }
        }catch (Exception e){
            edSoLuongSua.setError("Số lượng không hợp lệ!");
            return null;
        }
    }
}