package com.edu.fpoly.bookmanager;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.edu.fpoly.bookmanager.dao.SachDAO;
import com.edu.fpoly.bookmanager.dao.TheLoaiDAO;
import com.edu.fpoly.bookmanager.model.Sach;
import com.edu.fpoly.bookmanager.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class SachActivity extends AppCompatActivity {

    TextInputLayout edMaSach, edTenSach, edTacGia, edNXB, edSoLuong, edGiaBia, tilTheLoai;
    InstantAutoComplete actvTheLoai;
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
        tilTheLoai = findViewById(R.id.tilTheLoai);
        actvTheLoai = findViewById(R.id.actvTheLoai);

        ArrayList<String> listTenTL = new ArrayList<>();

        sachDAO = new SachDAO(this);
        theLoaiDAO = new TheLoaiDAO(this);
        List<TheLoai> listTL = theLoaiDAO.getAllTheLoai();
        for(TheLoai list : listTL){
            listTenTL.add(list.getTenTL());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, listTenTL);

        actvTheLoai.setAdapter(adapter);
    }

    public void themSach(View view) {
        String masach = checkMaSach();
        String tensach = checkTenSach();
        String tentl = checkTenTheLoai();
        String tacgia = checkTacGia();
        String nxb = checkNxb();
        String giabia = checkGiaBia();
        String soluong = checkSoLuong();

        if(masach != null && tensach != null && tentl != null && tacgia != null
            && nxb != null && giabia != null && soluong != null){
            Sach s = new Sach();
            s.setMasach(masach);
            s.setTensach(tensach);
            s.setTentheloai(tentl);
            s.setTacgia(tacgia);
            s.setNxb(nxb);
            s.setGiabia(giabia);
            s.setSoluong(soluong);
            if(sachDAO.insertSach(s)>0)
            {
                Toast.makeText(getApplicationContext(),
                        "Thêm sách thành công!",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),
                    "Thêm sách không thành công!",Toast.LENGTH_LONG).show();
        }
    }

    private String checkMaSach(){
        edMaSach.setError(null);
        try{
            String masach = edMaSach.getEditText().getText().toString();
            if(masach.length() == 0){
                edMaSach.setError("Mã sách không được để trống!");
                return null;
            }else if(masach.length() != 6){
                edMaSach.setError("Mã sách phải gồm 6 ký tự!");
                return null;
            }else if(sachDAO.checkSachExist(masach) != null){
                edMaSach.setError("Mã sách đã tồn tại!");
                return null;
            }else{
                return masach;
            }
        }catch (Exception e){
            Log.i("test", e.toString());
            edMaSach.setError("Mã sách không hợp lệ!");
            return null;
        }
    }

    private String checkTenSach(){
        edTenSach.setError(null);
        try{
            String tenSach = edTenSach.getEditText().getText().toString();
            if(tenSach.length() == 0){
                edTenSach.setError("Tên sách không được để trống!");
                return null;
            }else if(tenSach.length() < 2 || tenSach.length() > 30){
                edTenSach.setError("Tên sách có độ dài từ 2-30 ký tự");
                return null;
            }else{
                return tenSach;
            }
        }catch (Exception e){
            edTenSach.setError("Tên sách không hợp lệ!");
            return null;
        }
    }

    private String checkTenTheLoai(){
        tilTheLoai.setError(null);
        try{
            String tentl = actvTheLoai.getText().toString();
            if(tentl.length() == 0){
                tilTheLoai.setError("Tên thể loại không được để trống!");
                return null;
            }else if(theLoaiDAO.checkTLExist(tentl) == null){
                tilTheLoai.setError("Thể loại không tồn tại");
                return null;
            }else{
                return tentl;
            }
        }catch (Exception e){
            tilTheLoai.setError("Tên thể loại không hợp lệ!");
            return null;
        }
    }

    private String checkTacGia(){
        edTacGia.setError(null);
        try{
            String tacgia = edTacGia.getEditText().getText().toString();
            if(tacgia.length() == 0){
                edTacGia.setError("Tên tác giả không được để trống!");
                return null;
            }else if(tacgia.length() < 8 || tacgia.length() > 35){
                edTacGia.setError("Tên tác giả có độ dài từ 8-35 ký tự");
                return null;
            }else{
                return tacgia;
            }
        }catch (Exception e){
            edTacGia.setError("Tên tác giả không hợp lệ!");
            return null;
        }
    }

    private String checkNxb(){
        edNXB.setError(null);
        try{
            String nxb = edNXB.getEditText().getText().toString();
            if(nxb.length() == 0){
                edNXB.setError("Nhà xuất bản không được để trống!");
                return null;
            }else if(nxb.length() < 5 || nxb.length() > 30){
                edNXB.setError("Tên nhà xuất bản có độ dài từ 5-30 ký tự");
                return null;
            }else{
                return nxb;
            }
        }catch (Exception e){
            edNXB.setError("Tên nhà xuất bản không hợp lệ!");
            return null;
        }
    }

    private String checkGiaBia(){
        edGiaBia.setError(null);
        try{
            String giabia = edGiaBia.getEditText().getText().toString();
            Double gia = Double.parseDouble(giabia);
            if(giabia.length() == 0){
                edGiaBia.setError("Giá bìa không được để trống!");
                return null;
            }else if(gia <= 0){
                edGiaBia.setError("Giá bìa phải lớn hơn 0!");
                return null;
            }else{
                return giabia;
            }
        }catch (Exception e){
            edGiaBia.setError("Giá bìa không hợp lệ!");
            return null;
        }
    }

    private String checkSoLuong(){
        edSoLuong.setError(null);
        try{
            String soluong = edSoLuong.getEditText().getText().toString();
            Double so = Double.parseDouble(soluong);
            if(soluong.length() == 0){
                edSoLuong.setError("Số lượng không được để trống!");
                return null;
            }else if(so <= 0){
                edSoLuong.setError("Số lượng phải lớn hơn 0!");
                return null;
            }else{
                return soluong;
            }
        }catch (Exception e){
            edSoLuong.setError("Số lượng không hợp lệ!");
            return null;
        }
    }
}